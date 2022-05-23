/** 创建数据库及用户脚本 */
CREATE DATABASE IF NOT EXISTS keycloak DEFAULT CHARSET utf8;
create user 'kkuser'@'%' identified by 'kkuser';
grant create,alter,drop,select,insert,update,delete on keycloak.* to kkuser@'%';
flush privileges; 