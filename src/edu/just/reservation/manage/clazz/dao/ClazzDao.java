package edu.just.reservation.manage.clazz.dao;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.clazz.entity.Clazz;

public interface ClazzDao extends BaseDao<Clazz> {
	//根据班级号获取班级信息
	public Clazz findClazzByCNum(String cnum);
}
