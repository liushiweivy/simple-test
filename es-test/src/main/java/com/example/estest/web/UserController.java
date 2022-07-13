package com.example.estest.web;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.estest.entity.User;

/**
 * @author simple
 * @date 2021/9/19 10:32
 */
@RestController
@RequestMapping("user")
public class UserController {

	@GetMapping
	public List<User> userList() {

		return null;
	}
}
