package edu.just.reservation.core.service;

import java.io.Serializable;
import java.util.List;

import edu.just.reservation.core.page.PageResult;
import edu.just.reservation.core.util.QueryHelper;

public interface BaseService<T> {
	// ���
	public void add(T entity);

	// ɾ��
	public void delete(Serializable id);

	// �޸�
	public void update(T entity);

	// ������������
	public T findById(Serializable id);

	// ��������
	public List<T> findAll();

	// ����������ѯ
	public List<T> findByConditions(String hql, List<Object> parameters);

	public List<T> findByConditions(QueryHelper helper);
	//��ҳ������ѯʵ���б�--��ѯ����queryHelper
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
}
