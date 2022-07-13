package com.example.estest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsTestApplication.class, args);
	}

}
