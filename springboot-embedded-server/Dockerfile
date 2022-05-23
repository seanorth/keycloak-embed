# syntax=docker/dockerfile:1

FROM openjdk:11
WORKDIR /keycloak
ADD target/cn.springseed.keycloak.springboot-embedded-server-17.0.1.jar keycloak-embedded-server.jar
ENTRYPOINT ["java","-Dspring.profiles.active=pro","-Djava.security.egd=file:/dev/./urandom","-jar","keycloak-embedded-server.jar"]
EXPOSE 9000