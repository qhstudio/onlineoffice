package com.yyf.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyf.model.Message;

public class MessageDaoTest {

	ApplicationContext ac = null;
	MessageDao dao = null;
	UserDao userDao = null;

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (MessageDao) ac.getBean("messageDao");
		userDao = (UserDao) ac.getBean("userDao");
	}

	@Test
	public void test() {
		
	}

	@Test
	public void testFindAll() {

	}
	
	@Test
	public void testSave() {
		
		for (int i = 0; i < 10; i++) {
			Message m = new Message();
			m.setMsgContent("欢迎您使用中软国际文库系统！"+i);
			m.setMsgDate(new Date());
			m.setMsgFrom(userDao.findOne(2l));
			m.setMsgTo(userDao.findOne(1l));
			dao.save(m);
		}
	}
}
