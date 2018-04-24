package edu.just.reservation.manage.clazz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.manage.clazz.dao.ClazzDao;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.clazz.service.ClazzService;

@Service("clazzService")
public class ClazzServiceImp extends BaseServiceImpl<Clazz> implements
		ClazzService {
	private ClazzDao clazzDao;

	@Resource
	public void setClazzDao(ClazzDao clazzDao) {
		super.setBaseDao(clazzDao);
		this.clazzDao = clazzDao;
	}

	public Clazz findClazzByCNum(String cnum) {
		return clazzDao.findClazzByCNum(cnum);
	}
	
}
