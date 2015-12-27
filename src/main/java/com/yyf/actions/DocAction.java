package com.yyf.actions;

import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionContext;
import com.yyf.base.BaseAction;
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
	
	
	@Action(value = "mydoc-list", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/doc/mydoc-list.jsp") })
	public String doMyDocList() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		page = docSevice.getMyDocs(user.getUserId(),getPageNum(),DEFAULT_PAGE_SIZE);
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
	
	
	@Action(value="add-doc-info",results={@Result(name="success",type="dispatcher",location="/model.jsp")})
	public String addDocInfo(){
		Doc innerDoc = docSevice.getDocById(docId);
		DocType docType = new DocType();
		docType.setTypeId(typeId);
		innerDoc.setDocAuthority(docAuthority);
		innerDoc.setDocDesc(docDesc);
		innerDoc.setDocName(docName);
		innerDoc.setDocType(docType);
		docSevice.addDoc(innerDoc);
		System.out.println(innerDoc);
		return SUCCESS;
	}

	
	

}
