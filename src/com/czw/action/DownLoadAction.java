package com.czw.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.czw.bean.User;
import com.czw.service.UploadService;



public class DownLoadAction {
	private final String LABEL = "G"; 
	
	public String listDownloadFiles(){
		
		
		
		HttpServletRequest requset = ServletActionContext.getRequest();
		HttpSession session = requset.getSession();
		UploadService uploadService = new UploadService();
		
		//======================================================================
				User user = (User) session.getAttribute("user");
				if(user == null){
					return "login";
				}
				
				
		//======================================================================
		
		ArrayList<Object[]> dataList = uploadService.getDownloadFiles();
		
		requset.setAttribute("label", LABEL);
		requset.setAttribute("dataList", dataList);
		
		return "upload";
	}
}
