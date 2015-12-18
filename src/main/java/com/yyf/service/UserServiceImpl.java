package com.yyf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.dao.UserDao;
import com.yyf.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public User addUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.delete(id);
	}

}
