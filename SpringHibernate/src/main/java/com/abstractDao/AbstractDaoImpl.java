package com.abstractDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import pageUtil.PageRequest;
import pageUtil.PageResponse;

@Repository
public class AbstractDaoImpl<E extends AbstractEntity> implements AbstractDao<E>{
	
	@PersistenceContext
	protected EntityManager entityManager;
	protected Logger logger = LoggerFactory.getLogger(super.getClass());
	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		ResolvableType resolvableType = ResolvableType.forClass(ClassUtils.getUserClass(super.getClass()));
		return (Class<E>) resolvableType.as(AbstractDao.class).getGeneric(new int[] { 0 }).resolve();
	}

	public AbstractDaoImpl() {
		this.entityClass = getEntityClass();
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return this.entityManager.getCriteriaBuilder();
	}

	@Transactional
	public void delete(E entity) {
		this.entityManager.remove(this.entityManager.find(this.entityClass, entity.getId()));
		this.entityManager.flush();
	}

	@Transactional
	public void delete(List<E> enttities) {
		for (AbstractEntity entity : enttities) {
			this.entityManager.remove(this.entityManager.find(this.entityClass, entity.getId()));
		}
		this.entityManager.flush();
	}

	@Transactional
	public void update(E entity) {
		this.entityManager.merge(entity);
		this.entityManager.flush();
	}
	
	@Transactional	
	public void update(List<E> entities) {
		for (int i = 0; i < entities.size(); ++i) {
			this.entityManager.merge(entities.get(i));
			if (i % 30 == 0) {
				this.entityManager.flush();
				this.entityManager.clear();
			}
		}
		this.entityManager.flush();
	}
	
	@Transactional	
	public void insert(E entity) {
		this.entityManager.persist(entity);
		this.entityManager.flush();
	}
	
	@Transactional
	public void saveOrUpdate(E entity) {
		if (((entity.getId() == null) || (entity.getId().toString().trim().length() <= 0))) {
			this.entityManager.persist(entity);
		} else {
			this.entityManager.merge(entity);
		}
		this.entityManager.flush();
	}
	
	public E findById(Integer id) {
		return this.entityManager.find(this.entityClass, id);
	}

	public List<E> findByHql(String hql) {
		return findByHqlOrSql(true, hql, new HashMap());
	}
	
	public List<E> findByHql(String hql, Map<String, Object> condition) {
		return findByHqlOrSql(true, hql, condition);
	}
	
	public PageResponse<E> findByHql(String hql, PageRequest pageRequest) {
		return findByHqlOrSql(true, hql, pageRequest, new HashMap());
	}
	
	public PageResponse<E> findByHql(String hql, PageRequest pageRequest, Map<String, Object> condition) {
		return findByHqlOrSql(true, hql, pageRequest, condition);
	}
	
	public List<E> findBySql(String sql) {
		return findByHqlOrSql(false, sql, new HashMap());
	}
	
	public List<E> findBySql(String sql, Map<String, Object> condition) {
		return findByHqlOrSql(false, sql, condition);
	}
	
	public PageResponse<E> findBySql(String sql, PageRequest pageRequest) {
		return findByHqlOrSql(false, sql, pageRequest, new HashMap());
	}
	
	public PageResponse<E> findBySql(String sql, PageRequest pageRequest, Map<String, Object> condition) {
		return findByHqlOrSql(false, sql, pageRequest, condition);
	}
	
	@Deprecated
	public PageResponse<E> findByNativeSql(String sql, PageRequest pageRequest, Map<String, Object> condition) {
		Query q = this.entityManager.createNativeQuery(sql);
		((SQLQuery) q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.aliasToBean(this.entityClass));
		if ((condition != null) && (condition.size() > 0)) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		this.logger.debug("find sql==============:{},{}", sql, condition);
		q.setFirstResult(pageRequest.getFirstResult().intValue());
		q.setMaxResults(pageRequest.getPageSize().intValue());
		PageResponse pageResponse = new PageResponse(pageRequest);
		pageResponse.setRecords(q.getResultList());
		pageResponse.setTotalCount(countBySqlOrHql(false, sql, condition));
		return pageResponse;
	}
	
	@Deprecated
	public List<E> findByNativeSqlToList(String sql, Map<String, Object> condition) {
		Query q = this.entityManager.createNativeQuery(sql);
		((SQLQuery) q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.aliasToBean(this.entityClass));
		if ((condition != null) && (condition.size() > 0)) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		this.logger.debug("find sql==============:{},{}", sql, condition);
		return q.getResultList();
	}
	
	public Object getObjectNativeSql(String paramString, Map<String, Object> condition) {
		String sql = "SELECT userId AS id,userName AS name FROM user where userId=:userId";
		Query q = this.entityManager.createNativeQuery(sql);
		((SQLQuery) q.unwrap(SQLQuery.class)).setResultTransformer(Transformers.aliasToBean(this.entityClass));
		if ((condition !=null)&&(condition.size() > 0)) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		return   q.getResultList();
	}

	/*给分页方法服务*/
	private PageResponse<E> findByHqlOrSql(boolean isHQL, String shql, PageRequest pageRequest,
			Map<String, Object> condition) {
		if (!(StringUtils.hasLength(shql)))
			return null;
		Query q = (isHQL) ? this.entityManager.createQuery(shql) : this.entityManager.createNativeQuery(shql, this.entityClass);
		if ((condition != null) && (condition.size() > 0)) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		this.logger.debug("find hql:{},{}", shql, condition);
		q.setFirstResult(pageRequest.getFirstResult().intValue());
		q.setMaxResults(pageRequest.getPageSize().intValue());
		PageResponse pageResponse = new PageResponse(pageRequest);
		pageResponse.setRecords(q.getResultList());
		pageResponse.setTotalCount(countBySqlOrHql(isHQL, shql, condition));
		return pageResponse;
	}
	
	public Query createQuery(String hql) {
		return this.entityManager.createQuery(hql);
	}
	
	public Query createNativeQuery(String sql) {
		return this.entityManager.createNativeQuery(sql);
	}
	
	public TypedQuery<E> createTypedQuery(String hql) {
		return this.entityManager.createQuery(hql, getEntityClass());
	}
	
	public int executeByHql(String hql, Map<String, Object> condition) {
		Query q = createQuery(hql);
		if ((condition != null) && (!(condition.isEmpty()))) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		return q.executeUpdate();
	}
	
	public Object getObjectHql(String hql, Map<String, Object> condition) {
		return getObjectByHqlOrSql(true, hql, condition);
	}
	
	public Object getObjectSql(String sql, Map<String, Object> condition) {
		return getObjectByHqlOrSql(false, sql, condition);
	}
	
	public int executeBySql(String sql, Map<String, Object> condition) {
		Query q = this.entityManager.createNativeQuery(sql, this.entityClass);
		if ((condition != null) && (!(condition.isEmpty()))) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		return q.executeUpdate();
	}
	
	private Long countBySqlOrHql(boolean isHQL, String shql, Map<String, Object> condition) {
		if (shql == null) {
			return Long.valueOf(0L);
		}
		String tmpHql = (isHQL) ? shql : shql.toLowerCase();
		shql = "select count(*) " + shql.substring(tmpHql.indexOf("from"));
		Object object = getObjectByHqlOrSql(isHQL, shql, condition);
		return Long.valueOf(object.toString());
	}
	
	private Object getObjectByHqlOrSql(boolean isHQL, String shql, Map<String, Object> condition) {
		if (!(StringUtils.hasLength(shql)))
			return null;
		Query q = (isHQL) ? this.entityManager.createQuery(shql) : this.entityManager.createNativeQuery(shql);
		if ((condition != null) && (condition.size() > 0)) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		return q.getSingleResult();
	}
	
	/*给findByHql()方法服务*/
	private List<E> findByHqlOrSql(boolean isHQL, String shql, Map<String, Object> condition) {
		if (!(StringUtils.hasLength(shql)))
			return null;
		Query q = (isHQL) ? this.entityManager.createQuery(shql)
				: this.entityManager.createNativeQuery(shql, this.entityClass);
		if ((condition != null) && (condition.size() > 0)) {
			for (String key : condition.keySet()) {
				q.setParameter(key, condition.get(key));
			}
		}
		return q.getResultList();
	}
	
	
}

























