<?php
  $api = 'WmK0Y7Urol8ByhfDxZQUHyN5';
  $secret = 'xsftUxoPsRZiYUu2WgERjh7k7rhxg7Tl';
  $client = 'client_credentials';
  $openapi = 'https://openapi.baidu.com/oauth/2.0/token';
  $param = array('grant_type'=>$client,
                 'client_id'=>$api,
                 'client_secret'=>$secret
  );
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_POST, true);
  curl_setopt($ch, CURLOPT_TIMEOUT,100);
  curl_setopt($ch,CURLOPT_URL,$openapi);
  curl_setopt($ch,CURLOPT_POST,1);
  curl_setopt($ch,CURLOPT_HEADER,0);
  curl_setopt($ch,CURLOPT_RETURNTRANSFER,1);
  curl_setopt($ch,CURLOPT_POSTFIELDS,$param);
  //注意!!!
  curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
  curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
  $return = curl_exec($ch);
  curl_close($ch);
  //var_dump($return);
  $token = json_decode($return)->access_token;
  var_dump($token);
  echo $token;

 ?>
