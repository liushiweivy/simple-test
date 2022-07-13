package com.simple.asyncdemo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

/**
 * @author simple
 * @date 2021/6/19 7:56
 */
@Slf4j
public class MyTaskThread implements Callable<Map<String, Object>> {

	private Map<String, Object> params;

	public MyTaskThread(Map<String, Object>params) {
		this.params=params;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		String appNbr = (String) params.get("appNbr");
		String message = (String) params.get("message");
		Long wait = (Long) params.get("wait");
		boolean success = (Boolean) params.get("success");
		log.info("执行开始：当前编号为appNbr:{},等待时长wait:{}", appNbr, wait);
		Thread.sleep(wait);
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", success);
		resultMap.put("message", message);
		log.info("执行结束：当前编号为appNbr:{},等待时长wait:{}", appNbr, wait);
		return resultMap;
	}
}
