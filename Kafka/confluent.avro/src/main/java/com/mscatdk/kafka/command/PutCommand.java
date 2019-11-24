package com.mscatdk.kafka.command;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.beust.jcommander.Parameter;
import com.mscatdk.kafka.model.Customer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class PutCommand implements Command {
	
	@Parameter(names = { "-d", "--data"}, description = "Customer data")
	private String data;

	@Override
	public void exec(Properties properties, String topic) throws Exception {
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", KafkaAvroSerializer.class.getName());
        
        Customer customer;
        
        if (data == null) {
        	customer = Customer.newBuilder().setId("10").setName("test").setAddress("Avenue 1").setAge(23).build();
        } else {
        	customer = parseJson();
        }
        
        try (KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<>(properties)) {
	        kafkaProducer.send(new ProducerRecord<String, Customer>(topic, customer)).get();       
        }
	}

	private Customer parseJson() throws IOException {
		Customer customer;
		InputStream input = new ByteArrayInputStream(data.getBytes());
		DataInputStream din = new DataInputStream(input);
		Decoder decoder = DecoderFactory.get().jsonDecoder(Customer.SCHEMA$, din);
		SpecificDatumReader<Customer> reader = new SpecificDatumReader<>(Customer.SCHEMA$);
		customer = reader.read(null, decoder);
		return customer;
	}

}
