### Kafka demo

- Start kafka

```
docker-compose up kafka-cluster
```

- Create topic

```
docker exec -it $containerId bin/bash
kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 3 --topic test
```

- Send message

```
kafka-console-producer --broker-list localhost:9092 --topic test

```

- Read message

```
kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
```
