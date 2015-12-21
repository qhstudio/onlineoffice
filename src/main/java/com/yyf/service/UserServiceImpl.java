package com.yyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public List<User> getUsers() {
		return (List<User>) userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findAll(Integer pageNum, int defaultPageSize) {
		Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Direction.DESC, "userId"));
		return userDao.findAll(pageable);
	}

}
