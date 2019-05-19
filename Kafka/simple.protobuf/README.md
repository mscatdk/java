# simple.protobuf

Show how to send protocol buffer messages via. Kafka

## Kafka Configuration

ZooKeeper and Kafka must be running and a Kafka topic must exists

````Bash
# Start ZooKeeper
bin/zookeeper-server-start.sh config/zookeeper.properties

# Start Kafka Server
bin/kafka-server-start.sh config/server.properties

# Create Topic
bin/kafka-topics.sh --create --bootstrap-server [host:port] --replication-factor 1 --partitions 1 --topic [topic]
````

## Build

````Bash
mvn clean install
````

## Generate Java Classes

````Bash
# URL: https://developers.google.com/protocol-buffers/docs/downloads.html
protoc -I src/main/proto --java_out src/main/java src/main/proto/customer.proto
````

## Run

````Bash
# Send default message
java -jar target/simple.protobuf-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] put

# Send user defined message
java -jar target/simple.protobuf-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] put -d "{ \"id\": \"t\", \"name\": \"de\", \"address\": \"jkjk\", \"age\": 32 }"

# Retrieve messages
java -jar target/simple.protobuf-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] get
````