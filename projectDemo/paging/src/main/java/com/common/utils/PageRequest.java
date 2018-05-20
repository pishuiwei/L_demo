package com.common.utils;

import java.io.Serializable;

public class PageRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	/*默认每页的数据条数*/
	protected static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(5);
	/*默认从第一条数据开始*/
	protected static final Integer DEFAULT_PAGE_NUMBER = Integer.valueOf(1);
	/*每页显示的数据条数*/
	private Integer pageSize;
	/*页号*/
	private Integer currentPage;
	
	public PageRequest() {
		this(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
	}

	public PageRequest(Integer currentPage, Integer pageSize) {
		this.pageSize = new Integer(DEFAULT_PAGE_SIZE.intValue());
		this.currentPage = new Integer(DEFAULT_PAGE_NUMBER.intValue());
		setCurrentPage(currentPage);
		setPageSize(pageSize);		
	}

	public Integer getPageSize() {
		return pageSize;
	}
	/*页面数据条数*/
	public void setPageSize(Integer pageSize) {
		this.pageSize = ( (pageSize==null || pageSize <= 0) ? DEFAULT_PAGE_SIZE : pageSize);
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	
	/*当前页号*/
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = ((currentPage == null || currentPage <=0) ? DEFAULT_PAGE_NUMBER : currentPage);
	}
	
	/*偏移位置*/
	public Integer getFirstResult() {
		return Integer.valueOf((getCurrentPage().intValue() - 1) * getPageSize().intValue());
	}
	
	/*偏移位置*/
	public Integer getOffset() {
		return getFirstResult();
	}
}











