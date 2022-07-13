package com.simple.asyncdemo.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.simple.asyncdemo.service.AsyncService;
import com.simple.asyncdemo.service.TestService;

/**
 * @author simple
 * @date 2021/6/17 22:36
 */
@RestController
@Slf4j
public class TestController {

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private TestService testService;

	@GetMapping("test")
	public void test() {
		System.out.println("开始");
		for (int i = 0; i < 10; i++) {
			Future<Map<String, Integer>> mapFuture = asyncService.test2("task" + i);
		}

		asyncService.test1();
		System.out.println("结束");
	}

	@GetMapping("test1")
	public void test1() {
		System.out.println("开始");
		for (int i = 0; i < 5; i++) {
			asyncService.test1();
		}

		System.out.println("结束");
	}

	@GetMapping("test2")
	public Map<String, Object> test2() throws ExecutionException, InterruptedException {
		System.out.println("开始");
		List<Map<String, Object>> params = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			HashMap<String, Object> param = new HashMap<>();
			param.put("appNbr", "S"+i);
			param.put("success", true);
			param.put("message", "成功");

			param.put("wait", 1000L);
			params.add(param);
		}
		for (int i = 0; i < 25; i++) {
			HashMap<String, Object> param = new HashMap<>();
			param.put("appNbr", "f"+i);
			param.put("success", false);
			param.put("message", "失败");

			param.put("wait", 4000L);
			params.add(param);
		}

		Map<String, Object> stringObjectMap = testService.doBatchJob(params);

		System.out.println("结束");
		return stringObjectMap;
	}

	@GetMapping("test3")
	public Map<String, Object> test3() throws ExecutionException, InterruptedException {
		System.out.println("开始");
		List<Map<String, Object>> params = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			HashMap<String, Object> param = new HashMap<>();
			param.put("appNbr", "S"+i);
			param.put("success", true);
			param.put("message", "成功");
			param.put("wait", 1000L);
			params.add(param);
		}
		for (int i = 0; i < 25; i++) {
			HashMap<String, Object> param = new HashMap<>();
			param.put("appNbr", "f"+i);
			param.put("success", false);
			param.put("message", "失败");
			param.put("wait", 4000L);
			params.add(param);
		}

		Map<String, Object> stringObjectMap = testService.doBatchJobNew(params);

		System.out.println("结束");
		return stringObjectMap;
	}

	@GetMapping("test4")
	public String test4(String appNbr) throws ExecutionException, InterruptedException {
		Thread.sleep(10000);
		log.info(Thread.currentThread().getName()+"处理请求");
		System.out.println("请求成功");
		return "success";
	}

}
