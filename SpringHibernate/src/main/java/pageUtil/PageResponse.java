package pageUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final PageRequest pageRequest;
	/** 当前页数据(list) */
	private List<T> records = new ArrayList<T>();
	/** 总记录数:默认为:0 */
	private Long totalCount = 0l;
	
	public PageResponse(PageRequest pageRequest) {
		if (pageRequest == null) {
			throw new IllegalArgumentException("PageRequest");
		}
		this.pageRequest = pageRequest;
	}
	
	/** 当前页记录列表 */
	public List<T> getRecords() {
		return records;
	}

	/** 当前页记录列表 */
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	/** 总页数 */
	public long getTotalPage() {
		if (totalCount % pageRequest.getPageSize() == 0) {
			return totalCount / pageRequest.getPageSize();// 能整除
		} else {// 不能整除需要加1
			return (totalCount / pageRequest.getPageSize()) + 1;
		}
	}

	/**获取总记录数 */
	public Long getTotalCount() {
		return totalCount;
	}

	/**设置总记录数 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	/**获取每页显示记录数::默认为10 */
	public Integer getpageSize() {
		return pageRequest.getPageSize();
	}

	/**设置当前页号:默认为1 */
	public Integer getCurrentPage() {
		return pageRequest.getCurrentPage();
	}

	@Override
	public String toString() {
		return "PageResponse [pageRequest=" + pageRequest + ", records=" + records + ", totalCount=" + totalCount + "]";
	}

}
