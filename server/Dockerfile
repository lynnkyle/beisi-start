# 运行时镜像（不需要 maven）
FROM openjdk:8-jre-alpine

# 工作目录
WORKDIR /app

# 拷贝本地已编译好的 jar
COPY ./beisi-backend-0.0.1-SNAPSHOT.jar beisi-backend-0.0.1-SNAPSHOT.jar

# 暴露端口（可选，便于文档和调试）
EXPOSE 8080

# 启动命令
CMD ["java","-jar","/app/beisi-backend-0.0.1-SNAPSHOT.jar"]