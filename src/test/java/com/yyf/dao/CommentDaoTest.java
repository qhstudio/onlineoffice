package com.yyf.dao;

import java.util.Date;
import java.util.List;

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

public class CommentDaoTest {

	ApplicationContext ac = null;
	CommentDao dao = null;
	UserDao userDao = null;
	DocDao docDao = null;
	

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (CommentDao) ac.getBean("commentDao");
		userDao = (UserDao) ac.getBean("userDao");
		docDao = (DocDao) ac.getBean("docDao");
	}

	@Test
	public void test() {

	}
	
	@Test
	public void testGetPage() {
		Pageable pageable = new PageRequest(0, 10, new Sort(Direction.DESC, "commentTime"));
		Page<Comment> page = dao.findByCommentDoc(152l, pageable);
		for(Comment c:page.getContent()){
			System.out.println(c.getCommentId()+" "+c.getCommentContext());
			System.out.println(c.getParentComment() == null?"无":c.getParentComment().getCommentContext());
//			for(Comment i:c.getChildrenComment()){
//				System.out.println("children:"+i.getCommentId()+" "+i.getCommentContext());
//			}
			System.out.println("---------------------------");
		}
	}
	
	/**
	 * 增加子评论
	 */
	@Test
	public void testAddChildComment() {
//		Doc doc = docDao.findOne(11l);
//		Set<Comment> cs = doc.getDocComments();
//		
		Comment e = new Comment();
		e.setCommentTime(new Date());
		e.setCommentUser(userDao.findOne(1l));
		e.setCommentDoc(dao.findOne(31l).getCommentDoc());
		e.setCommentContext("新增子评论2");
		e.setParentComment(dao.findOne(31l));
//		cs.add(e);
		dao.save(e);
		
	}
	
	/**
	 * 增加父评论
	 */
	@Test
	public void testAddComment() {
		Doc doc = docDao.findOne(11l);
		Comment e = new Comment();
		e.setCommentTime(new Date());
		e.setCommentUser(userDao.findOne(1l));
		e.setCommentDoc(doc);
		e.setCommentContext("新增评论yyf");
		dao.save(e);
		
	}

	@Test
	public void testFindAll() {
			List<Comment> cs = (List<Comment>) dao.findAll();
			for(Comment c:cs){
				System.out.println(c.getCommentId()+" "+c.getCommentContext());
				System.out.println(c.getParentComment() == null?"无":c.getParentComment().getCommentContext());
//				for(Comment i:c.getChildrenComment()){
//					System.out.println("children:"+i.getCommentId()+" "+i.getCommentContext());
//				}
				System.out.println("---------------------------");
			}
	}

	@Test
	public void testSave() {
		for (int i = 0; i < 10; i++) {
			Comment c = new Comment();
			c.setCommentTime(new Date());
			c.setCommentUser(userDao.findOne(1l+i));
			c.setCommentDoc(docDao.findOne(11l));
			c.setCommentContext("我也是！");
			c.setParentComment(dao.findOne(1l+i));
			dao.save(c);
		}
	}
}
