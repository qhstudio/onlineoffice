package com.yyf.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.Doc;
/**
 * 文档Dao
 * @author yyf
 *
 */
public interface DocDao extends PagingAndSortingRepository<Doc,Long>{
	
}
