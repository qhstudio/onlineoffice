package com.yyf.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name = "comment_info")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;
	private String commentContext;
	private Date commentTime;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "userId")
	private User commentUser;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "docId")
	private Doc commentDoc;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.REMOVE })
	@JoinColumn(name = "parentCommentId")
	private Comment parentComment;

	@OneToMany(cascade = { CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "parentComment")
	private Set<Comment> childrenComment = new HashSet<Comment>();

	public Set<Comment> getChildrenComment() {
		return childrenComment;
	}

	public void setChildrenComment(Set<Comment> childrenComment) {
		this.childrenComment = childrenComment;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentContext() {
		return commentContext;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}
	@JSON(serialize=false)
	public Doc getCommentDoc() {
		return commentDoc;
	}

	public void setCommentDoc(Doc commentDoc) {
		this.commentDoc = commentDoc;
	}

	@JSON(format="yyyy年MM月dd日 HH:mm:ss")
	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public User getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentContext=" + commentContext + ", commentTime=" + commentTime
				+ ", commentUser=" + commentUser + ", parentComment=" + parentComment + "]";
	}
	
	

}
