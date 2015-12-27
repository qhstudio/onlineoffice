package com.yyf.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyf.model.DocType;

public class DocTypeDaoTest {

	ApplicationContext ac = null;
	DocTypeDao dao = null;

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (DocTypeDao) ac.getBean("docTypeDao");
	}

	@Test
	public void test() {

	}

	@Test
	public void testFindAll() {
		System.out.println(dao.findAll());
	}

	@Test
	public void testFindOne() {
		DocType pdocType = dao.findOne(2l);
		System.out.println(pdocType.getParentType());
	}

	@Test
	public void testSave() {
		DocType pdocType = dao.findOne(1l);
		for (int i = 0; i < 6; i++) {
			DocType docType = new DocType();
			docType.setTypeName("JAVA" + i);
			docType.setTypeDesc("JAVA文档" + i);
			docType.setParentType(pdocType);
			dao.save(docType);
		}
	}
}
