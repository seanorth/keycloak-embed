# mail-sender-mqtt-provider

#### Keycloak SPI 插件： 发送邮件到MQTT服务

发送到MQTT的主题格式如下:
* `KEYCLAOK/MAIL/<REALM>`


下面是发送示例：

* MQTT主题: `KEYCLAOK/MAIL/MYRELEAM`  
* 数据: 


```
{
  "mailToList" : [ "zhangs@qq.com" ],
  "templateCode" : "KEYCLOAK.CREDENTIAL_RESET",
  "locale" : "zh_CN",
  "paramters" : {
    "firstName" : "张",
    "lastName" : "三三",
    "htmlBody" : "",
    "subject" : "",
    "textBody" : ""
  }
}
```

MQTT消费端可以按需要订阅:
* 所有邮件: `KEYCLAOK/MAIL/#`
* 指定realm的所有邮件: `KEYCLAOK/MAIL/MYRELEAM/#`

`templateCode` 是邮件模板名称, 在邮件发送服务定义模板。阿里的[邮件推送](https://help.aliyun.com/document_detail/29416.html)服务就支持定义邮件模板

## 帮助

在Keycloak中，手工发送邮件步骤：

1. 启动 `springboot-embedded-server` 项目
2. 登录，realm选择`dubhe` 
3. 进入`Managers -> Users -> View all users` , 选择一个用户
4. 进入`Credentials -> Credential Reset`, 选择一个`Reset Actions`
5. 点击`Send mail` 按钮

