package com.yyf.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yyf.dao.DocTypeDao;
import com.yyf.model.DocType;

@Service("docTypeService")
public class DocTypeServiceImpl implements DocTypeService {
	@Resource
	private DocTypeDao docTypeDao;

	@Override
	public List<DocType> getParentsType() {
		return docTypeDao.findByParentTypeIsNull();
	}
	
}
