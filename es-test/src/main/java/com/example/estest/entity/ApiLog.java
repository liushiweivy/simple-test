package com.example.estest.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author simple
 * @date 2021/10/3 22:40
 */
@Data
@TableName
public class ApiLog {
	private Integer id;
	private String url;
	private String desc;
	private String effFlg;
}
