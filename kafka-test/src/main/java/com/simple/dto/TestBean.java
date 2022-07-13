package com.simple.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author simple
 * @date 2021/7/30 19:47
 */
@Data
@AllArgsConstructor
public class TestBean {

	private Boolean success;
	private Object data;
	private  String message;
}
