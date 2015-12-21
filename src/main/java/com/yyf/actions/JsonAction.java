package com.yyf.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
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
}
