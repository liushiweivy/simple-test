package com.simple.asyncdemo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.threads.TaskThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * @author simple
 * @date 2021/6/19 7:53
 */
@Service
@Slf4j
public class TestService {

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private TaskExecutor taskExecutor;

	public Map<String, Object> doBatchJob(List<Map<String, Object>>params) throws InterruptedException, ExecutionException {
//		ExecutorService pool = Executors.newFixedThreadPool(50);
		StopWatch sw = new StopWatch();

		ExecutorCompletionService<Map<String, Object>> completionService = new ExecutorCompletionService<>(taskExecutor);
		for (Map<String, Object> param : params) {
			completionService.submit(new MyTaskThread(param));
		}
		boolean successAll=true;
		String messageAll="";
		for (Map<String, Object> param : params) {
			String appNbr = (String) param.get("appNbr");
			sw.start();
			Map<String, Object> map = completionService.take().get();
			boolean success = (Boolean) map.get("success");
			String message = (String) map.get("message");
			if (!success) {
				messageAll+=appNbr+message+" ";
			}
			sw.stop();
		}
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", successAll);
		resultMap.put("message", messageAll);
		log.info(sw.prettyPrint());
		return resultMap;
	}

	public Map<String, Object> doBatchJobNew(List<Map<String, Object>>params) throws InterruptedException,
		ExecutionException {
		StopWatch sw = new StopWatch();
		HashMap<String, Future<Map<String, Object>>> futureMap = new HashMap<>();
		for (Map<String, Object> param : params) {
			String appNbr = (String) param.get("appNbr");
			Future<Map<String, Object>> mapFuture = asyncService.testDoBatch(param);
			futureMap.put(appNbr, mapFuture);
		}
		boolean successAll=true;
		String messageAll="";
		for (Map.Entry<String, Future<Map<String, Object>>> entry : futureMap.entrySet()) {
			sw.start(entry.getKey());
			Future<Map<String, Object>> value = entry.getValue();
			Map<String, Object> res = value.get();
			boolean success = (Boolean) res.get("success");
			String message = (String) res.get("message");
			messageAll+=message;
			sw.stop();
		}
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", successAll);
		resultMap.put("messsge", messageAll);
		log.info(sw.prettyPrint());
		return resultMap;
	}
	}
