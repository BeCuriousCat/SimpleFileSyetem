package com.test.dao;

import java.util.Iterator;
import java.util.Vector;

import com.czw.dao.Dao;

public class TestDao {
	Dao dao = Dao.getInstance();
	
	public void testDao(){
		String sql = "select * from fs_user";
		Vector<Vector<Object>> list = dao.selectSomeNote(sql);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Vector<Object> vector = (Vector<Object>) iterator.next();
			
				System.out.println(vector.toString());
			
		}
	}
	
	public static void main(String[] args) {
		new TestDao().testDao();
	}
}
