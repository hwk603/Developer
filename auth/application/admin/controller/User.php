<?php
namespace app\admin\controller;

use app\admin\controller\Common;
use think\Db;
use think\Request;
use app\admin\model\User as U;
use app\admin\model\Group as G;
/**
* user controller
*/
class User extends Common
{
    public $view;        //视图实例
    function __construct()
    {
        parent::__construct();
        $this->view = new \think\View();
    }


public function index()
    {
      //用户所属分组id（多对多）
        $groups = [];
   //分组名
        $title = '';
  //查询用户表中部分信息列表，使用了系统的分页方式
        $userList = Db::table('think_user')->field('id,name,email,last_login_time,last_login_ip,last_login_location,login_num,status,create_time')->paginate();
       //分页后，一页内容显示的用户的全部id
        $uids = [];
        foreach ($userList as $key => $value) {
            $uids[] = $value['id'];
        }
  //获取每一个用户的所属分组情况
        foreach ($uids as $k => $id) {
            $title = '';
            $groups = [];
      //使用模型关联查询，一个uid可查询出多个groupid
            foreach (U::find($id)->getGroup as $key => $value) {
                $groups[] = $value->groupid;
            }
      //去除重复项（一般来说不会出现重复情况）
            $groups = array_unique($groups);
       //如果一个用户不属于任何分组，则显示 `无分组`
            if (empty($groups)) {
                $title = '无分组';
                $tmp = $userList[$k];        //将分组信息加入用户信息列表 userList
                $tmp['title'] = $title;        //涉及二维数组，不能直接使用 $var[x][y] 操作
                $userList[$k] = $tmp;        //需要使用一个中间变量传递
                continue;
            }
      //根据groupid数组，获取对应的分组名 title
            foreach (G::all($groups) as $key => $value) {
                $title .= '[' . $value->title .']';
            }
            $tmp = $userList[$k];
            $tmp['title'] = $title;
            $userList[$k] = $tmp;
        }
        $this->assign('userList',$userList);
        return $this->view->fetch();
    }

