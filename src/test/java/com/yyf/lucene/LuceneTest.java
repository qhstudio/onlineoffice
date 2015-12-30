package com.yyf.lucene;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.yyf.dao.DocDao;
import com.yyf.dao.DocTypeDao;
import com.yyf.dao.UserDao;
import com.yyf.model.Doc;

public class LuceneTest {
	ApplicationContext ac = null;
	DocDao dao = null;
	UserDao userDao = null;
	DocTypeDao typedao = null;

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (DocDao) ac.getBean("docDao");
		userDao = (UserDao) ac.getBean("userDao");
		typedao = (DocTypeDao) ac.getBean("docTypeDao");
	}

	private CreateIndex c = new CreateIndex();

	@Test
	public void test() {
		
	}

	@Test
	public void testFind() throws IOException, ParseException {
		List<Doc> list = c.getDocSearchList(1, "俞育峰的演示自我介绍 使用");
		for (Doc d : list) {
			System.out.println(d.getDocName()+" "+d.getDocDesc()+"");
		}
	}

	@Test
	public void testReCreate() throws IOException {
		c.deleteAll();
		// Pageable pageable = new PageRequest(1, 10, new Sort(Direction.DESC,
		// "docDate"));
		// dao.findByDocAuthorityGreaterThan(3, pageable);
		List<Doc> list = (List<Doc>) dao.findAll();
		for (Doc d : list) {
			c.addIndex(d);
		}
		// c.addIndex(upDoc);
	}
}
