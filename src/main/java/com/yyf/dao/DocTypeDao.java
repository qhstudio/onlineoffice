package com.yyf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yyf.model.DocType;

public interface DocTypeDao extends JpaRepository<DocType, Long> {
	 public List<DocType> findByParentTypeIsNull(); 
}