		public function adduser()
{
		if (Request::instance()->isGet()) {
	 //添加用户时，用户选择分组需要的所有的分组列表
				$groupList = Db::table('think_group')->where('status = 1')->field('id,title')->select();
				$this->assign('groupList',$groupList);
				return $this->fetch();
		} elseif (Request::instance()->isPost()) {
				$name = input('post.username');
				$email = input('post.email');
	//检查用户名或邮箱唯一，这是自定义的方法，下面将会编写
				$this->checkUnique($name,$email);
				$password = md5(input('post.password'));
				if ($password != md5(input('post.confirm'))) {
						return $this->error('确认密码不一致');
				}
	//是否启用账户
				$status = input('post.status') != null ? 1 : -1;
	//所属多个分组，接受数组参数
				$group = input('post.group/a');
	//若没有选择分组，则自动分组为 ‘游客’
				if (empty($group)) {
						$group = Db::table('think_group')->where('title','游客')->field('id')->select()[0]['id'];
				}
				$time = date('Y-m-d H:i:s',time());
				$User = new \app\admin\model\User();
	//插入新用户数据
				$User->save(['name'=>$name,'email'=>$email,'password'=>$password,'status'=>$status,'create_time'=>$time]);
	//获取插入数据的自增id
				$uid = $User->id;
				if ($uid) {
		 //如果选择了多个分组，在 group_access 中一一对应添加
						if (is_array($group)) {
								foreach (input('post.group/a') as $key => $value) {
										Db::table('think_group_access')->insert(['uid'=>$uid,'groupid'=>$value]);
								}
						} else {        //如果没有选择分组，默认分组为游客，添加对应关系
								Db::table('think_group_access')->insert(['uid'=>$uid,'groupid'=>$group]);
						}
						return $this->success('操作成功','User/index');
				} else {
						return $this->error($User->getError());
				}
		}
}
public function editUser($id){
	if (Request::instance()->isGet()) {
	          //此用户的分组
	            $groups = [];
	          //其他基本信息
	            $userInfo =Db::table('think_user')->where('id',$id)->field('name,email,status')->select()[0];
	          //在用户明细表中，查找该用户对应的全部分组
	            foreach (U::find($id)->getGroup as $key => $value) {
	                $groups[] = $value->groupid;
	            }
	          //将分组添加到基本信息中
	            $userInfo['groups'] = $groups;
	          //模板变量赋值
	            $this->assign('info',$userInfo);
	          //获取全部分组信息
	            $groupList = Db::table('think_group')->where('status = 1')->field('id,title')->select();
	          //逐个比较，若用户的分组存在于全部分组中，则该分组添加一个键值对 checked
	            foreach ($groupList as $key => $value) {
	                if (in_array($value['id'], $userInfo['groups'])) {
	                    $groupList[$key]['checked'] = '1';
	                } else {
	                    $groupList[$key]['checked'] = '0';
	                }
	            }
	          //模板变量赋值
	            $this->assign('groupList',$groupList);
	            return $this->view->fetch();
	  }
		elseif (Request::instance()->isPost()) {        //处理post请求
            $User = new \app\admin\model\User();    //使用 User 模型
            $name = input('post.username');        //接受数据
            $email = input('post.email');
            $this->checkUnique($name,$email,$id);        //检测数据唯一性
            if (input('post.password') != '') {        //检验密码
                if (input('post.password') != input('post.confirm')) {
                    return $this->error('确认密码不一致');
                }
                $password = md5(input('post.password'));
            }
            if (input('post.status') != null) {        //是否开启账号
                $status = 1;
            } else {
                $status = -1;
            }
            $groups = input('post.group/a');        //前端传来的分组信息
            $url = input('post.url');        //跳转的url
            if (isset($password)) {            //密码设置可选，所以区别对待
                $data = ['name'=>$name,'email'=>$email,'password'=>$password,'status'=>$status];
            } else {
                $data = ['name'=>$name,'email'=>$email,'status'=>$status];
            }
            $result = $User->validate(        //使用模型更新数据，使用模型的验证功能
                [
                    'name'  => 'require|max:25',
                    'email'   => 'email',
                ],
                [
                    'name.require' => '名称必须',
                    'name.max'     => '名称最多不能超过25个字符',
                    'email'        => '邮箱格式错误',
                ]
            )->save($data,['id'=>$id]);
            if ($User->getError()) {
                return $this->error("信息修改失败");
            }
            if ($groups == null) {        //若用户的分组信息为空，则默认设置为 `游客`
                $groups[] = Db::table('think_group')->where('title','游客')->field('id')->select()[0]['id'];
            }
            $data = [];
            Db::startTrans();        //开始事务，在用户组明细表中，删掉该用户原来的分组对应关系，重新插入对应关系
            Db::table('think_group_access')->where('uid',$id)->delete();
            foreach ($groups as $key => $value) {
                $data[$key]['uid'] = $id;
                $data[$key]['groupid'] = $value;
            }
            if (Db::table('think_group_access')->insertAll($data)) {
                Db::commit();
                return $this->success('操作成功',$url);
            } else {
                Db::rollback();
                return $this->error("权限修改失败");
            }
   }
}
public function delUser($id)
 {
		 if ($id == 1 && session('keyid')) {
				 return $this->error('非法操作');
		 }
		 Db::startTrans();
		 if (Db::table('think_user')->delete(intval($id))) {
				 if (Db::table('think_group_access')->where('uid',$id)->delete()) {
						 Db::commit();
						 return $this->success('操作成功');
				 } else {
						 Db::rollback();
						 return $this->error('操作失败');
				 }
		 } else {
				 Db::rollback();
				 return $this->error('操作失败');
		 }
 }

public function lockUser($id,$status)
{
 //不能锁定超管
		if ($id == 1 && session('keyid')) {
				return $this->error('非法操作');
		}
 //更新 status 字段
		if (Db::table('think_user')->where('id',$id)->setField('status',$status)) {
				return $this->success('操作成功');
		} else {
				return $this->error('操作失败');
		}
}

public function checkUnique($name,$email,$id = 0)
{
        if ($id == 0) {
            $originInfo = Db::table('think_user')->field('name,email')->select();
        } else {
            $originInfo = Db::table('think_user')->where("id != $id")->field('name,email')->select();
        }
        foreach ($originInfo as $key => $value) {
            if ($value['name'] == $name || $value['email'] == $email) {
                return $this->error("用户名或邮箱已被占用");
            }
        }
}

public function groupList()
    {
        $groupList = Db::table('think_group')->field('id,title,status,rules,create_time')->paginate();
        $this->assign('groupList',$groupList);
        return $this->view->fetch();
    }

