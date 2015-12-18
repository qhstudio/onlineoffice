package com.yyf.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyf.dao.UserDao;

public class UserDaoTest {

	@Test
	public void testFindOne() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");
//		System.out.println(userDao.findAll());
		System.out.println(userDao.findOne(1l));
	}
	@Test
	public void test() {
		System.out.println("UserDaoTest.test()");
	}

}
