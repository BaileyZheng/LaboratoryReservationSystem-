package edu.just.reservation.tjbb.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.tjbb.dao.DataStatDao;
import edu.just.reservation.tjbb.entity.DataStat;

public class DataStatDaoImpl extends BaseDaoImpl<DataStat> implements
		DataStatDao {

	public void deleteByOrder(Orders order) {
		String hql = "FROM DataStat WHERE room.roomId=? AND useDay=? AND useTime=? AND useNumber=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, order.getRoom().getRoomId());
		query.setParameter(1, order.getUseDay());
		query.setParameter(2, order.getUseTime());
		query.setParameter(3, order.getR_tea().getClazz().getSnumber());
		List<DataStat> list = query.list();
		if(list!=null&&list.size()>0){
			DataStat ds = list.get(0);
			delete(ds.getId());
		}
	}

	public List<DataStat> findByTime(Timestamp start, Timestamp end) {
		String hql = "FROM DataStat WHERE useDay>=? AND useDay<=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, start);
		query.setParameter(1, end);
		return query.list();
	}

	public List<DataStat> findByTimeAndRoom(Timestamp start, Timestamp end,
			String roomId) {
		String hql = "FROM DataStat WHERE useDay>=? AND useDay<=? AND room.roomId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, start);
		query.setParameter(1, end);
		query.setParameter(2, roomId);
		return query.list();
	}
	
}
