package com.simple.kafkatest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.simple.KafkaTestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaTestApplication.class)
class KafkaTestApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(11);
	}

	@Test
	void test() {
		System.out.println(11);
	}

}
