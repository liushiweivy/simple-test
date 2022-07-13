package com.simple.msgconsumer.web;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;

/**
 * @author simple
 * @date 2021/5/15 17:09
 */
@Component
public class MsgConsumer {

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "spring.test.queue", durable = "true"),
		exchange = @Exchange(value = "spring.test.exchange",ignoreDeclarationExceptions = "true",type =
			ExchangeTypes.TOPIC),
		key = {"#.#"}
	))
	public void listener(String msg) {
		System.out.println("#");
		System.out.println(msg);
	}

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "test.add", durable = "true"),
		exchange = @Exchange(value = "test.ex",ignoreDeclarationExceptions = "true",type =
			ExchangeTypes.TOPIC),
		key = {"test.add"}
	))
	public void addlisentner(String msg) {
		System.out.println("add:");
		System.out.println(msg);
	}

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "test.update", durable = "true"),
		exchange = @Exchange(value = "test.ex",ignoreDeclarationExceptions = "true",type =
			ExchangeTypes.TOPIC),
		key = {"test.update"}
	))
	public void updateListener(String msg) {
		System.out.println("update:");
		System.out.println(msg);
	}

	@RabbitListener(bindings = @QueueBinding(
		value = @Queue(value = "test.obj", durable = "true"),
		exchange = @Exchange(value = "test.ex",ignoreDeclarationExceptions = "true",type =
			ExchangeTypes.TOPIC),
		key = {"test.obj"}
	))
	public void objListener(String msg) {
		System.out.println("obj:");
		User user = JSONObject.parseObject(msg, User.class);
		System.out.println("user:");
		System.out.println(user.getName());
		System.out.println(msg);
	}

	@RabbitListener(queues = "direct")
	public void directListener(String msg) {
		System.out.println(msg);
		System.out.println("2222");
	}

	@RabbitListener(queues =SimpleMQConfig.BASKETBALL_QUEUE_NAME)
	@RabbitHandler
	public void basketListener(String msg) {
		System.out.println(msg);
	}

	@RabbitListener(queues ={SimpleMQConfig.FOOTBALL_QUEUE_NAME})
	public void footBallListener(String msg) {
		System.out.println("足球订阅消息");
		System.out.println(msg);
	}

	@RabbitListener(queues ={SimpleMQConfig.BOOK_QUEUE_NAME})
	@RabbitHandler
	public void bookListener(String msg) {
		System.out.println(msg);
	}


}
