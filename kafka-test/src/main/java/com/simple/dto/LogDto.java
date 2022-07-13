package com.simple.dto;

import lombok.Data;
import org.apache.kafka.common.protocol.types.Field.Str;

/**
 * @author simple
 * @date 2021/8/1 17:32
 */
@Data
public class LogDto {
	String uri;
	String code;
	Long count;
}
