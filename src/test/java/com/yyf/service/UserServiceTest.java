package com.yyf.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
	ApplicationContext ac = null;
	UserService userService = null;

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = (UserService) ac.getBean("userService");
	}

	@After
	public void testAfter() {
	}

	@Test
	public void testAddUser() {

	}

	@Test
	public void testGetUser() {
		System.out.println(userService.getUser(1l));
	}

	@Test
	public void testDeleteUser() {

	}

	@Test
	public void findAll() {
		System.out.println(userService.getUsers());
		System.out.println(userService.findAll(0, 5).getContent());
	}
}
