package edu.just.reservation.manage.clazz.service;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.clazz.entity.Clazz;

public interface ClazzService extends BaseService<Clazz> {
	//根据班级号获取班级信息
	public Clazz findClazzByCNum(String cnum);
}
