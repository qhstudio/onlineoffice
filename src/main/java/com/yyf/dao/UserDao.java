package com.yyf.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.User;
/**
 * 用户Dao
 * @author yyf
 *
 */
public interface UserDao extends PagingAndSortingRepository<User,Long>{
	
}
