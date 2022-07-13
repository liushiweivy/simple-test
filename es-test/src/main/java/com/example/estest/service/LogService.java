package com.example.estest.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.estest.dto.ApiLogDto;
import com.example.estest.dto.IndicatorDto;
import com.example.estest.entity.ApiLog;
import com.example.estest.entity.Indicator;
import com.example.estest.mapper.ApiLogMapper;
import com.example.estest.mapper.IndicatorMapper;

/**
 * @author simple
 * @date 2021/6/19 7:53
 */
@Service
@Slf4j
public class LogService {

	@Autowired
	ApiLogMapper apiLogMapper;

	@Autowired
	IndicatorMapper indicatorMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	private static final Map<String, Map<String, Integer>> cacheMap = new HashMap<>();


	//		Map<String, Set<String>> listMap = indicators.stream()
//			.collect(Collectors.groupingBy(Indicator::getFieldPath, Collectors.mapping(Indicator::getFieldValue,
//				Collectors.toSet())));

	//1.当接口第一次进入时，只记录接口的url，不统计指标，查到的指标信息是空的。
	//2.通过redis获取接口的指标配置，将url作为key，指标配置作为value存到redis，如果能查到配置，就多该指标配置进行统计。
	//3.新增和修改配置的时候同时更新redis
//	public void anaLog(ApiLogDto dto) throws Exception {
//		String response = dto.getResponseJson();
//		String url = dto.getUrl();
//		String list = stringRedisTemplate.opsForValue().get(url);
//
//		String fieldPath = entry.getKey();
//			Set<Indicator> indicatorsSet = entry.getValue();
//			List<String> valueList = getBypath(response, fieldPath);
//
//		log.info(response);
//		log.info(JSONObject.toJSONString(dto));
//		Object object = JSONObject.parseObject(response);
//	}

	public void anaLog(ApiLogDto dto) throws Exception {
		String url = dto.getUrl();
		String listStr = stringRedisTemplate.opsForValue().get(url);
		List<Indicator> indicators = JSONObject.parseArray(listStr, Indicator.class);
		//按照json路径分组
				Map<String, Set<String>> listMap = indicators.stream()
			.collect(Collectors.groupingBy(Indicator::getFieldPath, Collectors.mapping(Indicator::getFieldValue,
				Collectors.toSet())));
		Set<Entry<String, Set<String>>> entries = listMap.entrySet();
		String response = dto.getResponseJson();
		List<Indicator> indicatorList = new ArrayList<>();
		//遍历所有定义的json路径，并统计此次请求响应的指标值
		Map<String,  IndicatorDto> indicatorMap = new HashMap<>();
		for (Entry<String, Set<String>> entry : entries) {
			String fieldPath = entry.getKey();
			Set<String> indicatorsSet = entry.getValue();
			List<String> valueList = getBypath(response, fieldPath);
			valueList.removeIf(o -> !indicatorsSet.contains(o));
			for (String value : valueList) {
				indicatorMap.put(fieldPath + value, new IndicatorDto(fieldPath, value, 1));
			}
		}
		log.info(response);
		log.info(JSONObject.toJSONString(dto));
	}

//	public void anaLog(ApiLogDto dto) throws Exception {
//		ApiLog apiLog = saveApiLog(dto);
//		Integer apiLogId = apiLog.getId();
//		QueryWrapper<Indicator> configQueryWrapper = new QueryWrapper<>();
//		configQueryWrapper.lambda().eq(Indicator::getApiId, apiLogId);
//		List<Indicator> indicators = indicatorMapper.selectList(configQueryWrapper);
//		//按照json路径分组
//		Map<String, Set<Indicator>> listMap = indicators.stream()
//			.collect(Collectors.groupingBy(Indicator::getFieldPath,
//				Collectors.toSet()));
//		Set<Entry<String, Set<Indicator>>> entries = listMap.entrySet();
//		String response = dto.getResponseJson();
//		List<Indicator> indicatorList = new ArrayList<>();
//		//遍历所有定义的json路径，并统计此次请求响应的指标值
//		for (Entry<String, Set<Indicator>> entry : entries) {
//			String fieldPath = entry.getKey();
//			Set<Indicator> indicatorsSet = entry.getValue();
//			List<String> valueList = getBypath(response, fieldPath);
//			indicatorsSet.removeIf(o -> !valueList.contains(o.getFieldValue()));
//			indicatorList.addAll(indicatorsSet);
//		}
//
//		log.info(response);
//		log.info(JSONObject.toJSONString(dto));
//		Object object = JSONObject.parseObject(response);
//	}

	private ApiLog saveApiLog(ApiLogDto dto) {
		String url = dto.getUrl();
		QueryWrapper<ApiLog> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(ApiLog::getUrl, url);
		ApiLog apiLog = apiLogMapper.selectOne(wrapper);
		if (apiLog == null) {
			apiLog = new ApiLog();
			apiLog.setEffFlg("1");
			apiLog.setUrl(url);
			apiLogMapper.insert(apiLog);
		}
		return apiLog;
	}

	public static void main(String[] args) throws Exception {
//		String res = " {\"data\":[{\"code\":\"001\",\"msg\":\"111\"},{\"code\":\"002\",\"msg\":\"222\"}],\"message\":\"eeee\",\"success\":true}";
//		String res1 = " {\"data\":[{\"code\":\"001\",\"msg\":\"111\"},{\"code\":\"002\",\"msg\":\"222\"}]," +
//			"\"message\":\"eeee\",\"success\":true}";
//		String res2 = "你好";
//		String path = "data.code";
////		String data = getByKey(res, "data");
////		String success = getByKey(res, "success");
////		System.out.println(data);
////		System.out.println(success);
////		String bypath = getBypath(res, path);
//		String bypath1 = getBypath(res1, path);
//		String bypath2 = getBypath(res2, path);
////		System.out.println(bypath);
//		System.out.println(bypath1);
//		System.out.println(bypath2);


		Stream<String> lines = Files.lines(Paths.get("D:\\workspace\\simple-test\\es-test" +
			"\\src\\main\\java\\com\\example\\estest\\service\\LogService.java"));
		List<String> collect = lines.collect(Collectors.toList());
		for (String s : collect) {
			System.out.println(s);
		}
	}


	public static List<String> getBypath(String json, String path) throws Exception {
		String[] split = path.split("\\.");
		String value = json;
		for (int i = 0; i < split.length; i++) {
			value = getByKey(value, split[i]);
		}
		Object object = JSONObject.parse(value);
		if (object instanceof JSONArray) {
			return JSONObject.parseArray(value, String.class);
		}
		return Collections.singletonList(value);
	}

	public static List<String> getBypathAndValue(String json, String path) throws Exception {
		String[] split = path.split("\\.");
		String value = json;
		for (int i = 0; i < split.length; i++) {
			value = getByKey(value, split[i]);
		}
		Object object = JSONObject.parse(value);
		if (object instanceof JSONArray) {
			return JSONObject.parseArray(value, String.class);
		}
		return Collections.singletonList(value);
	}

	public static String getByKey(String json, String key) throws Exception {
		Object object = null;
		try {
			object = JSONObject.parse(json);
			if (object instanceof JSONObject) {
				return ((JSONObject) object).getString(key);
			} else if (object instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) object;
				List<Object> objects = new ArrayList<>();
				for (Object o : jsonArray) {
					if (o instanceof JSONObject) {
						objects.add(((JSONObject) o).get(key));
					}
				}
				return JSONObject.toJSONString(objects);
			}
		} catch (Exception e) {
			log.error("json:{},格式有误", json, e);
			return "";
		}
		return "";
	}
}
