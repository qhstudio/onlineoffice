package com.yyf.service;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yyf.dao.DocDao;
import com.yyf.exception.NoUserExcetion;
import com.yyf.model.Doc;
import com.yyf.model.User;
import com.yyf.utils.FileUtil;

@Service("DocService")
public class DocSeviceImpl implements DocSevice {

	@Resource
	private DocDao dao;

	@Override
	@Transactional(readOnly = true)
	public Page<Doc> getDocRecentPage(Integer pageNum, int defaultPageSize,User user) {
		Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Direction.DESC, "docDate"));
		int authority = 2;
		if(user!=null){
			authority = 3;
		}
		return dao.findByDocAuthorityGreaterThan(authority, pageable);
	}

	@Override
	public Doc addDoc(Doc doc) {
		return dao.save(doc);
	}

	@Override
	public Doc getDocById(Long docId) {
		return dao.findOne(docId);
	}

	@Override
	public Page<Doc> getMyDocs(Long userId, Integer pageNum, int defaultPageSize) {
		Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Direction.DESC, "docDate"));
		return dao.findByOwnUserId(userId, pageable);
	}

	@Override
	public void delete(Long userId, Long docId) throws Exception {
		Doc doc= dao.findOne(docId);
		if(userId == doc.getDocOwnUser().getUserId()){
			File file = new File(FileUtil.RootPath+doc.getDocPath());
			FileUtil.delete(file);
			file = new File(FileUtil.RootPath+doc.getDocPath()+".pdf");
			FileUtil.delete(file);
			file = new File(FileUtil.RootPath+doc.getDocPath()+".swf");
			FileUtil.delete(file);
			dao.delete(docId);
		}else{
			throw new NoUserExcetion();
		}
	}

}
