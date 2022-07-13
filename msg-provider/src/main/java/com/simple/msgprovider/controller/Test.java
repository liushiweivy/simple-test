package com.simple.msgprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.simple.msgprovider.service.TestService;

/**
 * @author simple
 * @date 2021/10/10 13:18
 */
@RestController
public class Test {

	@Autowired
	TestService testService;

	@PostMapping("test")
	public String test() {
		System.out.println(1111222);
		testService.test();
		return "11112223333444555";
	}
}
