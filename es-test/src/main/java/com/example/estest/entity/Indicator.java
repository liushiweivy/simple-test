package com.example.estest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author simple
 * @date 2021/8/19 23:12
 */
@Data
@TableName
public class Indicator {

	private Integer id;

	private String apiId;

	private String fieldPath;

	private String fieldDesc;

	private String fieldValue;

}
