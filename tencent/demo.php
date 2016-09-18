<?php
error_reporting(E_ALL ^ E_NOTICE);
require_once './src/QcloudApi/QcloudApi.php';
$config = array('SecretId'       => 'AKIDy1WpYBF3qPjTKXo4aea8qpTSTw3emUqR',
                'SecretKey'      => 'UEhStd2ge5OKU4qzb590pQ2Qo3Gzprtw',
                'RequestMethod'  => 'POST',
                'DefaultRegion'  => 'gz');
$wenzhi = QcloudApi::load(QcloudApi::MODULE_WENZHI, $config);
$message = $_POST["text"];
//$message = "《爱情公寓》系列曾是“假期金牌节目”！没看到《爱情公寓》就如同没有放假一样，已经大红大紫的四季让人也无限期待第五部的开拍。可是天意弄人，就在眼看《爱5》要来之际，王牌曾小贤的扮演者陈赫却发生了离婚，出轨等负面新闻，所以《爱5》的拍摄以及人员一直是人们的关注点。";
$package = array("content"=>$message);
$a = $wenzhi->TextSentiment($package);
if ($a === false) {
    $error = $wenzhi->getError();
    echo "Error code:" . $error->getCode() . ".\n";
    echo "message:" . $error->getMessage() . ".\n";
    echo "ext:" . var_export($error->getExt(), true) . ".\n";
} else {
    $values = json_encode($a);
    echo $values;
}
