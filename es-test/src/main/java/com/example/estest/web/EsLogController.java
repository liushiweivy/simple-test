package com.example.estest.web;

import java.util.ArrayList;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.estest.dto.EsLogDto;
import com.example.estest.entity.Business;
import com.example.estest.dto.PqsLogDto;
import com.example.estest.dto.PqsLogQuery;
import com.example.estest.service.EsLogService;


/**
 * @author simple
 * @date 2021/6/17 22:36
 */
@RestController
@Slf4j
public class EsLogController {

	@Autowired
	private EsLogService apiLogService;

//	@PostMapping("queryList")
//	public Map<String, Object> queryList(@RequestBody EsQueryParams esQueryParams) {
//		return bankService.queryList(esQueryParams);
//	}


	@PostMapping("addEsLog")
	public boolean addEsLog(@RequestBody EsLogDto apiLogDto) {
		ArrayList<Business> businessesA = new ArrayList<>();
		ArrayList<Business> businessesB = new ArrayList<>();
		businessesA.add(new Business("111", "A"));
		businessesA.add(new Business("222", "B"));
		businessesA.add(new Business("333", "C"));
		businessesB.add(new Business("111", "A"));
		businessesB.add(new Business("222", "B"));
		businessesB.add(new Business("333", "C"));
		apiLogDto=new EsLogDto( 1L,"AA", "1111", businessesA);
		new EsLogDto( 2L,"BB", "2222", businessesB);
		return apiLogService.addEsLog(apiLogDto);
	}

	@PostMapping("addPqsLog")
	public String addPqsLog( String str) {
		PqsLogDto build = PqsLogDto.build(str);
		return apiLogService.addPqsLog(build);
	}


	@PostMapping("getPqsLogList")
	public Map<String, Object> getPqsLogList( PqsLogQuery query) {
		return apiLogService.getPqsLogList(query);
	}


//	@PostMapping("addObject")
//	public boolean addObject(String index,@RequestBody Map<String, String> jsonMap) {
//		return bankService.addObject(index,jsonMap);
//
//	}
//
//	@PostMapping("updateEsLog")
//	public boolean updateEsLog(@RequestBody EsLog bank) {
//		return bankService.updateEsLog(bank);
//
//	}
//
//	@PostMapping("test")
//	@SystemControllerLog(description = "1111",sucPath = "success",errPath = "data.code")
//	public TestBean test() {
//		log.info("do sth");
//		ArrayList<ErrMessage> list = Lists.newArrayList();
//		List<ErrMessage> errMessages = Arrays.asList(new ErrMessage("001", "111"), new ErrMessage("002", "222"));
//		return new TestBean(true,errMessages,"eeee");
//
//	}
}
