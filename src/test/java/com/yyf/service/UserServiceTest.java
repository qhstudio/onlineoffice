package com.yyf.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

	@Test
	public void testAddUser() {
		
	}

	@Test
	public void testGetUser() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) ac.getBean("userService");
		System.out.println(userService.getUser(1l));
	}

	@Test
	public void testDeleteUser() {
		
	}

}
