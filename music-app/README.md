## ⚙️ Docker 部署

1. 构建 Docker Image

```sh
docker build -t music .
```

2. 启动 Docker Container

```sh
docker run -d --name music -p 80:80 music
```

3. Docker Compose 启动

```sh
docker-compose up -d
```

music 地址为 `http://localhost`


## :computer: 配置开发环境

运行本项目

```shell
# 安装依赖
yarn install

# 创建本地环境变量
cp .env.example .env

# 运行（网页端）
yarn serve

# 运行（electron）
yarn electron:serve
```

本地运行 NeteaseCloudMusicApi
```shell
# 运行 API （默认 3000 端口）
yarn netease_api:run
```

