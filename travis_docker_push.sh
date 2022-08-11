#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# gateway
cd ./spring-cloud-gateway
docker build -t harbor.jkservice.org/demo/spring-cloud-gateway:v1 .
cd ..

# admin
cd ./spring-cloud-admin
docker build -t harbor.jkservice.org/demo/spring-cloud-admin:v1 .
cd ..

docker images
docker push harbor.jkservice.org/demo/spring-cloud-gateway:v1
docker push harbor.jkservice.org/demo/spring-cloud-admin:v1
