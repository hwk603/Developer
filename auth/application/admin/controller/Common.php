<?php
namespace app\admin\controller;

use think\Controller;
use think\Db;
use app\admin\model\User;
use app\admin\model\Rule;
use app\admin\model\Group;
/**
* 公共控制器
*/
class Common extends Controller
{
    function __construct()
    {
        parent::__construct();
        if (!session('?username')) {
            $this->redirect('publics/login');
        }
        //默认的权限访问规则 模块/控制器/方法
        $name = strtolower(request()->module(). '/' . request()->controller() . '/' . request()->action());
    //检查权限 check 方法，如果session中有 `admin` ,代表是超级管理员，不用阻止操作。
        if (!$this->check($name, session('keyid'))) {
            if (!session('?admin')) {
                return $this->error('没有权限','index/index');
            }
        }
    }
		public function check($name,$uid)
    {
          //根据 uid 检查用户的状态
        if (!self::checkUserStatus($uid)) {
            session(null);
            return $this->error('账号已被锁定，请联系管理员','publics/login');
        }
      //本来想做成多条规则检验，但是只做了单条规则的检验，有兴趣的可以自己尝试
        if (is_string($name)) {
       //调用本类的静态方法，根据uid取得分组信息
            $groups = self::getGroupsByUid($uid);
            if (empty($groups)) {
                return false;
            }
       //调用本类静态方法，根据分组（数组形式）查询分组状态
            if (!self::checkGroupStatus($groups)) {
								session(null);
                return $this->error('用户组被锁定，请联系管理员','publics/login');
            }
       //调用本类静态方法，根据规则名检查规则状态（为了简单，这里只适用于每项权限单条规则的情况）
            if (!self::checkRuleStatus($name)) {
                return $this->error('没有权限');
            }
      //根据分组获取全部分组的权限信息
            $rules = self::getRulesByGroups($groups);
            $flag = false;
      //获取当前权限规则的id值（适用于单规则权限）
            $rule_id = Db::table('think_rule')->where('rules',$name)->field('id')->select()[0]['id'];
            $flag = false;
      //逐一检查当前规则是否符合权限
            foreach ($rules as $key => $value) {
                if (in_array($rule_id,explode(',',$value['rules']))) {
                    $flag = true;
                    break;
                }
            }
            return $flag;
        }
    }
		public static function getGroupsByUid($uid)
    {
        return Db::table('think_group_access')->where('uid',$uid)->field('groupid')->select();
    }
		public static function getRulesByGroups($groups)
    {
        if (is_array($groups)) {
            foreach ($groups as $key => $value) {
                $rules[] = Db::table('think_group')->where('id',$value['groupid'])->field('rules')->select()[0];
            }
            return $rules;
        }
    }
		public static function checkUserStatus($uid)
    {
        $status = Db::table('think_user')->where('id',$uid)->field('status')->select()[0]['status'];
        if ($status == '1') {
            return true;
        } else {
            return false;
        }
    }
		public static function checkGroupStatus($group)
    {
      //若多个分组，数组类型
        if (is_array($group)) {
            $flag = false;
            foreach ($group as $key => $value) {
                if ($value['groupid'] == 1) {        //代表超级管理员组，加入 session 标识
                    session('admin','1');
                } else {
                    session('admin',null);
                }
         //多个分组关系默认为 or，即只要一个分组可使用，则通过检测
                if (Db::table('think_group')->where('id',$value['groupid'])->field('status')->select()[0]['status'] == 1) {
                    $flag = true;
                }
            }
            return $flag;
        }
     //参数为单个id值时
        if (Db::table('think_group')->where('id',$group)->field('status')->select()[0]['status'] == 1) {
            return true;
        } else {
            return false;
        }
    }
		public static function checkRuleStatus($rule)
    {
				//echo $rule;
				//die;
        if (Db::table('think_rule')->where('rules',$rule)->field('status')->select()[0]['status'] == 1) {
            return true;
        } else {
            return false;
        }
    }
		public static function checkLogin($uid)
    {
        if (!self::checkUserStatus($uid)) {
            return 0;    //用户被禁用
        }
        $groups = self::getGroupsByUid($uid);
        if (!self::checkGroupStatus($groups)) {
            return 1;    //分组被禁用
        }
        if (empty(self::getGroupsByUid($uid))) {
            return 2;    //不属于任何分组
        }
        return 3;        //没有问题，可以通过
    }
}
