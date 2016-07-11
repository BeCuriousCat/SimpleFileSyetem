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
			System.out.println(vo.toString());
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

}
