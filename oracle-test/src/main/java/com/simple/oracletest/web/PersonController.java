package com.simple.oracletest.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.oracletest.dao.PersonMapper;
import com.simple.oracletest.dto.UserDTO;
import com.simple.oracletest.entity.Person;
import com.simple.oracletest.service.PersonServiceImpl;

/**
 * @author simple
 * @date 2021/5/15 14:54
 */
@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private PersonServiceImpl personService;

	@GetMapping("list")
	public List<Person> getList() {
		return personMapper.selectList(new QueryWrapper<>());
	}

	@PostMapping("register")
	public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO) throws Exception {
		personService.register(userDTO);
		return ResponseEntity.ok().build();
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@Valid @RequestBody UserDTO userDTO, HttpSession session) throws Exception {
		Person person = personService.login(userDTO);
		if (person !=null) {
			session.setAttribute("user", person);
			System.out.println("登录成功");
			return ResponseEntity.status(HttpStatus.OK).body("登录成功");
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("用户名或密码不存在");
		}
	}
}
