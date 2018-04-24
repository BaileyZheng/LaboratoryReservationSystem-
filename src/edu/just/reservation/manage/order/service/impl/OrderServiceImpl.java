package edu.just.reservation.manage.order.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.manage.order.dao.OrderDao;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.order.service.OrderService;
import edu.just.reservation.manage.room.entity.Room;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Orders> implements
		OrderService {
	private OrderDao orderDao;

	@Resource
	public void setOrderDao(OrderDao orderDao) {
		super.setBaseDao(orderDao);
		this.orderDao = orderDao;
	}

	public void deleteByOrderId(String orderId) {
		orderDao.deleteByOrderId(orderId);
	}

	public List<Orders> findByRoomId(String roomId) {
		return orderDao.findByRoomId(roomId);
	}

	public List<Orders> findOrderByTimeAndRoom(String startTime,
			String endTime, Room room) {
		try {
			return orderDao.findOrderByTimeAndRoom(startTime,endTime,room);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<Orders> findOrdersByOTime() {
		return orderDao.findOrdersByOTime();
	}

	public List<Orders> findOrdersByTeacherId(String tid) {
		return orderDao.findOrdersByTeacherId(tid);
	}

	public List<Orders> findSecceedOrderByTeacher(String tid) {
		return orderDao.findSecceedOrderByTeacher(tid);
	}

	public List<Orders> findSucceedOrdersByRelat(String rid) {
		return orderDao.findSucceedOrdersByRelat(rid);
	}

	public boolean judgeIsOk(String tid, Timestamp useDay, int useTime,String ostate) {
		return orderDao.judgeIsOk(tid, useDay, useTime,ostate);
	}
}
