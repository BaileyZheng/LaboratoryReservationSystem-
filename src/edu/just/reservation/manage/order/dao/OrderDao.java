package edu.just.reservation.manage.order.dao;

import java.sql.Timestamp;
import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.room.entity.Room;

public interface OrderDao extends BaseDao<Orders>{
	
	public void deleteByOrderId(String orderId);
	
	//根据实验室id获取所有属于当前实验室的预约信息
	public List<Orders> findByRoomId(String roomId);
	
	//根据指定时间段和实验室信息获取预约信息
	public List<Orders> findOrderByTimeAndRoom(String start,String end,Room room);
	
	//获取最新预约信息（审核中和已经审核通过的），按照预约时间排序
	public List<Orders> findOrdersByOTime();
	
	//根据授课关系id获取所有已经审核通过的预约信息
	public List<Orders> findSucceedOrdersByRelat(String rid);
	
	//根据教师id获取教师的所有预约信息，并按预约时间倒序排
	public List<Orders> findOrdersByTeacherId(String tid);
	
	//根据教师id获取已经成功预约的所有信息，并按使用时间倒序排
	public List<Orders> findSecceedOrderByTeacher(String tid);
	
	//根据教师、选择日期、选择时间段判断当前时间是否可以申请实验室
	public boolean judgeIsOk(String tid,Timestamp useDay,int useTime,String ostate);
}
