package com.simple.dto;

import lombok.Data;

/**
 * @author simple
 * @date 2021/8/1 17:32
 */
@Data
public class IndicatorDto {

	private Long id;
	private Long apiId;
	private String indicatorField;
	private String indicatorValue;
	private Integer count;

	public IndicatorDto addCount(IndicatorDto indicatorDto) {
		if (null != indicatorDto) {
			this.setCount(this.count+indicatorDto.getCount());
		}
		return this;
	}
}
