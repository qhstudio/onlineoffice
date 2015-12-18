package com.yyf.actions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yyf.model.User;
import com.yyf.service.UserService;

/**
 * 用户管理Action
 * @author yyf
 *
 */
@Controller
public class UserAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User model = new User();
	@Autowired
	private UserService userService;
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String input() throws Exception {
		System.out.println(model);
		userService.addUser(model);
		return INPUT;
	}

	@Override
	public User getModel() {
		return model;
	}
	
}
