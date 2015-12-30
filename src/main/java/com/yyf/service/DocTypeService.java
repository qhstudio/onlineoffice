package com.yyf.service;

import java.util.List;

import com.yyf.model.DocType;

public interface DocTypeService {

	List<DocType> getParentsType();

	DocType saveDocType(DocType model);

	void delete(DocType model);
}
