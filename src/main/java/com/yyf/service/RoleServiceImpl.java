package com.yyf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyf.dao.RoleDao;
import com.yyf.model.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Override
	public Role getRole(Long id) {
		return roleDao.findOne(id);
	}
	
	@Override
	public Role addRole(Role role) {
		return roleDao.save(role);
	}
	
	@Override
	public void deleteRole(Long id) {
		roleDao.delete(id);
	}
	
	@Override
	public List<Role> getRoleList() {
		return roleDao.findAll();
	}

}
