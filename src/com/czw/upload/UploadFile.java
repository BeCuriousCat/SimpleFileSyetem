package com.czw.upload;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.czw.bean.User;
import com.czw.service.UploadService;
import com.opensymphony.xwork2.ActionSupport;



public class UploadFile extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String LABEL = "G";
	
	private File upload;
	
	private String uploadContentType;
	
	private String uploadFileName;
	
	private String result;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String execute() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/downloadFiles");
		File file = new File(path);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("label", LABEL);
		
		if(!file.exists()){
			System.out.println("生成文件夹");
			file.mkdir();
		}
		
		
		//取得文件名(包括路径)里最后一个"."的索引
        int index = uploadFileName.lastIndexOf(".");
        //取得文件扩展名
        String extendName = "";
        if (index >= 0) {
        	extendName = uploadFileName.substring(index).toLowerCase();
		}
        //用当前时间为文件重名名,确保文件名不重复
        Date date=new Date();
		
        DateFormat formatA=new SimpleDateFormat("yyyyMMddhhmm");
		String newFileName=formatA.format(date);
        
        newFileName = newFileName + extendName;
		File newfile = new File(file,newFileName);
		FileUtils.copyFile(upload, newfile);
		
		UploadService uploadService = new UploadService();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String relatedAddress = "/WEB-INF/downloadFiles/"+newFileName;
		String author = user.getUserName();
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		
		String[] keys ={uploadFileName ,newFileName ,author ,relatedAddress ,time}; 
		uploadService.insertdata(keys);
		
		result= "上传成功";
		return SUCCESS;
	}
	
}