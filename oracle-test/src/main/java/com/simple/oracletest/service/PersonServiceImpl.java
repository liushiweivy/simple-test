package com.simple.oracletest.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.oracletest.dao.PersonMapper;
import com.simple.oracletest.dto.UserDTO;
import com.simple.oracletest.entity.Person;

/**
 * @author simple
 * @date 2021/5/15 22:15
 */
@Service
public class PersonServiceImpl {

	@Autowired
	private PersonMapper personMapper;

	public void register(UserDTO userDTO) throws Exception {
		Person person = new Person();
		BeanUtils.copyProperties(userDTO, person);
		Integer count = personMapper.selectCount(new QueryWrapper<Person>().lambda()
			.eq(Person::getUsername, person.getUsername()));
		if (count > 0) {
			throw new Exception("用户名已存在");
		}
		personMapper.insert(person);
		System.out.println(person.getId());
	}

	public Person login(UserDTO userDTO) throws Exception {
		Person person = new Person();
		BeanUtils.copyProperties(userDTO, person);
		Integer count = personMapper.selectCount(new QueryWrapper<Person>().lambda()
			.eq(Person::getUsername, person.getUsername()).eq(Person::getPassword, person.getPassword()));
		if (count > 0) {
			return person;
		}else {
			return null;
		}
	}
}
