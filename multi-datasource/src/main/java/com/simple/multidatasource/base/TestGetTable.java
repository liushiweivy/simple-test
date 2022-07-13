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

/**
 * @author simple
 * @date 2021/6/28 22:28
 */
public class TestGetTable {

	public static void getConnAndTableStruct() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;
		try {
			// oracle连接
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			connection= DriverManager.getConnection(url,"itcastuser","itcast");
			pstmt = (PreparedStatement) connection.prepareStatement("select * from A_STAT");
			pstmt.execute(); // 这点特别要注意:如果是Oracle而对于mysql可以不用加.
			rsmd = (ResultSetMetaData) pstmt.getMetaData();
			//className = "User";
			//tableName = "user";
			for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
				//rsmd.getColumnName(i);
				//rsmd.getColumnClassName(i).substring(rsmd.getColumnClassName(i).lastIndexOf(".") + 1);
				System.out.println(rsmd.getColumnClassName(i));
				System.out.println(rsmd.getColumnName(i));
				System.out.println(rsmd.getColumnTypeName(i));
				System.out.println(rsmd.getPrecision(i));
				System.out.println(rsmd.getScale(i));
				System.out.println(rsmd.isNullable(i));//0 不能为空   1可以为空
				System.out.println("=============================");
				System.out.println("=============================");
				System.out.println("=============================");
				System.out.println("=============================");
				System.out.println("=============================");
				System.out.println("=============================");
			}
		} catch (ClassNotFoundException cnfex) {
			cnfex.printStackTrace();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
