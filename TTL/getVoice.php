<?php
$text = '你好开发者';
$api = 'http://tsn.baidu.com/text2audio';
$token = '24.7ea417646a534ab2df472a96d581a6d1.2592000.1474114327.282335-3157010';
$uid = '00-00-00-00-00-00-00-E0';
$l = 'zh';
$text = urlencode($text);
//$api = urlencode($api);
//$token = urlencode($token);
//$l = urlencode($l);
//$uid = urlencode($uid);
//echo urlencode($text);
$data = array('tex'=>urlencode($text),
              'lan'=>urlencode($l),
              'cuid'=>urlencode($uid),
              'ctp'=>1,
              'tok'=>urlencode($token)
);

$c = curl_init();
curl_setopt($c, CURLOPT_POST, true);
//curl_setopt($c, CURLOPT_TIMEOUT,100);
curl_setopt($c,CURLOPT_URL,$api);
curl_setopt($c,CURLOPT_POST,1);
curl_setopt($c,CURLOPT_HEADER,1);
curl_setopt($c,CURLOPT_RETURNTRANSFER,1);
curl_setopt($c,CURLOPT_POSTFIELDS,$data);
//注意!!!
//curl_setopt($c, CURLOPT_SSL_VERIFYHOST, false);
//curl_setopt($c, CURLOPT_SSL_VERIFYPEER, false);
$voice = curl_exec($c);
curl_close($c);
//echo $voice;
var_dump($voice);
 ?>
