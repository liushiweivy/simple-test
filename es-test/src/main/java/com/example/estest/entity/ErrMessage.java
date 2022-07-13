package com.example.estest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author simple
 * @date 2021/7/30 19:48
 */
@Data
@AllArgsConstructor
public class ErrMessage {
	String code;
	String msg;
}
