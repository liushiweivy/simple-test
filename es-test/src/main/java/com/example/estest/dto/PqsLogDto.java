/**
  * Copyright 2021 json.cn 
  */
package com.example.estest.dto;
import java.util.Date;
import lombok.Data;
import com.alibaba.fastjson.JSONObject;

/**
 * Auto-generated: 2021-08-28 10:18:35
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class PqsLogDto {

    private String appName;
    private String requestJson;
    private Object requestObject;
    private Object responseObject;
    private Date requestTime;
    private String responseJson;
    private Date responseTime;
    private String url;

    public static PqsLogDto build(String str) {
        PqsLogDto pqsLogDto = JSONObject.parseObject(str, PqsLogDto.class);
        pqsLogDto.setRequestObject(JSONObject.parse(pqsLogDto.getRequestJson()));
        pqsLogDto.setResponseObject(JSONObject.parse(pqsLogDto.getResponseJson()));
        return pqsLogDto;
    }

}