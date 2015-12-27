package com.yyf.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyf.model.Role;

public class RoleDaoTest {
	ApplicationContext ac = null;
	RoleDao roleDao = null;

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		roleDao = (RoleDao) ac.getBean("roleDao");
	}

	@Test
	public void test() {
	}
	@Test
	public void testFindAll() {
		System.out.println(roleDao.findAll());
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 10; i++) {
			Role role = new Role();
			role.setRoleName("普通用户"+i);
			role.setRoleDesc("普通账户，包括基本操作"+i);
			roleDao.save(role);
		}

	}
}
