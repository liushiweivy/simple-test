package com.simple.util;

import java.util.Arrays;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Main {
    public static void main(String[] args) {
//        List<String> obj1 = Arrays.asList("1", "2", "3");
//        List<String> obj2 = Arrays.asList("1", "3", "2");
//        System.out.println(JsonSameUtil.same(obj1, obj2)); // true
//
//        String str1 = "[{\"a\":1},{\"b\":2},{\"c\":3}]";
//        String str2 = "[{\"a\":1},{\"c\":3},{\"b\":2}]";
//        System.out.println(JsonSameUtil.same(str1, str2)); // true


        User user = new User();
        user.setName("jjjjjj");
        String jsonString = JSONObject.toJSONString(user, SerializerFeature.WriteMapNullValue);
        System.out.println(jsonString);
        String json = "{\"age\":0,\"name\":\"jjjj\",\"object\":null,\"params\":null}";
        System.out.println(JsonSameUtil.same(json,jsonString));
        System.out.println(JsonSameUtil.same(JSONObject.parse(json),JSONObject.parse(jsonString)));
    }
}