		public function addGroup()
		    {
		        if (Request::instance()->isGet()) {        //get请求，展示添加页面，查询并展示所有可用的权限规则
		            $rules = Db::table('think_rule')->where('status',1)->field('id,title')->select();
		            $this->assign('rules',$rules);
		            return $this->view->fetch();
		        } elseif (Request::instance()->isPost()) {        //post请求
		            $title = input('post.title');
		       //新分组名判重
		            if (Db::table('think_group')->where('title',$title)->find()) {
		                return $this->error('组名已存在');
		            }
		            $status = input('post.title');
		            $rules = '';
		            $time = date('Y-m-d H:i:s');
		       //处理新分组对应的权限规则，传入数据为 [1,2,4],处理后为 ‘1,2,4’
		            if (input('post.rules/a') != null) {
		                foreach (input('post.rules/a') as $key => $value) {
		                    $rules .= $value.',';
		                }
		                $rules = substr($rules,0,-1);
		            } else {
		                $rules = Db::table('think_rule')->where('title','系统首页')->field('id')->select()[0]['id'];
		            }
		            $status = input('post.status') != null ? 1 : -1;        //是否启用该分组
		       //数据插入
		            if (Db::table('think_group')->insert(['title'=>$title,'status'=>$status,'rules'=>$rules,'create_time'=>$time])) {
		                return $this->success('操作成功','User/groupList');
		            } else {
		                return $this->error('操作失败');
		            }
		        }
		    }

public function editGroup($id){
	if (Request::instance()->isGet()) {
          //获取该分组的基本信息
            $groupInfo = Db::table('think_group')->where('id',$id)->field('title,status,rules')->select()[0];
          //处理该分组信息中的rules字段
            $groupInfo['rules'] = explode(',',$groupInfo['rules']);
          //获取规则表中可用的全部规则列表
            $rulesInfo = Db::table('think_rule')->where('status',1)->field('id,title')->select();
          //同上编辑用户逻辑
            foreach ($rulesInfo as $key => $value) {
                if (in_array($value['id'], $groupInfo['rules'])) {
                    $rulesInfo[$key]['checked'] = 1;
                } else {
                    $rulesInfo[$key]['checked'] = 0;
                }
            }
            $this->assign('groupInfo',$groupInfo);
            $this->assign('rulesInfo',$rulesInfo);
            return $this->view->fetch();
        }

				elseif (Request::instance()->isPost()) {
			            $title = input('post.title');
			            $titles = Db::table('think_group')->where("id != $id")->field('title')->select();
			         //组名判重
			            foreach ($titles as $key => $value) {
			                if (in_array($title,$value)) {
			                    return $this->error('组名已存在');
			                }
			            }
			            $rules = '';
			          //处理权限规则
			            if (input('post.rules/a') != null) {
			                foreach (input('post.rules/a') as $key => $value) {
			                    $rules .= $value.',';
			                }
			                $rules = substr($rules,0,-1);
			            } else {        //若没有权限，默认可以访问系统首页
			                $rules = Db::table('think_rule')->where('title','系统首页')->field('id')->select()[0]['id'];
			            }
			            if (Db::table('think_group')->where('id',$id)->update(['title'=>$title,'rules'=>$rules])) {
			                return $this->success('操作成功',input('post.url'));
			            } else {
			                return $this->error('信息未修改');
			            }
		}
}

public function delGroup($id)
{
       if ($id == 1) {
            return $this->error('非法操作');
        }
        if (Db::table('think_group_access')->where('groupid',$id)->find()) {
            return $this->error('此分组下有用户存在，暂时无法删除');
        }
        if (Db::table('think_group')->delete($id)) {
            return $this->success('操作成功');
        } else {
            return $this->error('操作失败');
        }

}
public function lockGroup($id,$status)
{
      if ($id == 1) {        //分组id为1，为超级管理员组，不能锁定
            return $this->error('非法操作');
        }
        if (Db::table('think_group')->where('id',$id)->setField('status',$status)) {
            return $this->success('操作成功');
        } else {
            return $this->error('操作失败');
        }
}
}
