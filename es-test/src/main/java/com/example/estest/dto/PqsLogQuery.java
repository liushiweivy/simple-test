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
public class PqsLogQuery {

    private String chlId;
    private String busiTyp;
    private Date startTime;
    private Date endTime;
    private Integer start ;
    private Integer limit ;


}