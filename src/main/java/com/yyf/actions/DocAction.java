package com.yyf.actions;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionContext;
import com.yyf.base.BaseAction;
import com.yyf.lucene.CreateIndex;
import com.yyf.model.Doc;
import com.yyf.model.DocType;
import com.yyf.model.User;
import com.yyf.service.DocSevice;

@Namespace("/doc")
@ParentPackage("default")
public class DocAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private DocSevice docSevice;
	private Doc upDoc;
	private Long docId;
	private String docName;
	private Long typeId;
	private Integer docAuthority;
	private String docDesc;
	private Page<Doc> page;
	private List<DocType> typeList;
	private CreateIndex ci = new CreateIndex();
	
	public List<DocType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DocType> typeList) {
		this.typeList = typeList;
	}

	public Page<Doc> getPage() {
		return page;
	}

	public void setPage(Page<Doc> page) {
		this.page = page;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Integer getDocAuthority() {
		return docAuthority;
	}

	public void setDocAuthority(Integer docAuthority) {
		this.docAuthority = docAuthority;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public Doc getUpDoc() {
		return upDoc;
	}

	public void setUpDoc(Doc upDoc) {
		this.upDoc = upDoc;
	}


	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	
	/**
	 * 我的文档页面
	 * @return
	 * @throws Exception
	 */
	@Action(value = "mydoc-list", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/doc/mydoc-list.jsp") })
	public String doMyDocList() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		page = docSevice.getMyDocs(user.getUserId(),getPageNum(),DEFAULT_PAGE_SIZE);
//		String path = ServletActionContext.getServletContext().getRealPath("/upload/photo/");
//		System.out.println(path);
		return SUCCESS;
	}
	
	@Action(value = "delete", results = {
			@Result(name = "success", type = "chain", location = "mydoc-list") })
	public String delete() throws Exception {
		if(docId != null){
			User user = (User) ActionContext.getContext().getSession().get("user");
			docSevice.delete(user.getUserId(),docId);
		}
		return SUCCESS;
	}

	/**
	 * 跳转到上传页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upload", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/doc/upload.jsp") })
	public String doUpload() throws Exception {
		if(docId != null){			
			upDoc = docSevice.getDocById(docId);
		}
		
		return SUCCESS;
	}
	
	@Action(value = "add-get-doc", results = {@Result(name = "success", type = "json", params = { "root", "upDoc" })})
	public String addDocFile() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user == null){
			return "nouser";
		}
		Doc doc = new Doc();
		doc.setDocOwnUser(user);
		upDoc = docSevice.addDoc(doc);
		return SUCCESS;
	}
	
	@Action(value = "change-au", results = {@Result(name = "success", type = "json", params = { "root", "upDoc" })})
	public String docChangeAu() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user == null){
			return "nouser";
		}
		Doc doc = docSevice.getDocById(docId);
		doc.setDocAuthority(docAuthority);
		upDoc = docSevice.addDoc(doc);
		return SUCCESS;
	}
	
	
	@Action(value="add-doc-info",results={@Result(name="success",type="redirectAction",location="mydoc-list")})
	public String addDocInfo() throws Exception{
		Doc innerDoc = docSevice.getDocById(docId);
		DocType docType = new DocType();
		docType.setTypeId(typeId);
		innerDoc.setDocAuthority(docAuthority);
		innerDoc.setDocDesc(docDesc);
		innerDoc.setDocName(docName);
		innerDoc.setDocType(docType);
		try {
//			System.out.println(innerDoc);
			docSevice.addDoc(innerDoc,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	

}
