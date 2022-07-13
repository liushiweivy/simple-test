package com.simple.oracletest.entity;

import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author simple
 * @date 2021/5/15 14:51
 */
@Data
@TableName("ITCASTUSER.person")
@KeySequence(value = "ITCASTUSER.PERSON1_SEQ",clazz = Integer.class)
public class Person {

	@TableId(type = IdType.INPUT)
	private Integer id;

	private String username;

	private String password;

	private Integer gender;

	private Date birthday;
}
