package com.simple.multidatasource.base;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

/**
 * @author simple
 * @date 2021/6/28 22:28
 */
@Slf4j
public class TestGetTableDemo {

	//获取数据表名
	public static List<String> getDataTableName(String database) {
		Connection connection = getConnection();
		List<String> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		try {
			DatabaseMetaData metaData = connection.getMetaData();
			ResultSet schemas = metaData.getSchemas();
			while (schemas.next()) {
				list1.add(schemas.getString("TABLE_SCHEM"));
			}
			ResultSet resultSet = metaData.getTables(database, "ITCASTUSER", "%", new String[]{"TABLE"});
//			int maxRowSize = metaData.getMaxRowSize();
//			int row = resultSet.getRow();
//			ResultSet catalogs = metaData.getCatalogs();
//			System.out.println(row);
//			System.out.println(maxRowSize);
			while (resultSet.next()) {
				list.add(resultSet.getString("TABLE_NAME"));
//				list.add(resultSet.getString("TABLE_NAME"));
//				list.add(resultSet.getString("remarks"));
				list.add(resultSet.getString("REMARKS"));
				list.add(resultSet.getString("COMMENTS"));
//				String NUM_ROWS = resultSet.getString("NUM_ROWS");
//				System.out.println(NUM_ROWS);
			}
			resultSet.close();
			schemas.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info(JSON.toJSONString(list1));
		log.info(JSON.toJSONString(list));
		return list1;
	}

	private static Connection getConnection() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;
		try {
			// oracle连接
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			connection = DriverManager.getConnection(url, "itcastuser", "itcast");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		getDataTableName("ITCASTUSER");
	}
}
