package com.mscatdk.kafka.command;

import java.io.StringReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.beust.jcommander.Parameter;
import com.googlecode.protobuf.format.JsonFormat;
import com.mscatdk.kafka.model.CustomerProtos.Customer;

public class PutCommand implements Command {
	
	@Parameter(names = { "-d", "--data"}, description = "Customer data")
	private String data;

	@Override
	public void exec(Properties properties, String topic) throws Exception {
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.ByteArraySerializer");
        
        Customer customer;
        
        if (data == null) {
        	customer = Customer.newBuilder().setId("10").setName("test").setAddress("Avenue 1").setAge(23).build();
        } else {
        	Customer.Builder build = Customer.newBuilder();
        	JsonFormat.merge(new StringReader(data), build);
        	customer = build.build();
        }
        
        try (KafkaProducer<String, byte[]> kafkaProducer = new KafkaProducer<>(properties)) {
	        kafkaProducer.send(new ProducerRecord<String, byte[]>(topic, customer.toByteArray())).get();       
        }
	}

}
