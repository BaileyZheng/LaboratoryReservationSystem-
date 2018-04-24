package edu.just.reservation.tjbb.dao;

import java.sql.Timestamp;
import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.tjbb.entity.DataStat;

public interface DataStatDao extends BaseDao<DataStat>{
	//����order��Ϣɾ�����Ӧ��ͳ������
	public void deleteByOrder(Orders order);
	//������ֹ���ڴ�ͳ�Ʊ��л�ȡ���е�ʵ���ҵ�ͳ����Ϣ
	public List<DataStat> findByTime(Timestamp start,Timestamp end);
	//������ֹ���ں�ʵ���һ�ȡ��ǰʵ����ʹ�����
	public List<DataStat> findByTimeAndRoom(Timestamp start, Timestamp end,	String roomId);
}
