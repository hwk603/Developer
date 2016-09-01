<?php
//appId
$appID = "97487200658c4846a5055b117746bd80";
//authKey
$authKey = "a9a26d1cdc5d47febc5fed38dc2e3345";
//待验证手机号
$pn = $_POST["number"];
$version = "1.0";
$url = "https://api.ciaapp.cn/{$version}/agent";
$headerList = array("Host:api.ciaapp.cn",
				"Accept:application/json",
				"Content-Type:application/json;charset=utf-8",
				"appId:{$appID}",
				"authKey:{$authKey}",
				"pn:{$pn}"
			);
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_TIMEOUT,100);
curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headerList);
curl_setopt($ch, CURLOPT_POSTFIELDS, $params);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
$returnData = curl_exec($ch);
$return = json_decode($returnData);
$returnCode = $return->authCode;
$code = substr($returnCode,-4);
curl_close($ch);
echo $code;
?>
