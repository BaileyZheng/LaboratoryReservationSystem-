package edu.just.reservation.manage.clazz.dao;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.clazz.entity.Clazz;

public interface ClazzDao extends BaseDao<Clazz> {
	//���ݰ༶�Ż�ȡ�༶��Ϣ
	public Clazz findClazzByCNum(String cnum);
}
