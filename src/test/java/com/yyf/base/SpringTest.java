package com.yyf.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyf.dao.UserDao;

public class SpringTest{
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) ac.getBean("userDao");
		System.out.println(userDao);
	}
}
