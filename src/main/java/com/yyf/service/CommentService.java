package com.yyf.service;

import org.springframework.data.domain.Page;

import com.yyf.model.Comment;

public interface CommentService {
	Page<Comment> getCommentPageByDocId(Long id,Integer page,Integer pageMax);
	Comment addComment(Comment c);
}
