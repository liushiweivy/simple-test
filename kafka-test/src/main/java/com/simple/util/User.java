package com.simple.util;

import java.util.List;
import lombok.Data;

/**
 * @author simple
 * @date 2021/7/22 23:40
 */
@Data
public class User {

	String name;
	int age;
	List<String> params;
	Object object;
}
