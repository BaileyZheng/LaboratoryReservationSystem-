package edu.just.reservation.manage.order.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.order.dao.OrderDao;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.room.entity.Room;

public class OrderDaoImpl extends BaseDaoImpl<Orders> implements OrderDao {

	public List<Orders> findByOrderId(String id) {
		Query query = getSession().createQuery("FROM Orders WHERE orderId=?");
		query.setParameter(0, id);
		return query.list();
	}

	public void deleteByOrderId(String orderId) {
		Query query = getSession().createQuery("DELETE FROM Orders WHERE orderId = ?");
		query.setParameter(0, orderId);
		query.executeUpdate();
	}

	public List<Orders> findByRoomId(String roomId) {
		String hql = "FROM Orders WHERE room.roomId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, roomId);
		return query.list();
	}

	public List<Orders> findOrderByTimeAndRoom(String start, String end, Room room) {
		String hql = "FROM Orders WHERE useDay>=? AND useDay<=? ";
		start = start+" 00:00:00";
		end = end+" 00:00:00";
		if(room!=null&&StringUtils.isNotBlank(room.getRoomId())){
			hql = hql + "AND room.roomId=? ";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, Timestamp.valueOf(start));
		query.setParameter(1, Timestamp.valueOf(end));
		if(room!=null&&StringUtils.isNotBlank(room.getRoomId())){
			query.setParameter(2, room.getRoomId());
		}
		return query.list();
	}

	public List<Orders> findOrdersByOTime() {
		String hql = "FROM Orders WHERE OState=? OR OState=? ORDER BY orderTime DESC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, Orders.ORDER_STATE_APPLYED);
		query.setParameter(1, Orders.ORDER_STATE_CHECKED);
		return query.list();
	}

	public List<Orders> findOrdersByTeacherId(String tid) {
		String hql = "FROM Orders WHERE r_tea.teacher.id=? ORDER BY orderTime DESC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, tid);
		return query.list();
	}

	public List<Orders> findSecceedOrderByTeacher(String tid) {
		String hql = "FROM Orders WHERE r_tea.teacher.id=? AND OState=? ORDER BY useDay DESC,useTime DESC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, tid);
		query.setParameter(1, Orders.ORDER_STATE_CHECKED);
		return query.list();
	}

	public List<Orders> findSucceedOrdersByRelat(String rid) {
		String hql = "FROM Orders WHERE r_tea.id=? AND OState=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, rid);
		query.setParameter(1, Orders.ORDER_STATE_CHECKED);
		return query.list();
	}

	public boolean judgeIsOk(String tid, Timestamp useDay, int useTime,String ostate) {
		String hql = "FROM Orders WHERE r_tea.teacher.id=? AND useDay=? AND useTime=? AND OState=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, tid);
		query.setParameter(1, useDay);
		query.setParameter(2, useTime);
		query.setParameter(3, ostate);
		List<Orders> list = query.list();
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}

}
