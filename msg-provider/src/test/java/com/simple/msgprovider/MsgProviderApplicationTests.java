package com.simple.msgprovider;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.simple.msgprovider.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestService.class})
public class MsgProviderApplicationTests {

	@Autowired
	private TestService testService;

	@Test
	public void contextLoads() {
		testService.test();
	}

}
