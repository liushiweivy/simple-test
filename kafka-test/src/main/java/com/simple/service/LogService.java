package com.simple.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
public class LogService {

	public Long saveOrupDate(ZcApiLog zcApiLog) {

		return zcApiLog.getId();
	}

	public void log(LogAnnoDTO dto) {

		String response = dto.getResponse();
		log.info(response);
		log.info(JSONObject.toJSONString(dto));
		Object object = JSONObject.parseObject(response);
		if (object instanceof JSONObject) {

		}
	}

	public static void main(String[] args) throws Exception {
		String res = " {\"data\":[{\"code\":\"001\",\"msg\":\"111\"},{\"code\":\"002\",\"msg\":\"222\"}],\"message\":\"eeee\",\"success\":true}";
		String res1 = " {\"data\":[{\"code\":\"001\",\"msg\":\"111\"},{\"code\":\"002\",\"msg\":\"222\"}]," +
			"\"message\":\"eeee\",\"success\":true}";
		String res2 = "你好";
		String path = "data.code";

//		String data = getByKey(res, "data");
//		String success = getByKey(res, "success");
//		System.out.println(data);
//		System.out.println(success);
//		String bypath = getBypath(res, path);
		String bypath1 = getBypath(res1, path);
		String bypath2 = getBypath(res2, path);
//		System.out.println(bypath);
		System.out.println(bypath1);
		System.out.println(bypath2);
	}


	public static String getBypath(String json, String path) throws Exception {
		String[] split = path.split("\\.");
		int ans = split.length;
		String value = json;
		for (int i = 0; i < split.length; i++) {

			 value = getByKey(value, split[i]);
			ans--;
		}
		return value;
	}

	public static String getByKey(String json, String key) throws Exception {
		Object object = null;
		try {
			object = JSONObject.parse(json);

		if (object instanceof JSONObject) {
			return ((JSONObject) object).getString(key);
		}else if (object instanceof JSONArray){
			JSONArray jsonArray=(JSONArray)object;
			List<Object> objects = new ArrayList<>();
			for (Object o : jsonArray) {
				if (o instanceof JSONObject){
					objects.add(((JSONObject) o).get(key));
				}
			}
			return JSONObject.toJSONString(objects);
		}
		} catch (Exception e) {
			log.error("json:{},格式有误",json, e);
			return "";
		}
		return "";
	}

}
