package com.simple.oracletest.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author simple
 * @date 2021/5/15 22:17
 */
@Data
public class UserDTO {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	private int gender;

	private Date birthday;
}
