package com.yyf.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户实体
 * 
 * @author yyf
 *
 */
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String userName;
	private Date userBirth;
	
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "roleId")
	private Role role;

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

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userBirth=" + userBirth + ", role=" + role
				+ "]";
	}
	

}
