package com.simple.dto;

import lombok.Data;

/**
 * Title: SystemControllerLog
 * @version V1.0
 * Description:  自定义注解，拦截controller
 * @date 2018年8月31日
 */

@Data
public class LogAnnoDTO {
	    String description;
    String sucPath;
    String errPath;
	String response;

}