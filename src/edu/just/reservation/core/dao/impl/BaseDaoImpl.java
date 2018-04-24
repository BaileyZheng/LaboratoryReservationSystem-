package edu.just.reservation.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.core.page.PageResult;
import edu.just.reservation.core.util.QueryHelper;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements
		BaseDao<T> {
	
	Class<T> clazz;
	
	public BaseDaoImpl(){
		ParameterizedType pt =  (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public void add(T entity) {
		getHibernateTemplate().save(entity);
	}

	public void delete(Serializable id) {
		getHibernateTemplate().delete(findById(id));
	}

	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	public T findById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {
		Query query = getSession().createQuery("FROM "+clazz.getSimpleName());
		return query.list();
	}

	public List<T> findByConditions(String hql, List<Object> parameters) {
		Query query = getSession().createQuery(hql);
		if(parameters!=null){
			for(int i = 0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}

	public List<T> findByConditions(QueryHelper helper) {
		return findByConditions(helper.getHQL(),helper.getParameters());
	}

	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
		Query query = getSession().createQuery(queryHelper.getHQL());
		List<Object> parameters = queryHelper.getParameters();
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		if(pageNo < 1) pageNo = 1;
		
		query.setFirstResult((pageNo-1)*pageSize);//设置数据起始索引号
		query.setMaxResults(pageSize);
		List items = query.list();
		//获取总记录数
		Query queryCount = getSession().createQuery(queryHelper.getCountHQL());
		if(parameters != null){
			for(int i = 0; i < parameters.size(); i++){
				queryCount.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long)queryCount.uniqueResult();
		
		return new PageResult(totalCount, pageNo, pageSize, items);
	}


	
}
