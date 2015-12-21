package com.yyf.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yyf.model.User;
import com.yyf.service.UserService;

/**
 * 用户管理Action
 * 
 * @author yyf
 *
 */
@Namespace("/user")
@ParentPackage("struts-default")
@Controller
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 5;
    
	private static final long serialVersionUID = 1L;
	
	protected Integer pageNum;
	
	

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	private User model = new User();
	@Autowired
	private UserService userService;

	@Override
	public User getModel() {
		return model;
	}

	/**
	 * 用户列表页面
	 */
	@Override
	@Action(value = "user-list", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/user/user-list.jsp") })
	public String execute() throws Exception {
		
		if(pageNum == null){
			pageNum = DEFAULT_PAGE_NUM;
		}
		
        Page<User> page = userService.findAll(pageNum, DEFAULT_PAGE_SIZE);
        
		ActionContext.getContext().put("page", page);
		
		return SUCCESS;
	}
	
	/**
	 * 用户增加页面
	 */
	@Action(value = "user-input", results = {
			@Result(name = "success", type = "dispatcher", location = "/WEB-INF/content/user/user-input.jsp") })
	public String doSave() throws Exception {
		return SUCCESS;
	}

	/**
	 * 用户数据提交
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "user-input-submit", results = {
			@Result(name = "success", type = "redirectAction", location = "user-input", params = {}) })
	public String doInput() throws Exception {
		System.out.println(model);
		userService.addUser(model);
		return SUCCESS;
	}

}
