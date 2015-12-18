package com.yyf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yyf.model.Role;
/**
 * 用户角色
 * @author yyf
 *
 */
public interface RoleDao extends JpaRepository<Role,Long>{

}
