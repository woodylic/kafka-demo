# kafka-demo

kotlin-springboot-kafka hello-world project.

## 开发环境

### 启动开发环境

```sh
docker-compose up -d
```

zookeeper: localhost:2181

kafka: localhost:9092

### 停止并清理开发环境

```sh
docker-compose down
```

## producer

TODO:

需要处理往QuartzJob注入KafkaTemplate，可参看：

https://stackoverflow.com/questions/6990767/inject-bean-reference-into-a-quartz-job-in-spring