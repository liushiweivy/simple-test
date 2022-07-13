package com.simple.multidatasource.base;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple.multidatasource.mapper.UserMapper;

/**
 * @author simple
 * @date 2021/6/27 11:27
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> queryUserInfo() {
		return userMapper.queryUserInfo();
	}

	public int register(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		String salt = CodecUtils.generateSalt();
		user.setPassword(CodecUtils.md5Hex(user.getPassword(),salt));
		return	userMapper.insert(user);
	}

	public Boolean check(String name,String password) {
		QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
		userQueryWrapper.lambda().eq(User::getUserName, name);
		User record = userMapper.selectOne(userQueryWrapper);
		String salt = CodecUtils.generateSalt();
		if (StringUtils.equals(password, CodecUtils.shaHex(record.getPassword(), record.getSalt()))) {
			return true;
		}
		return false;
	}


	public static void main(String[] args) {
		String salt = CodecUtils.generateSalt();
		String pass = CodecUtils.md5Hex("1234", salt);
		String password = CodecUtils.shaHex(pass, salt);
		System.out.println(salt);
		System.out.println(pass);
		System.out.println(password);
	}
}
