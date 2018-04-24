package edu.just.reservation.tjbb.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.tjbb.entity.DataStat;

public interface DataStatService extends BaseService<DataStat> {
	
	//根据order信息删除其对应的统计数据
	public void deleteByOrder(Orders order);

	//根据起止日期和统计类别获取所有实验室使用情况
	public Map<String,String> getRoomsByTime(Timestamp start,Timestamp end,String type);
	
	//根据统计时间和实验室获取当前实验室使用情况
	public Map<String,String> getMapByRoom(String type, String period,String roomId) throws ParseException;
}
