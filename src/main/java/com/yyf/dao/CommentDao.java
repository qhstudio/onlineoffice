package com.yyf.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.Comment;
/**
 * 评论Dao
 * @author yyf
 *
 */
public interface CommentDao extends PagingAndSortingRepository<Comment,Long>{
	@Query("select c from Comment c where c.commentDoc.docId = ?1 and c.parentComment is null")
	public Page<Comment> findByCommentDoc(Long docId, Pageable pageable);

}
