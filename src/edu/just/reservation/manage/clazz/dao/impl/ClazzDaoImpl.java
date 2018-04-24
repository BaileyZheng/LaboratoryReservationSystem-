package edu.just.reservation.manage.clazz.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.clazz.dao.ClazzDao;
import edu.just.reservation.manage.clazz.entity.Clazz;

public class ClazzDaoImpl extends BaseDaoImpl<Clazz> implements ClazzDao {

	public Clazz findClazzByCNum(String cnum) {
		String hql = "FROM Clazz WHERE cnumber=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, cnum);
		List<Clazz> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
