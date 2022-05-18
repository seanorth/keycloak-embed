# otp-sms-provider

#### Keycloak SPI 插件： 动态口令短信实现

[OTP](https://baike.baidu.com/item/%E4%B8%80%E6%AC%A1%E6%80%A7%E5%AF%86%E7%A0%81/10650649?fromtitle=OTP&fromid=1406545&fr=aladdin)一次性密码（One Time Password，简称OTP），又称“一次性口令”，是指只能使用一次的密码。`keycloak` 支持 `FreeOTP` 登录，详细信息请参考:TODO

此插件使用短信方式实现动态口令。发送到MQTT的主题格式如下:
* `KEYCLAOK/SMS/OTP/<REALM>`

下面是发送示例：

* MQTT主题: `KEYCLAOK/SMS/OTP/MYRELEAM`  
* 数据: 


```
{
  "mailToList" : [ "lis@qq.com" ],
  "templateCode" : "KEYCLOAK.OTP-MAIL",
  "locale" : "zh_CN",
  "paramters" : {
    "code" : "333490",
    "ttl" : "5"
  }
}
```

MQTT消费端可以按需要订阅:
* 所有邮件: `KEYCLAOK/SMS/OTP/#`
* 指定realm的所有邮件: `KEYCLAOK/SMS/OTP/MYRELEAM/#`

`templateCode` 是短信模板名称, 在邮件发送服务定义模板。阿里的[短信服务](https://help.aliyun.com/document_detail/44333.html)就支持定义邮件模板

## 帮助
用户操作流程如下：
1. 管理员在keycloak系统中定义每个用户的手机号
2. 用户使用用户名与密码登录，成功后，出现`验证码`对话框，系统要求用户输入验证码
3. 用户在手机短信中查看验证码
4. 将验证码输入到对话框，点击按钮登录即可

**如果没有配置手机号，用户将无法登录**
