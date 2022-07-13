package com.simple.kafkatest;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.Test;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import com.alibaba.fastjson.JSON;

public class CustomProducer {

	@Test
	public void test1() throws ExecutionException, InterruptedException {
		Properties props = new Properties();//kafka集群，
		//broker - list
		props.put("bootstrap.servers", "127.0.0.1:9092");
//		props.put("bootstrap.servers", "42.192.83.41:9092");
		props.put("acks", "all");
		//重试次数
		props.put("retries", 1);
		// 批次大小
		props.put("batch.size", 16384);
		// 等待时间
		props.put("linger.ms", 1);
		//RecordAccumulator缓冲区大小
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 520; i++) {
			Future<RecordMetadata> metadataFuture = producer.send(new ProducerRecord<String, String>("test3",
				Integer.toString(i), Integer
				.toString(i)));
			RecordMetadata recordMetadata = metadataFuture.get();
			System.out.println(recordMetadata.topic());
			System.out.println(recordMetadata.offset());
			System.out.println(recordMetadata.partition());
			System.out.println(JSON.toJSONString(recordMetadata));
		}
		producer.close();
	}

}