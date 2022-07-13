package com.simple.dto;

import java.util.Map;
import lombok.Data;

/**
 * @author simple
 * @date 2021/8/1 17:32
 */
@Data
public class ZcApiLogDto {
	private Long id;
	private String uri;
	private String productCode;
	private String apiDesc;
	private Map<String, IndicatorDto> indicatorMap;

}
