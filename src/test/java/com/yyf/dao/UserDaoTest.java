package com.yyf.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.yyf.dao.UserDao;
import com.yyf.model.User;

public class UserDaoTest {
	ApplicationContext ac = null;
	UserDao userDao = null;
	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) ac.getBean("userDao");
	}

	@After
	public void testAfter() {
	}

	@Test
	public void testFindOne() {
		
		UserDao userDao = (UserDao) ac.getBean("userDao");
		System.out.println(userDao.findOne(1l));
	}

	@Test
	public void test() {
		Pageable pageable = new PageRequest(0, 5, new Sort(
                Direction.DESC, "userId"));
		Page<User> page = userDao.findAll(pageable);
		System.out.println(page.getContent());
//		System.out.println(page.getContent());
	}

}
