package com.yyf.service;

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

@Service("DocService")
public class DocSeviceImpl implements DocSevice {

	@Resource
	private DocDao dao;

	@Override
	@Transactional(readOnly = true)
	public Page<Doc> getDocPage(Integer pageNum, int defaultPageSize) {
		Pageable pageable = new PageRequest(pageNum, defaultPageSize, new Sort(Direction.DESC, "docDate"));
		return dao.findAll(pageable);
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
			dao.delete(docId);
		}else{
			throw new NoUserExcetion();
		}
	}

}
