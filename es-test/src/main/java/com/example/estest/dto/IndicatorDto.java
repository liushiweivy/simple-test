package com.example.estest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author simple
 * @date 2021/8/19 23:12
 */
@Data
public class IndicatorDto {

	private Integer id;

	private String apiId;

	public IndicatorDto(String fieldPath, String fieldValue, Integer count) {
		this.fieldPath = fieldPath;
		this.fieldValue = fieldValue;
		this.count = count;
	}

	private String fieldPath;

	private String fieldValue;

	private Integer count;

}
