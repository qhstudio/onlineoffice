package com.yyf.service;

import java.util.List;

import org.springframework.data.domain.Page;

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
	
	/**
	 * 得到所有用户
	 */
	
	List<User> getUsers();

	/**
	 * 得到分页用户
	 * @param pageNum
	 * @param defaultPageSize
	 * @return
	 */
	Page<User> findAll(Integer pageNum, int defaultPageSize);
}
