package com.yyf.dao;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yyf.model.RunPic;

public class RunPicDaoTest {

	ApplicationContext ac = null;
	RunPicDao dao = null;

	@Before
	public void testBefore() {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (RunPicDao) ac.getBean("runPicDao");
	}

	@Test
	public void test() {
		
	}

	@Test
	public void testFindAll() {
		List<RunPic> ps = (List<RunPic>) dao.findAll();
		for(RunPic r:ps){
			System.out.println(r.getRunPicTitle());
		}
		
	}
	
	@Test
	public void testSave() {
		for (int i = 0; i < 10; i++) {
			RunPic pic = new RunPic();
			pic.setRunPicTitle("轮播图"+i);
			pic.setRunPicDate(new Date());
			pic.setRunPicContent("轮播图简介"+i);
			pic.setRunPicPath("image/"+i+".png");
			pic.setIsShow(true);
			dao.save(pic);
		}
	}
}
