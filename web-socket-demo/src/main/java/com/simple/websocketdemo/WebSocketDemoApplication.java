package com.simple.websocketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.core.AbstractMessageSendingTemplate;

@SpringBootApplication
public class WebSocketDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketDemoApplication.class, args);
	}

	@Bean
	public AbstractMessageSendingTemplate getAbstractMessageSendingTemplate() {
		return new AbstractMessageSendingTemplate() {
			@Override
			protected void doSend(Object o, Message message) {
			}
		};
	}
}
