package com.yyf.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.yyf.base.BaseAction;
import com.yyf.model.DocType;
import com.yyf.service.DocTypeService;

@Namespace("/admin")
@ParentPackage("default")
public class DocTypeAction extends BaseAction implements ModelDriven<DocType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private DocType model = new DocType();

	@Resource
	private DocTypeService docTypeService;

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	@Action(value = "sort-manage", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/admin/sort-manage.jsp") })
	public String doToSortUI() throws Exception {
		return SUCCESS;
	}

	/**
	 * 得到分类列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "json-doc-types", results = {
			@Result(name = "json", type = "json", params = { "root", "dataMap" }) })
	public String getDocTypeList() throws Exception {
		List<DocType> list =  docTypeService.getParentsType();
		dataMap.put("doctypes",list);
		return "json";
	}
	
	@Action(value = "doc-type-submit", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String docTypeSubmit() throws Exception {
		DocType docType = docTypeService.saveDocType(model);
		dataMap.put("doctype",docType);
		return "success";
	}
	
	@Action(value = "delete-doc-type", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String deleteDocType() throws Exception {
		docTypeService.delete(model);
		return "success";
	}
	
	

	@Override
	public DocType getModel() {
		return model;
	}

}
