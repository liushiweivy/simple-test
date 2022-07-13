package com.simple.msgprovider.test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author simple
 * @date 2021/10/10 14:41
 */
@Component
public class ListenerDemo {

//	@RabbitListener(queues ={"ttl"})
//	public void footBallListener(Integer msg) {
//		try {
//			System.out.println("footBallListener消息");
//			System.out.println(msg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@RabbitListener(queues ={"deadQueue"})
//	public void deadQueueListener(String msg) {
//		try {
//			System.out.println("deadQueueListener消息");
//			System.out.println(msg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
