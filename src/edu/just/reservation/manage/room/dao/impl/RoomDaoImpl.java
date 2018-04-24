package edu.just.reservation.manage.room.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.room.dao.RoomDao;
import edu.just.reservation.manage.room.entity.Room;

public class RoomDaoImpl extends BaseDaoImpl<Room> implements RoomDao {

	public List<Room> findAllRoomsWithPicture() {
		Query query = getSession().createQuery("FROM Room WHERE RImgPath IS NOT NULL");
		return query.list();
	}

	public List<Room> findAllRoomsByType(String type) {
		Query query = getSession().createQuery("FROM Room WHERE RType=?");
		query.setParameter(0, type);
		return query.list();
	}

	public Room findRoomByRNumber(String rNumber) {
		Query query = getSession().createQuery("FROM Room WHERE RNumber=?");
		query.setParameter(0, rNumber);
		List<Room> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
