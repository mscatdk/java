package com.mscatdk.kafka.command;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mscatdk.kafka.model.Customer;


public class GetCommand implements Command {
	
	private static final Logger console = LoggerFactory.getLogger("console");

	@Override
	public void exec(Properties properties, String topic) throws Exception {
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-1");
		
		try (KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(properties)) {
			consumer.subscribe(Arrays.asList(topic)); 
			ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMinutes(1));
			
			for (ConsumerRecord<String, byte[]> record : records) {
				
				ByteBuffer bb = getByteBuffer(record);
				Customer customer = Customer.fromByteBuffer(bb);
				
				console.info("Name: {}, Adress: {}, Age: {}", customer.getName(), customer.getAddress(), customer.getAge());
				
			}
			
		}
		
	}

	private ByteBuffer getByteBuffer(ConsumerRecord<String, byte[]> record) {
		byte[] payload = record.value();
		ByteBuffer bb = ByteBuffer.allocate(payload.length);
		bb.put(record.value());
		bb.rewind();
		return bb;
	}

}
