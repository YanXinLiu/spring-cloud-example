FROM openjdk:11

MAINTAINER liuyanxin@jkstack.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /spring-cloud-gateway

WORKDIR /spring-cloud-gateway

EXPOSE 30001

ADD ./build/libs/spring-cloud-gateway-1.0-RELEASES.jar ./

CMD java -Djava.security.egd=file:/dev/./urandom -jar spring-cloud-gateway-1.0-RELEASES.jar