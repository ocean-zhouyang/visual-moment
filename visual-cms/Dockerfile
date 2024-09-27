# 指定创建镜像的基础镜像
FROM openjdk:11-jdk-alpine
# 设置工作目录 指的是容器中的目录而不是宿主机的目录
WORKDIR /app
# 将宿主机当前目录内容复制到容器的/app中
COPY . /app
# 设置容器启动后执行的命令
CMD ["java", "-jar", "/app/visual-cms.jar"]
