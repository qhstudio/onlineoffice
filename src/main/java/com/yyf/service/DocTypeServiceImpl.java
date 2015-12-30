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

	@Override
	public DocType saveDocType(DocType model) {
		if(model.getParentType() != null && model.getTypeId() == model.getParentType().getTypeId()){
			model.setParentType(null);
		}
		return docTypeDao.save(model);
	}

	@Override
	public void delete(DocType model) {
		docTypeDao.delete(model.getTypeId());
	}
	
}
