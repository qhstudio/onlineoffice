package com.yyf.service;

import java.util.List;

import com.yyf.model.Role;

public interface RoleService {
	/**
	 * 查询一个角色信息
	 * @param id
	 * @return
	 */
	Role getRole(Long id);

	/**
	 * 增加一个角色信息
	 * @param role
	 * @return
	 */
	Role addRole(Role role);
	
	/**
	 * 删除一个角色信息
	 * @param id
	 */
	void deleteRole(Long id);
	
	/**
	 * 得到所有角色信息
	 * @return
	 */
	List<Role> getRoleList();
}
