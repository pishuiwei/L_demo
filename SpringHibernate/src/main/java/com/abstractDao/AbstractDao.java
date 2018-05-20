package com.abstractDao;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pageUtil.PageRequest;
import pageUtil.PageResponse;

public interface AbstractDao<E extends AbstractEntity>{
	public void delete(E paramE);
	
	public void delete(List<E> paramList);
	
	public void update(E paramE);
	
	public void update(List<E> paramList);
	
	public void insert(E paramE);
	
	public void saveOrUpdate(E paramE);
	
	public E findById(Integer paramId);
	
	public List<E> findByHql(String paramString);
	
	public List<E> findByHql(String paramString, Map<String, Object> paramMap);
	
	public PageResponse<E> findByHql(String paramString, PageRequest paramPageRequest);
	
	public PageResponse<E> findByHql(String paramString, PageRequest paramPageRequest,Map<String, Object> paramMap);
	
	public List<E> findBySql(String paramString);
	
	public List<E> findBySql(String paramString, Map<String, Object> paramMap);
	
	public PageResponse<E> findBySql(String paramString, PageRequest paramPageRequest);
	
	public PageResponse<E> findBySql(String paramString, PageRequest paramPageRequest,Map<String, Object> paramMap);
	
	/**返回自定义的dto*/
	public PageResponse<E> findByNativeSql(String paramString, PageRequest paramPageRequest,Map<String, Object> paramMap);
	
	/**返回自定义的dto*/
	public List<E> findByNativeSqlToList(String paramString, Map<String, Object> paramMap);
	
	public Query createQuery(String paramString);
	
	public Query createNativeQuery(String paramString);
	
	/**继承与Query*/
	public TypedQuery<E> createTypedQuery(String paramString);
	
	public int executeByHql(String paramString, Map<String, Object> paramMap);
	
	public int executeBySql(String paramString, Map<String, Object> paramMap);
	
	public Object getObjectHql(String paramString, Map<String, Object> paramMap);
	
	public Object getObjectSql(String paramString, Map<String, Object> paramMap);
	
	/**返回自定义的dto*/
	public Object getObjectNativeSql(String paramString, Map<String, Object> paramMap);
	
	
}













