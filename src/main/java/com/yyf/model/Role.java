package com.yyf.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 用户角色实体
 * 
 * @author yyf
 *
 */
@Entity
@Table(name = "role_info")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long RoleId;
	private String RoleName;
	private String RoleDesc;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<User> users = new HashSet<User>();

	public Long getRoleId() {
		return RoleId;
	}

	public void setRoleId(Long roleId) {
		RoleId = roleId;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	@JSON(serialize = false)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getRoleDesc() {
		return RoleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		RoleDesc = roleDesc;
	}

}
