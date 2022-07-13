package com.simple.multidatasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

/**
 * @author simple
 * @date 2021/7/1 23:17
 */
@Slf4j
public class Test {

	@org.junit.jupiter.api.Test
	public void test() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/test2?useSSL=false&useUnicode=true&characterEncoding=UTF-8" +
			"&serverTimezone";
		// =GMT%2B8&zeroDateTimeBehavior=convertToNull
		String driverClass = "com.mysql.jdbc.Driver";
		//2. 准备连接数据库的基本信息: url, user, password
		String user = "root";
		String password = "root";

		//2. 加载数据库驱动程序(对应的 Driver 实现类中有注册驱动的静态代码块)
		Class.forName(driverClass);



		Connection connection = DriverManager.getConnection(url, "root", "033233");
		System.out.println(connection);
		System.out.println(connection.isValid(4000));
		PreparedStatement preparedStatement = connection.prepareStatement("select * from user");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
//			System.out.println(JSON.toJSON(resultSet));
			System.out.println(resultSet.getObject(1));
		}

	}
}
