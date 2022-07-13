package com.simple.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.simple.dto.LogDto;
import com.simple.kafka.KafkaProducer;

/**
 * @author simple
 * @date 2021/7/18 15:36
 */
@RestController
public class KafkaController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@PostMapping("sendMsg")
	public String sendMsg(@RequestBody Object object) {

		kafkaProducer.send(object);
		return "success";
	}


	@PostMapping("sendLog")
	public String sendMsg(@RequestBody LogDto object) {
		kafkaProducer.sendLog(object);
		return "success";
	}
}
