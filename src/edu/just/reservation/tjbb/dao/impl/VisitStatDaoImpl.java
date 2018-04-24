package edu.just.reservation.tjbb.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.tjbb.dao.VisitStatDao;
import edu.just.reservation.tjbb.entity.VisitStat;

public class VisitStatDaoImpl extends BaseDaoImpl<VisitStat> implements
		VisitStatDao {

	public List<VisitStat> findByTime(Timestamp start, Timestamp end) {
		String hql = "FROM VisitStat WHERE visitTime>=? AND visitTime<=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, start);
		query.setParameter(1, end);
		return query.list();
	}
	
}
