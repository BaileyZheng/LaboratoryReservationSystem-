package edu.just.reservation.tjbb.dao;

import java.sql.Timestamp;
import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.tjbb.entity.DataStat;

public interface DataStatDao extends BaseDao<DataStat>{
	//根据order信息删除其对应的统计数据
	public void deleteByOrder(Orders order);
	//根据起止日期从统计表中获取所有的实验室的统计信息
	public List<DataStat> findByTime(Timestamp start,Timestamp end);
	//根据起止日期和实验室获取当前实验室使用情况
	public List<DataStat> findByTimeAndRoom(Timestamp start, Timestamp end,	String roomId);
}
