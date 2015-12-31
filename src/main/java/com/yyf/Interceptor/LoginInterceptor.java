package com.yyf.Interceptor;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.yyf.model.User;
import com.yyf.service.UserService;

public class LoginInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
//		if (user == null) {
//			user = userService.getUser(1l);
//			ActionContext.getContext().getSession().put("user", user);
//		}else{
////			System.out.println(user.getUserName());
//		}
		String uri=ServletActionContext.getRequest().getRequestURI();
		if(uri.substring(0, uri.lastIndexOf("/")).endsWith("doc")){
//			System.out.println(uri.substring(0, uri.lastIndexOf("/")));
			if (user == null) {
				return "nouser";
			}
		}
		
		String ret = invocation.invoke();
		
		return ret;
	}

}
