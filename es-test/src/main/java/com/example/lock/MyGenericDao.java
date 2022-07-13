package com.example.lock;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;

/**
 * @author simple
 * @date 2022/3/19 10:41
 */
public class MyGenericDao {

	public   <T> List<T> queryList(String sql, Class<T> clazz, Object... args) throws SQLException,
		InstantiationException, IllegalAccessException, NoSuchFieldException {
		List<T> list = new ArrayList<>();
		Connection connection = JDBCUtils.getConnection();
		String schema = connection.getSchema();
		System.out.println(schema);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i+1, args[i]);
			}
		}
		ResultSet resultSet = preparedStatement.executeQuery();
		DatabaseMetaData metaDataBase = connection.getMetaData();
		ResultSetMetaData metaData = preparedStatement.getMetaData();
		int columnCount = metaData.getColumnCount();
			while (resultSet.next()) {
				T instance = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					String columnLabel = metaData.getColumnLabel(i + 1);
					Object value = resultSet.getObject(columnLabel);
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(instance, value);
				}
				list.add(instance);
			}

		JDBCUtils.closeResource(connection, preparedStatement, resultSet);
		return list;
	}

	public static void main(String[] args) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
//		List<Book> books = queryList("select * from book where id = ?;", Book.class,"6");
//		System.out.println(JSON.toJSON(books));
	}

}
