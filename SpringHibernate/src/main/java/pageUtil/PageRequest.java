package pageUtil;

import java.io.Serializable;

public class PageRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 默认每页数据条数:10, */
	protected static final Integer DEFAULT_PAGE_SIZE = 10;
	/** 默认当前页码:1 */
	protected static final Integer DEFAULT_PAGE_NUMBER = 1;

	// 每页显示数据条数
	private Integer pageSize = new Integer(DEFAULT_PAGE_SIZE);
	// 当前页号
	private Integer currentPage = new Integer(DEFAULT_PAGE_NUMBER);

	public PageRequest() {
		this(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 初始化请求
	 * 
	 * @param currentPage
	 *            当前需要显示的页号(从1开始)
	 * @param pageSize
	 *            每页显示记录数(缺省为10)
	 */
	public PageRequest(Integer currentPage, Integer pageSize) {
		setCurrentPage(currentPage);
		setPageSize(pageSize);
	}

	/** 每页显示记录数:默认为10 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = (pageSize == null || pageSize <= 0) ? DEFAULT_PAGE_SIZE : pageSize;
	}

	/** 每页显示记录数::默认为10 */
	public Integer getPageSize() {
		return pageSize;
	}

	/** 当前页号:默认为1 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = (currentPage == null || currentPage <= 0) ? DEFAULT_PAGE_NUMBER : currentPage;
	}

	/** 当前页号:默认为1 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/** 开始记录:同getOffset */
	public Integer getFirstResult() {
		return (getCurrentPage() - 1) * getPageSize();
	}

	/** 开始记录，偏移位置:同getFirstResult */
	public Integer getOffset() {
		return getFirstResult();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentPage == null) ? 0 : currentPage.hashCode());
		result = prime * result
				+ ((pageSize == null) ? 0 : pageSize.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageRequest other = (PageRequest) obj;
		if (currentPage == null) {
			if (other.currentPage != null)
				return false;
		} else if (!currentPage.equals(other.currentPage))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		return true;
	}	

}
