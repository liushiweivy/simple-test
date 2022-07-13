package com.simple.msgprovider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.simple.msgprovider.config.RabbitMQTemplateConfig;
import com.simple.msgprovider.service.TestService;
import com.simple.msgprovider.test.User;

/**
 * @author simple
 * @date 2021/4/28 23:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MsgProviderApplication.class})
public class ProviderTest {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private TestService testService;

	@Test
	public void sendMsg() {
//		System.out.println(1111);
//		rabbitTemplate.convertAndSend("spring.test.exchange", "a.b", "123");
//		rabbitTemplate.convertAndSend("test.ex", "test.add", "add123");
//		rabbitTemplate.convertAndSend("test.ex", "test.update", "update22223");
		User user = new User();
		user.setId("112334");
		user.setName("刘是为");
		rabbitTemplate.convertAndSend("test.ex", "test.obj", user);
	}

	@Test
	public void sendDirectMsg() {
		System.out.println(1111);
		rabbitTemplate.convertAndSend("direct", "123");
	}

	@Test
	public void sendSportMsg() {
		System.out.println("send：");
		rabbitTemplate.convertAndSend(RabbitMQTemplateConfig.EXCHANGE_TEST,"topic.sport.news", "篮球比赛");
	}

	@Test
	public void sendTTLMsg() {
		for (int i = 0; i < 10; i++) {
			rabbitTemplate.convertAndSend("ttl", "ttl.add", i);
		}
	}

	@Test
	public void sendTTLDeadMsg() {
		for (int i = 0; i < 10; i++) {
			rabbitTemplate.convertAndSend("ttl", "ttl.add", "123");
		}
	}

	@Test
	public void setTestService() {
		testService.test();
	}

}
