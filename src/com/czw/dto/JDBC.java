package com.czw.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.czw.util.FileUtility;



public class JDBC {
	private static String DRIVERCLASS = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://115.28.172.55:3306?user=user&password=159357";
	private static String USERNAME = "user";
	private static String PASSWORD = "159357";
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	static {											// 在静态代码块中加载数据库驱动
		try {
			FileUtility fileUtility = new FileUtility("/jdbc.properties");
			DRIVERCLASS = fileUtility.getProperties("driverClassName");
			URL = fileUtility.getProperties("url");
			Class.forName(DRIVERCLASS).newInstance();	// 加载数据库驱动
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {	// 创建数据库连接的方法
		Connection conn = threadLocal.get();	// 从线程中获得数据库连接
		if (conn == null) {						// 没有可用的数据库连接
			try {
				conn = DriverManager.getConnection(URL);// 创建新的数据库连接
				threadLocal.set(conn);			// 将数据库连接保存到线程中
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;							// 返回数据库连接
	}

	public static boolean closeConnection() {	// 关闭数据库连接的方法
		boolean isClosed = true;				// 默认关闭成功
		Connection conn = threadLocal.get();	// 从线程中获得数据库连接
		if (conn != null) {						// 数据库连接可用
			threadLocal.set(null);				// 清空线程中的数据库连接
			try {
				conn.close();					// 关闭数据库连接
			} catch (SQLException e) {
				isClosed = false;				// 关闭失败
				e.printStackTrace();
			}
		}
		return isClosed;						// 返回关闭结果
	}
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
}
