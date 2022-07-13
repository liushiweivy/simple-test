package com.example.estest.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.estest.config.anno.SystemControllerLog;
import com.example.estest.entity.ErrMessage;
import com.example.estest.entity.TestBean;
import com.example.estest.entity.Bank;
import com.example.estest.entity.EsQueryParams;
import com.example.estest.service.BankService;
import com.google.common.collect.Lists;


/**
 * @author simple
 * @date 2021/6/17 22:36
 */
@RestController
@Slf4j
public class TestController {

	@Autowired
	private BankService bankService;

	@PostMapping("queryList")
	public Map<String, Object> queryList(@RequestBody EsQueryParams esQueryParams) {
		return bankService.queryList(esQueryParams);
	}


	@PostMapping("addBank")
	public boolean addBank(@RequestBody Bank bank) {
		return bankService.addBank(bank);

	}

	@PostMapping("addObject")
	public boolean addObject(String index,@RequestBody Map<String, String> jsonMap) {
		return bankService.addObject(index,jsonMap);
	}

	@PostMapping("updateBank")
	public boolean updateBank(@RequestBody Bank bank) {
		return bankService.updateBank(bank);

	}

	@PostMapping("test")
	@SystemControllerLog(description = "1111",sucPath = "success",errPath = "data.code")
	public TestBean test() {
		log.info("do sth");
		ArrayList<ErrMessage> list = Lists.newArrayList();
		List<ErrMessage> errMessages = Arrays.asList(new ErrMessage("001", "111"), new ErrMessage("002", "222"));
		return new TestBean(true,errMessages,"eeee");

	}
}
