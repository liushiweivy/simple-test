package com.simple.asyncdemo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author simple
 * @date 2021/6/17 22:33
 */
@Service
@Slf4j
public class AsyncService {

	@Autowired
	private RestTemplate restTemplate;

	@Async
	public void test1() {
		log.info(Thread.currentThread().getName());
		System.out.println("test2");
		ExecutorService es2 = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			es2.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "正在执行任务");
				}
			});
		}

		System.out.println("=================");
//		test3();
	}

	@Async
	public void test3() {
		log.info(Thread.currentThread().getName());
		System.out.println("test3");
	}

	@Async
	public Future<Map<String, Integer>> test2(String name) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put(name, 1);
		return new AsyncResult(map);
	}

	@Async
	public Future<Map<String, Object>> testDoBatch(Map<String, Object> params) throws InterruptedException {
		String appNbr = (String) params.get("appNbr");
		Long wait = (Long) params.get("wait");
		boolean success = (Boolean) params.get("success");
		log.info("执行开始：当前编号为appNbr:{},等待时长wait:{}", appNbr, wait);
		Thread.sleep(wait);
		send();
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", success);
		resultMap.put("messsge", "你好");
		log.info("执行结束：当前编号为appNbr:{},等待时长wait:{}", appNbr, wait);
		return new AsyncResult(resultMap);
	}

	@HystrixCommand(fallbackMethod = "getMsgFallback", commandProperties =
		{
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900")
		})
	public void send() throws InterruptedException {
		System.out.println(1 / 0);
		String forObject = restTemplate.getForObject("http://localhost:8089/test4",
			String.class);
		System.out.println(forObject);
	}

	public void getMsgFallback() {
		System.out.println("111111");
	}
}
