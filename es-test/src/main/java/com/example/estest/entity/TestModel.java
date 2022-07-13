package com.example.estest.entity;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author simple
 * @date 2021/9/5 23:49
 */
@NoArgsConstructor
@Data
public class TestModel {

	@JsonProperty("test_id")
	private String testId;
	@JsonProperty("test_name")
	private String testName;
	@JsonProperty("test_content")
	private List<TestContentDTO> testContent;

	@NoArgsConstructor
	@Data
	public static class TestContentDTO {
		@JsonProperty("lyric")
		private String lyric;
	}

	public static void main(String[] args) {
		extracted();
	}

	private static void extracted() {
		System.out.println(111);
	}
}
