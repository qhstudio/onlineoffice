package com.yyf.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.RunPic;
/**
 * 首页滚动图片 Dao
 * @author yyf
 *
 */
public interface RunPicDao extends PagingAndSortingRepository<RunPic,Long>{
	
}
