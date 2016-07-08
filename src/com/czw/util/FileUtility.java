/**
 * 
 */
package com.czw.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Huaishao Luo
 * @create 2015-1-18下午4:46:13
 */
@SuppressWarnings("serial")
public class FileUtility implements java.io.Serializable {

	private Properties prop;
	private InputStream is;

	/**
	 * 构造函数
	 * 
	 * @param filename
	 * */
	public FileUtility(String filename){
		prop = new Properties();
		is = getClass().getResourceAsStream(filename);
		try {
			prop.load(is);
			if (is != null){
				is.close();
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 取得属性
	 * @param 属性名
	 * @return 属性值
	 * 
	 * */
	public String getProperties(String PropertyName){
		return prop.getProperty(PropertyName);
	}
}