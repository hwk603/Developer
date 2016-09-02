### 说明

---

在已有的火车票查询工具 `iquery` 的基础上，利用 `BosonNLP` 提供的中文语义分析接口，添加自然语言交互功能。

`iquery` 使用 `Python3` 编写，提供基于命令行各种信息查询。

`BosonNLP` 即玻森中文语义开放平台，提供中文自然语言分析云服务。

### 前置要求

---

```
$ sudo pip3 install iquery
$ sudo pip3 install bosonnlp
```

另外需要注册玻森中文语义开放平台，在后台获取 API 密钥，替换到 `npl.py` 文件的第 13 行。

之后执行：

```
python3 npl.py 明天从成都到北京的火车票
```