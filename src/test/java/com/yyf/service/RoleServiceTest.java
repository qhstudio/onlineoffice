package com.yyf.service;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RoleServiceTest {

	@Test
	public void testGetRole() {
		
	}

	@Test
	public void testAddRole() {
		
	}

	@Test
	public void testDeleteRole() {
		
	}

	@Test
	public void testGetRoleList() {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		RoleService roleService = (RoleService) ac.getBean("roleService");
		System.out.println(roleService.getRoleList());
	}
	
	
}
