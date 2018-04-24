package edu.just.reservation.tjbb.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Map;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.tjbb.entity.DataStat;

public interface DataStatService extends BaseService<DataStat> {
	
	//����order��Ϣɾ�����Ӧ��ͳ������
	public void deleteByOrder(Orders order);

	//������ֹ���ں�ͳ������ȡ����ʵ����ʹ�����
	public Map<String,String> getRoomsByTime(Timestamp start,Timestamp end,String type);
	
	//����ͳ��ʱ���ʵ���һ�ȡ��ǰʵ����ʹ�����
	public Map<String,String> getMapByRoom(String type, String period,String roomId) throws ParseException;
}
