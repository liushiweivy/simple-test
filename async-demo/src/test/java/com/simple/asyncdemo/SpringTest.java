package com.simple.asyncdemo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.simple.asyncdemo.service.AsyncService;

/**
 * @author simple
 * @date 2021/6/17 22:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringTest {

	@Autowired
	private AsyncService asyncService;

	@org.junit.Test
	public void test2() {
		System.out.println("开始");
		asyncService.test1();
		System.out.println("结束");
	}
}
