package com.czw.service;

import java.util.ArrayList;
import java.util.Vector;

import com.czw.dao.Dao;



public class UploadService {
	Dao dao = Dao.getInstance();

	public void insertdata(String[] keys) {
		
		//String[] keys ={uploadFileName ,newFileName ,author ,relatedAddress ,time}
		//在上传文件的表里插入记录
		String sql = "insert into fs_upLoadFile ( filename ,fileNameNo ,author ,relatedPath ,uploaddate,downloadtimes ) values ( '"+keys[0]+"','"+keys[1]+"','"+keys[2]+"','"+keys[3]+"','"+keys[4]+"','"+0+"')";
		dao.insert(sql);

	}

	public String getRelatePath(String filename) {
		String sql = "select relatedPath from fs_upLoadFile where fileNameNo ='"+filename+"'";
		String path = dao.selectOnlyValue(sql).toString();
		return path;
	}
	public String getFlieName(String filename) {
		String sql = "select filename from fs_upLoadFile where fileNameNo ='"+filename+"'";
		String fileName = dao.selectOnlyValue(sql).toString();
		return fileName;
	}

	public ArrayList<Object[]> getDownloadFiles() {
		String sql = "select * from fs_upLoadFile order by uploaddate desc";
		Vector<Vector<Object>> Vect= dao.selectSomeNote(sql);
		System.out.println(Vect.toString());
		ArrayList<Object[]> dataList = null; 
		dataList = getUsefulData(Vect);
		return dataList;
	}

	private ArrayList<Object[]> getUsefulData(Vector<Vector<Object>> vect) {
		ArrayList<Object[]> dataList = new ArrayList<Object[]>();
		for (Vector<Object> v : vect) {
			Object[] o = new Object[6];
			o[0] = v.get(2);						// 从2开始的原因是：查询时含有编号，调用selectSomeNote(sql)再加上编号 名称
			o[1] = v.get(3);						// 获取列表顺序与数据库字段顺序对应 时间
			o[2] = v.get(4);						// 次数
			o[3] = v.get(5);						// 作者
			o[4] = v.get(7);						// 路径
		}
		return dataList;
	}
/**
 * 更新下载次数
 * @param filename
 */
	public void updownloadtimes(String filename) {
		String sql = "select downloadtimes from fs_upLoadFile where fileNameNo ='"+filename+"'";
		int times = 0;
		Object o = dao.selectOnlyValue(sql);
		if( o == null){
		}else{
			 times = (Integer) o ;
		} 
		times = times + 1;
		sql = "update fs_upload set downloadtimes ='" + times + "' where fileNameNo ='"+filename+"'";
		dao.update(sql);
		
	}

	
}