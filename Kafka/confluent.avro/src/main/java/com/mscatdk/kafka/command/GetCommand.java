package com.mscatdk.kafka.command;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mscatdk.kafka.model.Customer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;


public class GetCommand implements Command {
	
	private static final Logger console = LoggerFactory.getLogger("console");

	@Override
	public void exec(Properties properties, String topic) throws Exception {
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.put("schema.registry.url", "http://127.0.0.1:8081");
        properties.put("specific.avro.reader", "true");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-1");
		
		try (KafkaConsumer<String, Customer> consumer = new KafkaConsumer<>(properties)) {
			consumer.subscribe(Arrays.asList(topic)); 
			ConsumerRecords<String, Customer> records = consumer.poll(Duration.ofMinutes(1));
			
			for (ConsumerRecord<String, Customer> record : records) {
				
				Customer customer = record.value();
				
				console.info("Name: {}, Adress: {}, Age: {}", customer.getName(), customer.getAddress(), customer.getAge());
				
			}
			
		}
		
	}

}
