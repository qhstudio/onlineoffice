package com.yyf.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yyf.model.DocType;
import com.yyf.service.DocTypeService;
import com.yyf.service.UserService;

/**
 * struts2 中 json的使用
 * @author yyf
 *
 */
@ParentPackage("json-default")
@Namespace("/json")
@Results({ @Result(name = "json", type = "json", params = { "root", "dataMap" }) })
@Controller
public class JsonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private DocTypeService docTypeService;
	@Autowired
	private UserService userService;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Action(value = "json-users")
	public String json() throws Exception {
		dataMap.put("users", userService.getUsers());
		System.out.println(userService.getUsers());
		return "json";
	}
	/**
	 * 得到分类列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "json-doc-types")
	public String getDocTypeList() throws Exception {
		List<DocType> list =  docTypeService.getParentsType();
		dataMap.put("doctypes",list);
		return "json";
	}
}
