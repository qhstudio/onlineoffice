package com.yyf.actions;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.data.domain.Page;

import com.opensymphony.xwork2.ActionContext;
import com.yyf.base.BaseAction;
import com.yyf.model.Doc;
import com.yyf.model.User;
import com.yyf.service.DocSevice;
import com.yyf.service.UserService;

/**
 * 首页跳转等
 * 
 * @author yyf
 *
 */
@Namespace("/main")
@ParentPackage("default")
public class MainAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private DocSevice docSevice;
	
	@Resource
	private UserService userService;
	private Long docId;
	private Doc doc;
	

	public Doc getDoc() {
		return doc;
	}

	public void setDoc(Doc doc) {
		this.doc = doc;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	/**
	 * 跳转到主页
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "index", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/main/index.jsp") })
	public String doIndex() throws Exception {
		
		return SUCCESS;
	}

	/**
	 * 跳转到最近文档
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "recent", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/main/recent.jsp") })
	public String doRecent() throws Exception {

		return SUCCESS;
	}

	@Action(value = "recentListInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String doGetRecentList() {
		if (pageNum == null) {
			pageNum = DEFAULT_PAGE_NUM;
		}
		Page<Doc> page = docSevice.getDocPage(pageNum, DEFAULT_PAGE_SIZE);
		dataMap.put("docs", page);
		return SUCCESS;
	}
	
	/**
	 * 跳转到我的文档
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "doc-result", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/doc/doc-result.jsp") })
	public String doToResultDoc() throws Exception {
		doc = docSevice.getDocById(docId);
		return SUCCESS;
	}

}
