package com.simple.kafkatest;


import java.util.Arrays;
import java.util.Properties;
import org.junit.Test;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

;

public class CustomConsumer {

	@Test
	public void test1() throws InterruptedException {
		Properties props = new Properties();

		props.put("bootstrap.servers", "localhost:9092");
//		props.put("bootstrap.servers", "42.192.83.41:9092");

		props.put("group.id", "test");

		props.put("enable.auto.commit", "true");

		props.put("auto.commit.interval.ms", "1000");

		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new
			KafkaConsumer<>(props);

		consumer.subscribe(Arrays.asList("test"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			Thread.sleep(3000);
			System.out.println("-----------");
			System.out.println(records.count());
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset  =  %d,  key  =  %s,  value = %s%n", record.offset(), record.key(), record.value());
			}

		}
	}
}