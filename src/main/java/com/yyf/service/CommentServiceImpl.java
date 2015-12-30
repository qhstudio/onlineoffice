package com.yyf.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yyf.dao.CommentDao;
import com.yyf.model.Comment;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Resource
	CommentDao commentDao;

	@Override
	public Page<Comment> getCommentPageByDocId(Long id,Integer page,Integer pageMax) {
		Pageable pageable = new PageRequest(page, pageMax, new Sort(Direction.DESC, "commentTime"));
		Page<Comment> pageComment = commentDao.findByCommentDoc(id, pageable);
		return pageComment;
	}

	@Override
	public Comment addComment(Comment c) {
		return commentDao.save(c);
	}

}
