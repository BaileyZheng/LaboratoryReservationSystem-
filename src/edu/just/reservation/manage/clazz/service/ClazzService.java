package edu.just.reservation.manage.clazz.service;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.clazz.entity.Clazz;

public interface ClazzService extends BaseService<Clazz> {
	//���ݰ༶�Ż�ȡ�༶��Ϣ
	public Clazz findClazzByCNum(String cnum);
}
