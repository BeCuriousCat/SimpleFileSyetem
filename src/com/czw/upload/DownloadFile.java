package com.czw.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.czw.service.UploadService;
import com.opensymphony.xwork2.ActionSupport;



public class DownloadFile extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String inputPath;
	
	public String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	@Override
	public String execute() throws Exception {
	
		return SUCCESS;
	}
	
	public InputStream getInputStream() throws IOException{
	
		UploadService uploadService = new UploadService();
		String path = uploadService.getRelatePath(filename);
		
		String filepath = ServletActionContext.getServletContext().getRealPath(path);
		
		File file = new File(filepath);

 		return  FileUtils.openInputStream(file);
	}
	
	public String getDownloadFileName(){
		String downloadFileName ="";
		
		UploadService uploadService = new UploadService();
		uploadService.updownloadtimes(filename);
		downloadFileName = uploadService.getFlieName(filename);
		

		try {
			downloadFileName = URLEncoder.encode(downloadFileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return downloadFileName;
	}
}