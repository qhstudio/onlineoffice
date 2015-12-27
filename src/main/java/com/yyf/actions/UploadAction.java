package com.yyf.actions;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yyf.model.Doc;
import com.yyf.model.User;
import com.yyf.service.DocSevice;
import com.yyf.service.OfficeConvert;
import com.yyf.service.OfficeConvertImp;
import com.yyf.utils.FileUtil;

/**
 * 上传文件
 * 
 * @author yyf
 *
 */
@Namespace("/doc")
@ParentPackage("default")
public class UploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private DocSevice docSevice;
	
	private File file;
	private String fileName;
	private String contentType;
	private Long docId;
	

	

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	@Action(value = "file-upload", results = {
			@Result(name = "success", type = "redirect") })
	public String execute() throws Exception {
//		User user = (User) ActionContext.getContext().getSession().get("user");
//		Long userId = user.getUserId();
		User user =new User();
		user.setUserId(1l);
		String uri = user.getUserId()+"/document/";
		File f=new File("G:/upload/"+uri);
		f.mkdirs();
		
		String ext = FileUtil.getFileExt(fileName);
		uri += UUID.randomUUID().toString() + "." + ext;
		FileUtil.saveFile(new FileInputStream(file), new File("G:/upload/"+uri));
		
		System.out.println(docId+" "+fileName);
		Doc doc = docSevice.getDocById(docId);
		doc.setDocDate(new Date());
		doc.setDocName(fileName);
		doc.setDocFoot(ext);
		float fileSize = file.length()/1024;
		doc.setDocSize(fileSize);
		doc.setDocPath(uri);
		doc.setAuthority(3);
		docSevice.addDoc(doc);
		
		OfficeConvert convert = getOfficeConvert();
		boolean isOffice = convert.isOfficeFile(fileName);
		if(isOffice){
			convert.convertToPDF( new File("G:/upload/"+uri),  new File("G:/upload/"+uri+".pdf"));
			convert.convertToSWF(new File("G:/upload/"+uri+".pdf"), new File("G:/upload/"+uri+".swf"));
			System.out.println("转换成功");
		}
		
		return NONE;
	}
	
	public OfficeConvert getOfficeConvert(){
		org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration config = new org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration();
		config.setOfficeHome("C:\\Program Files (x86)\\OpenOffice 4\\");
		//OpenOffice安装路径
		config.setPortNumber(8100);
		//启动服务端口号
		config.setTaskExecutionTimeout(60000);
		//单任务执行超时 1000*60 1分钟
		config.setTaskQueueTimeout(60000000);
		//任务队列超时  1000*60 * 1000 1000分钟
		OfficeConvertImp imp = new OfficeConvertImp();
		imp.setConvert(true);
		imp.setConfiguration(config);
		imp.setPdf2swf("D:\\DevInstall\\SWFTools\\pdf2swf.exe");
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, new String[]{"doc","docx","xls","xlsx","ppt","pptx"});
		imp.setConvertExtList(list);
		return imp;
		
	}

}
