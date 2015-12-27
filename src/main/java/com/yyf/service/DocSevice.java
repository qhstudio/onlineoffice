package com.yyf.service;

import org.springframework.data.domain.Page;

import com.yyf.model.Doc;

public interface DocSevice {
	/**
	 * 获得分页列表
	 */
	Page<Doc> getDocPage(Integer pageNum, int defaultPageSize);

	Doc addDoc(Doc doc);

	Doc getDocById(Long docId);

	Page<Doc> getMyDocs(Long userId, Integer pageNum, int defaultPageSize);

	void delete(Long userId, Long docId) throws Exception;
}
