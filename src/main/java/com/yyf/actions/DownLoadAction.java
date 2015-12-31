package com.yyf.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;
import com.yyf.model.Doc;
import com.yyf.service.DocSevice;
import com.yyf.utils.FileUtil;

@Namespace("/doc")
@ParentPackage("default")
public class DownLoadAction extends ActionSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long size;
	private String contentType;
	private String fileName;
	private InputStream inputStream;
	private Long docId;
	private String ext = "";
	@Resource
	private DocSevice docSevice;
	@Override
	@Action(value = "down-load-doc")
	public String execute() throws Exception {
		//Map<String, Object> session = ActionContext.getContext().getSession();
		Doc doc = docSevice.getDocById(docId);
		fileName = doc.getDocName();
		//System.out.println(fileName);
		contentType = doc.getDocContentType();
		File file = new File(FileUtil.RootPath+doc.getDocPath()+ext);
		inputStream = new FileInputStream(file);
		size = file.length();
//		System.out.println("G://upload/"+doc.getDocPath()+ext);
		return "down";
	}
	public long getSize() {
		return size;
	}
	public String getContentType() {
		return contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public void setExt(String ext) {
		if(ext == null || "".equals(ext.trim())){
			this.ext = "";
		}else{
			this.ext = "."+ext;
		}
		
		
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	
	
	
}
