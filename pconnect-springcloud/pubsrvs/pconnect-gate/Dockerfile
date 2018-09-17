FROM java

MAINTAINER 金飞 28206254@qq.com

WORKDIR /ROOT

RUN echo "拷贝jar包..."
COPY ./target/pconnect-gate.jar /
RUN echo "拷贝jar包完成"

EXPOSE 8965

ENTRYPOINT ["java","-jar","/ROOT/pconnect-gate.jar"]