package com.yyf.service;

import com.yyf.model.User;

public interface UserService {
	/**
	 * 增加一个用户
	 * @param user
	 */
	User addUser(User user);
	
	/**
	 * 得到一个用户
	 * @param id
	 * @return
	 */
	User getUser(Long id);
	
	/**
	 * 删除一个用户
	 * @param id
	 */
	void deleteUser(Long id);
}
