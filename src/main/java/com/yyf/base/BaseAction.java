package com.yyf.base;

import com.opensymphony.xwork2.ActionSupport;

/**
 * BaseAction
 * 
 * @author yyf
 *
 */
public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分页数据
	 */
	protected static final int DEFAULT_PAGE_NUM = 0;
	protected static final int DEFAULT_PAGE_SIZE = 10;

	protected Integer pageNum;

	public Integer getPageNum() {
		
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		
		this.pageNum = pageNum;
	}

}
