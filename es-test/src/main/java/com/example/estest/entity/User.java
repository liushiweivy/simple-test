package com.example.estest.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author simple
 * @date 2021/9/19 10:34
 */
@Data
@TableName
public class User {

	private String id;
	private String userName;
}
