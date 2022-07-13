package com.simple.service;

import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.simple.dto.LogDto;
import com.simple.kafka.KafkaConsumer;

/**
 * @author simple
 * @date 2021/8/1 19:34
 */
@Service
@Slf4j
public class TaskService {

	@Autowired
	private KafkaConsumer kafkaConsumer;


}
