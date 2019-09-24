# simple.avro

Show how to send avro messages via. Kafka

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
# URL: http://repo1.maven.org/maven2/org/apache/avro/avro-tools/
java -jar ./avro-tools-1.8.2.jar compile schema src/main/avro/customer.avro src/main/java
````

## Run

````Bash
# Send default message
java -jar target/simple.avro-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] put

# Send user defined message
java -jar target/simple.avro-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] put -d "{ \"id\": \"t\", \"name\": \"de\", \"address\": \"jkjk\", \"age\": {\"int\": 32} }"

# Retrieve messages
java -jar target/simple.avro-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] get
````