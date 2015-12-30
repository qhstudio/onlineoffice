package com.yyf.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.data.domain.Page;

import com.yyf.model.Doc;
import com.yyf.model.User;

public interface DocSevice {
	/**
	 * 获得分页列表
	 */
	Page<Doc> getDocRecentPage(Integer pageNum, int defaultPageSize,User user);

	Doc addDoc(Doc doc, int i) throws IOException;
	Doc addDoc(Doc doc);
	Doc getDocById(Long docId);

	Page<Doc> getMyDocs(Long userId, Integer pageNum, int defaultPageSize);

	void delete(Long userId, Long docId) throws Exception;

	Page<Doc> getDocRecentPageByType(Integer pageNum, int defaultPageSize, User user, Long docTypeId);

	List<Doc> getSearchResult(String searchInfo) throws IOException, ParseException;
}
