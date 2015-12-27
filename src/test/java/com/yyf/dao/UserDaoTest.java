package com.yyf.dao;

import java.util.Date;
import java.util.List;

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

import com.yyf.dao.UserDao;
import com.yyf.model.Doc;
import com.yyf.model.Message;
import com.yyf.model.Role;
import com.yyf.model.User;

public class UserDaoTest {
	ApplicationContext ac = null;
	UserDao userDao = null;
	RoleDao roleDao = null;
	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		userDao = (UserDao) ac.getBean("userDao");
		roleDao = (RoleDao) ac.getBean("roleDao");
	}

	@After
	public void testAfter() {
	}

	@Test
	public void testFindOne() {
		
		System.out.println(userDao.findOne(1l));
	}

	@Test
	public void testPage() {
		Pageable pageable = new PageRequest(0, 5, new Sort(
                Direction.DESC, "userId"));
		Page<User> page = userDao.findAll(pageable);
		System.out.println(page.getContent());
	}
	
	@Test
	public void testFindAll() {
		List<User> users = (List<User>) userDao.findAll();
		for(User u:users){
			System.out.println(u.getUserName());
		}
	}
	
	@Test
	public void test() {
		
	}
	
	/**
	 * 测试用户收藏
	 */
	@Test
	public void testForks() {
		User u = userDao.findOne(3l);
		for(Doc d:u.getForkDocs()){
			System.out.println(d.getDocName());
		}
	}
	
	@Test
	public void testGetMessage() {
		User u = userDao.findOne(1l);
		for(Message m:u.getSendedMessages()){
			System.out.println(m.getMsgContent());
		}
	}
	
	@Test
	public void testSave() {
		
		
		for (int i = 0; i < 10; i++) {
			User user= new User();
			Role role = roleDao.getOne(1l);
			user.setUserName("用户名"+i);
			user.setUserDesc("我是一个用户，普通的");
			user.setRole(role);
			user.setUserDate(new Date());
			user.setUserPhoto("image/photo.png");
			userDao.save(user);
		}
	}

}
