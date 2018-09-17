FROM java

MAINTAINER 金飞 28206254@qq.com

WORKDIR /ROOT

RUN echo "拷贝jar包..."
COPY ./target/analysis-spc.jar /
RUN echo "拷贝jar包完成"

EXPOSE 8972

ENTRYPOINT ["java","-jar","/ROOT/analysis-spc.jar"]