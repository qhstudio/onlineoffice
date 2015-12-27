package com.yyf.dao;

import java.io.File;
import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.yyf.model.Comment;
import com.yyf.model.Doc;
import com.yyf.model.User;
import com.yyf.utils.FileUtil;

public class DocDaoTest {

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

	@Test
	public void test() {
		
	}
	
	@Test
	public void testDel() {
		Doc doc = dao.findOne(112l);
//		dao.delete(doc);
		File file = new File(FileUtil.RootPath+doc.getDocPath()+".png");
		file.delete();
		System.out.println(file);
	}
	
	@Test
	public void testComment() {
		for(Comment c:dao.findOne(11l).getDocComments()){
			System.out.println(c.getCommentContext());
		}
			
	}
	@Test
	public void findOneByCondation() {
		Doc d = dao.findByOwnUserId(2l);
		System.out.println(d.getDocName());
	}
	
	@Test
	public void testFindAllByCondation() {
		
		Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "docDate"));
		Page<Doc> page = dao.findByDocAuthorityGreaterThan(2, pageable);
		for(Doc d:page.getContent()){
			System.out.println(d.getDocName()+" "+d.getDocAuthority());
		}
	}
	
	@Test
	public void testFindAll() {
		Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "docDate"));
		
		Page<Doc> page = dao.findAll(pageable);
		for(Doc d:page.getContent()){
			System.out.println(d.getDocName());
		}
		
	}
	
	@Test
	public void testSave() {
		
		for (int i = 0; i < 10; i++) {
			Doc doc = new Doc();
			doc.setDocDate(new Date());
			doc.setDocPath("G://download/"+i+".doc");
			doc.setDocName("OpenStack高可用核心架构分析"+i);
			doc.setDocDesc("OpenStack实际上是由众多服务组合而成，它们之间的关联或多或少，而且具有一定的层次关系，每个服务就像积木块一样，你可以根据实际需要进行取舍并组合搭建，因此良好的运营架构整合能力是应用OpenStack的前提。");
			doc.setDocSize(1182f);
			doc.setDocOwnUser(userDao.findOne(9l-i));
			doc.setDocType(typedao.findOne(0l+i));
			if(i%2 == 0){
				doc.setDocFoot("ppt");
			}else{
				doc.setDocFoot("doc");
			}
			
//			doc.setDocForkers(docForkers);
			dao.save(doc);
		}
	}
	
	/**
	 * 测试收藏
	 */
	@Test
	public void testAddFork() {
		Doc doc = dao.findOne(11l);
		Set<User> docForkers = doc.getDocForkers();
		docForkers.add(userDao.findOne(3l));
		docForkers.add(userDao.findOne(5l));
		doc.setDocForkers(docForkers);
		dao.save(doc);
	}
	
	public static void main(String[] args) {
//		ApplicationContext ac = null;
//		DocDao dao = null;
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		dao = (DocDao) ac.getBean("docDao");
//		Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "docDate"));
//		Page<Doc> page = dao.findByOwnUserId(2l, pageable);
//		
//		for(Doc d:page.getContent()){
//			System.out.println(d.getDocName()+" - "+d.getDocOwnUser().getUserName());
//		}
	}
}
