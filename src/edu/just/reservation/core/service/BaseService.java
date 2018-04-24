package edu.just.reservation.core.service;

import java.io.Serializable;
import java.util.List;

import edu.just.reservation.core.page.PageResult;
import edu.just.reservation.core.util.QueryHelper;

public interface BaseService<T> {
	// 添加
	public void add(T entity);

	// 删除
	public void delete(Serializable id);

	// 修改
	public void update(T entity);

	// 根据主键查找
	public T findById(Serializable id);

	// 查找所有
	public List<T> findAll();

	// 根据条件查询
	public List<T> findByConditions(String hql, List<Object> parameters);

	public List<T> findByConditions(QueryHelper helper);
	//分页条件查询实体列表--查询助手queryHelper
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
}
