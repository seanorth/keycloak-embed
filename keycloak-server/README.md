# keycloak-server

Keycloak服务

#### 运行服务

下载[keycloak服务端](https://www.keycloak.org/downloads.html)，最好下载`Distribution powered by Quarkus`版本

编辑`${KEYCLOAK_HOME}\conf\keycloak.conf`文件，修改数据库的配置及服务端口号

然后`run-keycloak.bat`启动服务，打开游览器，访问地址：http://localhost:9000/

#### 安装SPI插件

将打包后的provider拷贝到`${KEYCLOAK_HOME}\providers`下，看起来项这样的：

```
cn.dubhe.keycloak.event-listener-mqtt-provider-17.0.1.jar
cn.dubhe.keycloak.keycloak-extensions-17.0.1.jar
cn.dubhe.keycloak.mail-sender-mqtt-provider-17.0.1.jar
cn.dubhe.keycloak.mqtt-publisher-provider-17.0.1.jar
cn.dubhe.keycloak.otp-mail-provider-17.0.1.jar
cn.dubhe.keycloak.otp-sms-provider-17.0.1.jar
cn.dubhe.keycloak.theme-provider-17.0.1.jar
org.eclipse.paho.client.mqttv3-1.2.5.jar
spring-security-crypto-5.6.2.jar
```

***注意***：插件的依赖包也需要拷贝到这里

插件配置，编辑`${KEYCLOAK_HOME}\conf\keycloak.conf`文件，添加

```
...
spi-email-sender-provider=dubhe-mqtt
spi-mqtt-publisher-provider=dubhe-logger
...

```

打开cmd客户端，进入`${KEYCLOAK_HOME}\bin`目录下，运行命令：

```
kc.bat build
```

启动keycloak服务即可

#### 参考

- [Configuring providers](https://www.keycloak.org/server/configuration-provider)


