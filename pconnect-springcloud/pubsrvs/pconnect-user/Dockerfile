FROM java

MAINTAINER 金飞 28206254@qq.com

WORKDIR /ROOT

RUN echo "拷贝jar包..."
COPY ./target/pconnect-user.jar /
RUN echo "拷贝jar包完成"

EXPOSE 8964

ENTRYPOINT ["java","-jar","/ROOT/pconnect-user.jar"]