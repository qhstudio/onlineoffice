package com.yyf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yyf.model.User;
/**
 * 用户Dao
 * @author yyf
 *
 */
public interface UserDao extends JpaRepository<User,Long>{
	
}
