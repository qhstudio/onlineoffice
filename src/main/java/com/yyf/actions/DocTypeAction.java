package com.yyf.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.yyf.base.BaseAction;

@Namespace("/admin")
@ParentPackage("default")
public class DocTypeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	@Action(value = "sort-manage", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/admin/sort-manage.jsp") })
	public String doToSortUI() throws Exception {
		
		return SUCCESS;
	}

}
