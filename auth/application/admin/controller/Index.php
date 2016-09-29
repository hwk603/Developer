<?php
namespace app\admin\controller;

use app\admin\controller\Common;        //公共控制器
use think\Db;
use app\admin\model\User as U;        //为了方便，可以使用别名，用 U 代表 User 模型
use app\admin\model\Group as G;        //用户组模型
/**
 * 后台首页控制器
 */
class Index extends Common
{
    public function index()
    {
        $view = new \think\View();
     //系统信息
        $sysinfo = [
                    'system'=>php_uname('s'),
                    'mode'=>php_sapi_name(),
                    'php_version'=>PHP_VERSION,
                    'mysql_version'=>Db::table('think_user')->query('select version() as ver')[0]['ver'],
                    'apache_version'=>apache_get_version(),
                    'time' => date("Y-m-d H:i:s", time()),
                    'language' => $_SERVER['HTTP_ACCEPT_LANGUAGE'],
                    'port' => $_SERVER['SERVER_PORT'],
                    'max_upload' => ini_get("file_uploads") ? ini_get("upload_max_filesize") : "Disabled",
                    'gd_version'=> gd_info()['GD Version'],
                    'zend_version'=>Zend_Version(),
                    'port'=>$_SERVER['SERVER_PORT'],
                    'memery'=>memory_get_usage(),
                    'user'=>getmypid(),
                    'current_user_name'=>Get_Current_User(),
                    'sys_version'=> php_uname('r'),
                    ];
    //用户组id
        $groups = [];
    //用户组名
        $title = '';
    //使用模型的关联查询，User 模型与 Group 模型关联。查询此登录用户的所属分组id
        foreach (U::find(session('keyid'))->getGroup as $key => $value) {
                $groups[] = $value->groupid;
        }
        if (empty($groups)) {
            $title = '无分组';
        } else {
       //在Group模型中获取分组的名
            foreach (G::all($groups) as $key => $value) {
                $title .= '[' . $value->title .']';
            }
        }

     //用户个人信息
        $profile_data = (new U())->where('id',session('keyid'))->field('email,last_login_ip,last_login_location,last_login_time,create_time,status,login_num')->select()[0];
        $profile_data['title'] = $title;
        $profile = [
                    'uid' => session('keyid'),
                    'name' => session('username'),
                    'email' => $profile_data['email'],
                    'group' => $profile_data['title'],
                    'last_login_ip' => $profile_data['last_login_ip'],
                    'last_login_location' => $profile_data['last_login_location'] == '目前暂时没有您的IP信息,&nbsp;&nbsp;期待您的分享' ? '本地' : $profile_data['last_login_location'],
                    'last_login_time' => $profile_data['last_login_time'],
                    'create_time' => $profile_data['create_time'],
                    'status' => $profile_data['status'],
                    'login_num' => $profile_data['login_num'],
                    ];
        $info['profile'] = $profile;
        $info['sysinfo'] = $sysinfo;
        $view->assign('info',$info);
        return $view->fetch('index/index');
    }
}
