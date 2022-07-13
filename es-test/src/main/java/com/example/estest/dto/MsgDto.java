package com.example.estest.dto;

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
public class MsgDto {

	private String msgCode;

	private String msg;

}
