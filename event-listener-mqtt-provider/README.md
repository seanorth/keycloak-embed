# event-listener-mqtt-provider

##### Keycloak SPI 插件： 发布事件到MQTT服务  


下面的示例是管理员修改用户信息时的事件

* 主题: `KEYCLAOK/EVENT/ADMIN/DUBHE/SUCCESS/USER/UPDATE`  
* 数据: 


```
{
  "@class" : "cn.dubhe.keycloak.event.mqtt.AdminEventMessage",
  "time" : 1652866433000,
  "realmId" : "dubhe",
  "authDetails" : {
    "realmId" : "master",
    "clientId" : "debb631d-6ad6-407b-893a-60e1c76509bc",
    "userId" : "767b1c0c-fe27-4d29-bae3-78ffb1d54ec0",
    "ipAddress" : "0:0:0:0:0:0:0:1"
  },
  "resourceType" : "USER",
  "operationType" : "UPDATE",
  "resourcePath" : "users/446c2235-a335-4d4e-ae3b-b2373d35c867",
 "representation" : "...",
  "resourceTypeAsString" : "USER"
} 
```

事件发布主题格式
* admin 事件: `KEYCLAOK/EVENT/ADMIN/<REALM>/<RESULT>/<RESOURCE_TYPE>/<OPERATION>`
* client 事件: `KEYCLAOK/EVENT/CLIENT/<REALM>/<RESULT>/<CLIENT>/<EVENT_TYPE>`

MQTT消费者可以有选择的订阅事件
* 所有事件: `KEYCLAOK/EVENT/#`
* 指定realm的事件: `KEYCLAOK/EVENT/*/MYREALM/#`
* 指定realm的错误的事件: `KEYCLAOK/EVENT/*/MYREALM/ERROR/#`
* 指定realm，clientid的用户事件: `KEYCLAOK/EVENT/*/MY-REALM/*/MY-CLIENT/USER`


## Keycloak设置
1. 启动项目`springboot-embedded-server`
2. 管理员登录Keycloak界面，在 `Manage > Events > Config > Events Config > Event Listeners` 中添加**dubhe-event-listener-mqtt**  
 





