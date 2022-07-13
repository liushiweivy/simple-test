package com.simple.msgprovider.test;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author simple
 * @date 2021/4/28 23:42
 */
@Component
public class ProviderTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMsg() {
		rabbitTemplate.convertAndSend("test.ex", "test.add", "123");
	}



}
