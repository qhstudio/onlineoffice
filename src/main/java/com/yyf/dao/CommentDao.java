package com.yyf.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.Comment;
/**
 * 评论Dao
 * @author yyf
 *
 */
public interface CommentDao extends PagingAndSortingRepository<Comment,Long>{
	
}
