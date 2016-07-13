package com.czw.service;

import java.util.Vector;

import com.czw.bean.User;
import com.czw.dao.Dao;

public class UserService {
	static Dao dao = Dao.getInstance();

	public static User checkLogin(String username, String password) {
		try {
			String sql = "select * from fs_user where username='" + username
					+ "'";
			Vector<Object> vo = dao.selectOnlyNote(sql);
			
			if (vo != null && vo.size() != 0) {
				if (vo.get(2).equals(password)) {
					User user = new User(username, password);
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static long registerUser(String username, String password) {
		
		try {
			String sql = "insert into fs_user (username,password) values ('"+username+"','"+password+"')";
			System.out.println(sql);
			long id = dao.insert(sql);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 检查是否已经有此用户名，若有则返回true
	 * @param username
	 * @return
	 */
	public static boolean checkName(String username) {
		System.out.println("this is checkName");
		String sql = "";
		sql += "select * from fs_user where username = '"+username+"'";
		Vector<Object> v = dao.selectOnlyNote(sql);
		if( v != null && v.size() > 0) return true;
		return false;
	}
}
