package com.simple.asyncdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCircuitBreaker
public class AsyncDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncDemoApplication.class, args);
	}

}
