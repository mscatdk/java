# simple.avro

Show how to send avro messages via. Confluent Kafka

## Confluent Kafka Configuration

````Bash
git clone https://github.com/confluentinc/examples
cd examples/

git checkout 5.3.1-post
cd cp-all-in-one

# Start the Confluent containers
docker-compose up -d --build
docker-compose ps

# Create the topic via. the Control Center on http://10.11.12.31:9021
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
java -jar target/confluent.avro-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] put

# Retrieve messages
java -jar target/confluent.avro-1.0-SNAPSHOT-jar-with-dependencies.jar -h [host:port] -t [topic] get
````