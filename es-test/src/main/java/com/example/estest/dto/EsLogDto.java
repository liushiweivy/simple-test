package com.example.estest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EsLogDto {
	private Long id;

	private String requestJson;

	private String url;

	private Object responseJson;

}
