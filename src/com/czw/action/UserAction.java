package com.czw.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.czw.bean.User;
import com.czw.service.UserService;
import com.czw.util.DealStr;
import com.czw.util.IPUtil;



public class UserAction {
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		
		DealStr dealStr = new DealStr();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		username = username == null ? "" : dealStr.codeStringNoEncode(username);
		password = password == null ? "" : dealStr.codeStringNoEncode(password);
		username = username.trim();
		password = password.trim();
		
		User user = null;
		
		//json使用
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		PrintWriter out = null;
		
		try {
			user = UserService.checkLogin(username, password);
			out = response.getWriter();
			String str = "";
			
			if (user != null ){
				session.removeAttribute("user");
				session.setAttribute("user", user);
				str = "{ \"result\" : \"1\" }";		//登录成功时字符串返回1
			} else {
				str = "{ \"result\" : \"0\" }";		//登录失败时字符串返回0
			}
			out.println(str);
		
		} catch (Exception e) {
			e.printStackTrace();
			out.println("程序内部错误");
		} finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
}
