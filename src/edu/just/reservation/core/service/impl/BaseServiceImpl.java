package edu.just.reservation.core.service.impl;

import java.io.Serializable;
import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.core.page.PageResult;
import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.core.util.QueryHelper;

public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public void add(T entity) {
		baseDao.add(entity);
	}

	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public T findById(Serializable id) {
		return baseDao.findById(id);
	}

	public List<T> findAll() {
		return baseDao.findAll();
	}

	public List<T> findByConditions(String hql, List<Object> parameters) {
		return baseDao.findByConditions(hql, parameters);
	}

	public List<T> findByConditions(QueryHelper helper) {
		return baseDao.findByConditions(helper);
	}

	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize) {
		return baseDao.getPageResult(queryHelper, pageNo, pageSize);
	}
	
	

}
