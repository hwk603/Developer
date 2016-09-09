<?php 
/**
 * This file is part of workerman.
 *
 * Licensed under The MIT License
 * For full copyright and license information, please see the MIT-LICENSE.txt
 * Redistributions of files must retain the above copyright notice.
 *
 * @author walkor<walkor@workerman.net>
 * @copyright walkor<walkor@workerman.net>
 * @link http://www.workerman.net/
 * @license http://www.opensource.org/licenses/mit-license.php MIT License
 */
use \Workerman\Worker;
use \Workerman\WebServer;
use \GatewayWorker\Gateway;
use \GatewayWorker\BusinessWorker;
use \Workerman\Autoloader;

// �Զ�������
require_once __DIR__ . '/../../Workerman/Autoloader.php';
Autoloader::setRootPath(__DIR__);

// WebServer
$web = new WebServer("http://127.0.0.1:8088");
// WebServer��������
$web->count = 2;
// ����վ���Ŀ¼
$web->addRoot('', __DIR__.'/web');


// ��������ڸ�Ŀ¼������������runAll����
if(!defined('GLOBAL_START'))
{
    Worker::runAll();
}