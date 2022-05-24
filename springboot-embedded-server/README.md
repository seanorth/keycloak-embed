# springboot-embedder-server

基于springboot的，内嵌式的Keycloak服务

#### 打包

``` 
mvn clean package spring-boot:repackage
```

#### 本地运行项目

编辑`src/resources/application-dev.yml`文件，修改配置：

```
spring:
...
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/keycloak-embed?characterEncoding=UTF-8&useSSL=true
    username: root
    password: root

...
s8d.keycloak:
  custom:
    adminUser:
      username: admin
      password: admin
      create-admin-user-enabled: true

    migration:
      import-enabled: true
      import-location: classpath:springseeds-realm.json
...
```      

可以在IDE中启动项目，也可以在命令行使用maven命令启动：

``` 
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

说明：
1. 项目第一次启动配置上面信息，成功后就可以注销掉
2. 在项目启动过程中，会自动创建表结构，初始化超级用户，导入springseeds等等

打开游览器，访问地址：http://localhost:9000/auth ，输入超级用户名及密码： admin/admin

#### Docker中运行项目

Step-1: 在生成镜像的过程中，需要docker环境。如果是windows系统，启动`Docker Desktop` 

Step-2: 在`Docker Desktop`中安装mysql，如下命令：
```
docker pull mysql:8.0.29
docker run -p 13306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:8.0.29
```

启动mysql服务，使用客户端连接服务（端口号是：13306），创建数据库和用户，脚本如下：
```
CREATE DATABASE IF NOT EXISTS keycloak_embed DEFAULT CHARSET utf8;
create user 'kkuser'@'%' identified by 'kkuser';
grant create,alter,drop,select,insert,update,delete on keycloak.* to kkuser@'%';
flush privileges; 
```


Step-3: 打包，生成镜像文件，运行如下命令：

```
mvn clean package spring-boot:repackage
mvn dockerfile:build
```

成功运行后，在`Docker Desktop`中的images功能中可以查看到`cn.springseed.keycloak/springboot-embedded-server:17.0.1`记录

Step-3: 运行镜像
```
docker run -p 19000:9000 --name springboot-embedded-server -d cn.springseed.keycloak/springboot-embedded-server:17.0.1
```




