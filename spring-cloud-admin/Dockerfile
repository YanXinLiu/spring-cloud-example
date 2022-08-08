FROM openjdk:11

MAINTAINER liuyanxin@jkstack.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /spring-cloud-admin

WORKDIR /spring-cloud-admin

EXPOSE 8081

ADD ./build/libs/spring-cloud-admin-1.0-RELEASES.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar spring-cloud-admin-1.0-RELEASES.jar