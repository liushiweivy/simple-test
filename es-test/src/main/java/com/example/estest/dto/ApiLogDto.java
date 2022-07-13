package com.example.estest.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiLogDto {

	private String requestJson;

	private String url;

	private String responseJson;

	private Map<String,  IndicatorDto> indicatorMap;

}
