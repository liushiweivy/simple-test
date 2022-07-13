package com.simple.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simple.dto.LogAnnoDTO;
import com.simple.dto.ZcApiLog;

/**
 * @author simple
 * @date 2021/6/19 7:53
 */
@Service
@Slf4j
public class PqsService {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
//		String url = "https://www.zhipin.com/web/geek/recommend";
		String url = "https://blog.csdn.net/qq_41701956/article/details/110119625";
		ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
		String body = forEntity.getBody();
		System.out.println(JSONObject.toJSONString(forEntity));
		System.out.println(JSONObject.toJSONString(body));
	}


}
