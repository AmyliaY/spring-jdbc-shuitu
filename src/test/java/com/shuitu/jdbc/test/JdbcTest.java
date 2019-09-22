package com.shuitu.jdbc.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shuitu.demo.entity.Coder;

public class JdbcTest {

	public static void main(String[] args) {

		// 原生的JDBC的操作和JDBC框架的封装思路
		try {

			/*
			 * Driver类的源代码，看到其中的静态代码块可以发现，这个类在被加载进虚拟机时就已经完成了实例化，
			 * 并向DriverManager中进行了注册
			 * 
			 * package com.mysql.jdbc;
			 * 
			 * import java.sql.*;
			 * 
			 * public class Driver extends NonRegisteringDriver implements
			 * java.sql.Driver { public Driver() throws SQLException { }
			 * 
			 * static { try { DriverManager.registerDriver(new Driver()); }
			 * catch (SQLException E) { throw new RuntimeException(
			 * "Can't register driver!"); } } }
			 */

			// 第1步和第2步 可以被封装成DataSource，放入连接池，提高程序响应速度
			
			// 1、加载驱动类
			Class.forName("com.mysql.jdbc.Driver");

			// 2、建立连接
			Connection con = DriverManager.getConnection("jdbc:mysql://192.168.1.105:3306/gupaoedu_db", "root",
					"123456");

			
			
			// 3、创建语句集
			PreparedStatement pstm = con.prepareStatement("select * from t_member");
			// 4、执行
			ResultSet rs = pstm.executeQuery();

			
			
			// 第5步可以封装成一个ORM的过程，将数据库返回的结果自动变成一个 实体类

			int len = rs.getMetaData().getColumnCount();
			// 5、获取结果集
			List<Object> result = new ArrayList<Object>();
			while (rs.next()) {

				Class clazz = Coder.class;
				Object obj = clazz.newInstance();
				for (int i = 1; i <= len; i++) {
					String columnName = rs.getMetaData().getColumnName(i);
					// 前提条件是属性名和字段名一致（约定）
					Field field = clazz.getDeclaredField(columnName);

					field.setAccessible(true);
					Object type = field.getType();
					if (type == Long.class) {
						field.set(obj, rs.getLong(columnName));
					} else if (type == String.class) {
						field.set(obj, rs.getString(columnName));
					}
				}

				result.add(obj);
			}
			System.out.println(JSON.toJSON(result));
			rs.close();
			pstm.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
