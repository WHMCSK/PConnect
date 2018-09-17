#!/bin/bash

echo "编译、打包、构建docker image.."
../pconnect-springcloud/mvnw install

echo "启动微服务项目.."
cd ..
docker-compose up

