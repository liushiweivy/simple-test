package com.example.estest.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author simple
 * @date 2021/10/3 22:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	public ResponseDto(Boolean success, String msgCode, String msg) {
		this.success = success;
		this.msgCode = msgCode;
		this.msg = msg;
	}

	private Boolean success;

	private String msgCode;

	private String msg;

	private List<MsgDto> msgDtoList;

}
