<?php
namespace app\admin\controller;

use think\Request;        //使用Request类
use app\admin\model\User;    //使用User模型
use think\Controller;    //使用基类控制器
use think\Db;    //使用数据库Db类
use app\admin\controller\Common;     //使用公共控制器类
/**
* 登录公共控制器
*/
class Publics extends Controller
{
    public $captcha;    //验证码类示例
    function __construct()
    {
        parent::__construct();
        $this->captcha = new \think\captcha\Captcha();
    }

    public function index()
    {
        return $this->fetch('login');
    }

    public function login()
    {
      //已登录？去首页
        if (session('?username')) {
            $this->redirect('Index/index');
        }
        if (Request::instance()->isPost()) {     //使用post方式访问
            var_dump(Request::instance()->param());
        } else {        //使用get方法访问
            return $this->fetch();
        }
    }

  //登出
    public function logout()
    {
        if (session('?username')) {
            session(null);
            // \think\Cache::clear('user_data');
            return $this->redirect('login');
        } else {
            return $this->error('注销失败');
        }
    }

  //执行登录操作
    public function doLogin()
    {
        if (request()->isPost()) {
            $verifyCode = Request::instance()->post('code','','trim');
            if (!$this->captcha->check($verifyCode)) {
                return $this->error('验证码不正确');
            }
            $email = Request::instance()->post('email','','trim');
            $password = Request::instance()->post('password','','trim');
            if ($email == '' || $password == '' || $verifyCode == '') {
                $this->error('各项不能为空');
            }
            $info = Db::table('think_user')->where('email',$email)->field('id,password,name')->select();
            if (empty($info)) {
                return $this->error('账号不存在');
            }
            $info = $info[0];
            if ($info['password'] != md5($password)) {
                $this->error('密码错误');
            }
       //登陆时，检查用户及所属用户组的的权限与状态，这部分可暂时注释掉
            
            switch (Common::checkLogin($info['id'])) {
                case 0:
                    return $this->error('账号已被锁定，请联系管理员');
                    break;
                case 1:
                    return $this->error('用户组已被锁定，请联系管理员');
                    break;
                case 2:
                    return $this->error('没有权限，请联系管理员');
                    break;
                default:
                    break;
            }


            session('username',$info['name']);
            session('keyid',$info['id']);
            return $this->success('登录成功','Index/index');
        } else {
            return 'hello world';
        }
    }

  //创建验证码
    public function createCode()
    {
        return $this->captcha->entry();
    }

  //通知（登录相关操作）
    public function notify($uid)
    {
        $ip = get_client_ip();            //获取用户ip地址
        $location = check_ip_location($ip);        //获取ip地址的地理位置
        $time = date('Y-m-d H:i:s',time());        //登录时间
        $old_info = Db::table('think_user')->where('id',$uid)->field('login_time,login_ip,login_num,last_login_time,last_login_ip,login_location')->select()[0];        //用户上一次登录的信息
        $new_info = [
                    'login_time' => $time,
                    'login_ip' => $ip,
                    'login_location' => $location,
                    'login_num' => $old_info['login_num']+1,
                    'last_login_time' => $old_info['login_time'],
                    'last_login_ip' => $old_info['login_ip'],
                    'last_login_location' => $old_info['login_location']
                    ];
        Db::startTrans();        //开始事务处理
        try {
            Db::table('think_user')->where('id',$uid)->update($new_info);
            Db::commit();
            return true;
        } catch (\Exception $e) {
            Db::rollback();
            return false;
        }
    }

}
