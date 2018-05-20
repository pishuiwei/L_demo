package com.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private final PageRequest pageRequest;
	/*当前页数据*/
	private List<T> records = new ArrayList<T>();
	/*总记录数*/
	private int totalCount = 0;

	public PageResponse(PageRequest pageRequest) {
		if(pageRequest == null){
			throw new IllegalArgumentException("PageRequest");
		}
		this.pageRequest = pageRequest;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	/*获取总页数*/
	public int getTotalPage() {
		if(this.totalCount % this.pageRequest.getPageSize().intValue() == 0){
			return (this.totalCount / this.pageRequest.getPageSize().intValue());
		}
		return (this.totalCount / this.pageRequest.getPageSize().intValue() + 1);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getpageSize() {
		return this.pageRequest.getPageSize();
	}

	public Integer getCurrentPage() {
		return this.pageRequest.getCurrentPage();
	}	

}













