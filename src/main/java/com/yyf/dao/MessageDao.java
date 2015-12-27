package com.yyf.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.Message;
/**
 * 消息Dao
 * @author yyf
 *
 */
public interface MessageDao extends PagingAndSortingRepository<Message,Long>{
	
}
