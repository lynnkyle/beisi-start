# 依赖的基础镜像
FROM nginx

# 复制代码到镜像
WORKDIR /usr/share/nginx/html/beisi
USER root

COPY ./docker/nginx.conf /etc/nginx/conf.d/default.conf

COPY ./beisi/dist/  /usr/share/nginx/html/beisi

# 占用的端口号
EXPOSE 80

# 运行镜像默认命令
CMD ["nginx", "-g", "daemon off;"]