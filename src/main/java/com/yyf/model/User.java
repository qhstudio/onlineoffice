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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 用户实体
 * 
 * @author yyf
 *
 */
@Entity
@Table(name = "user_info")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String userName;
	private Date userDate;
	private String userPhoto;
	private String userDesc;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "roleId")
	private Role role;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "docForkers")
	private Set<Doc> forkDocs = new HashSet<Doc>();

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "commentUser")
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "msgFrom")
	private Set<Message> sendedMessages = new HashSet<Message>();

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "msgTo")
	private Set<Message> gettedMessages = new HashSet<Message>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	@JSON(serialize=false)
	public Set<Doc> getForkDocs() {
		return forkDocs;
	}

	
	public void setForkDocs(Set<Doc> forkDocs) {
		this.forkDocs = forkDocs;
	}

	@JSON(serialize=false)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@JSON(serialize=false)
	public Set<Message> getSendedMessages() {
		return sendedMessages;
	}

	public void setSendedMessages(Set<Message> sendedMessages) {
		this.sendedMessages = sendedMessages;
	}

	@JSON(serialize=false)
	public Set<Message> getGettedMessages() {
		return gettedMessages;
	}

	
	public void setGettedMessages(Set<Message> gettedMessages) {
		this.gettedMessages = gettedMessages;
	}

}
