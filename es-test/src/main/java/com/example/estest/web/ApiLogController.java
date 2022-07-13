package com.example.estest.web;

import java.util.ArrayList;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.example.estest.config.anno.SystemControllerLog;
import com.example.estest.dto.EsLogDto;
import com.example.estest.dto.PqsLogDto;
import com.example.estest.dto.PqsLogQuery;
import com.example.estest.dto.ResponseDto;
import com.example.estest.entity.Business;
import com.example.estest.service.EsLogService;


/**
 * @author simple
 * @date 2021/6/17 22:36
 */
@RestController
@Slf4j
public class ApiLogController {

	@Autowired
	private EsLogService apiLogService;


	@PostMapping("testApi")
	@SystemControllerLog
	public ResponseDto addEsLog(@RequestBody EsLogDto apiLogDto) {
		ResponseDto responseDto = new ResponseDto(true,"111","一");
		log.info(JSON.toJSONString(responseDto));
		return responseDto;
	}


}
