<?php
namespace app\admin\controller;

use think\Controller;
use app\admin\controller\Common;        //使用公共控制器
use think\Db;   
/**
* 权限管理控制器
*/
class Auth extends Common
{
	function __construct()
	{
		parent::__construct();
	}

	public function index()
	{
		return $this->redirect('authList');
	}

	//权限列表
	public function authList()
	{
		$authList = Db::table('think_rule')->field('id,rules,title,status,create_time,update_time')->paginate();
		$this->assign('authList',$authList);
		return $this->fetch();
	}

	//添加权限
	public function addAuth()
	{
		if (request()->isGet()) {        //Get请求
				return $this->fetch();
		} elseif (request()->isPost()) {    //Post请求
				$title = input('post.title');        //权限名
				$titles = Db::table('think_rule')->field('title')->select();
	 //权限名判重
				foreach ($titles as $key => $value) {
						if ($value['title'] == $title) {
								return $this->error('权限名已被使用');
						}
				}
	 //权限使用状态
				$status = input('post.status') != null ? 1 : -1;
	//传递过来的权限数据是以‘，’分开的可能存在多个组合规则的字符串
				$rules = input('post.rules');
				$create_time = date('Y-m-d H:i:s',time());
				if (Db::table('think_rule')->insert(['title'=>$title,'status'=>$status,'rules'=>$rules,'create_time'=>$create_time])) {
						return $this->success('操作成功','Auth/authList');
				} else {
						return $this->error('操作失败');
				}
		}
	}
	public function editAuth($id)
	    {
	        if (request()->isGet()) {        //Get 请求
	            $authInfo = Db::table('think_rule')->where('id',$id)->field('title,status,rules')->select()[0];
	            $authInfo['rules'] = str_replace(',',','.PHP_EOL,$authInfo['rules']);
	            $authInfo['model'] = substr($authInfo['rules'],0,5);
	            $this->assign('authInfo',$authInfo);
	            return $this->fetch();
	        } elseif (request()->isPost()) {        //Post请求
	            $title = input('post.title');
	            $titles = Db::table('think_rule')->where("id != $id")->field('title')->select();
	      //权限名判重
	            foreach ($titles as $key => $value) {
	                if ($value['title'] == $title) {
	                    return $this->error('权限名已被使用');
	                }
	            }
	            $status = input('post.status') != null ? 1 : -1;
	            $rules = input('post.rules');
	            $url = input('post.url');
	      //更新权限信息（状态。规则）
	            if (Db::table('think_rule')->where('id',$id)->update(['title'=>$title,'status'=>$status,'rules'=>$rules])) {
	                return $this->success('操作成功',$url);
	            } else {
	                return $this->error('操作失败');
	            }
	        }
	    }
			public function delAuth($id)
			    {
			          //查找所有分组的权限分配信息
			        $used_rules = Db::table('think_group')->field('rules')->select();
			        foreach ($used_rules as $key => $value)
			            if (in_array($id, explode(',',$value['rules']))) {
			                return $this->error('有分组正在使用该权限，无法删除');
			            } else {
			                if (Db::table('think_rule')->delete($id)) {
			                    return $this->success('权限删除成功');
			                }
			            }
			    }
					public function lockAuth($id,$status)
					    {
					        if (Db::table('think_rule')->where('id',$id)->setField('status',$status)) {
					            return $this->success('操作成功');
					        } else {
					            return $this->error('操作失败');
					        }
					    }


}
