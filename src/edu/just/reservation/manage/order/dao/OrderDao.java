package edu.just.reservation.manage.order.dao;

import java.sql.Timestamp;
import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.room.entity.Room;

public interface OrderDao extends BaseDao<Orders>{
	
	public void deleteByOrderId(String orderId);
	
	//����ʵ����id��ȡ�������ڵ�ǰʵ���ҵ�ԤԼ��Ϣ
	public List<Orders> findByRoomId(String roomId);
	
	//����ָ��ʱ��κ�ʵ������Ϣ��ȡԤԼ��Ϣ
	public List<Orders> findOrderByTimeAndRoom(String start,String end,Room room);
	
	//��ȡ����ԤԼ��Ϣ������к��Ѿ����ͨ���ģ�������ԤԼʱ������
	public List<Orders> findOrdersByOTime();
	
	//�����ڿι�ϵid��ȡ�����Ѿ����ͨ����ԤԼ��Ϣ
	public List<Orders> findSucceedOrdersByRelat(String rid);
	
	//���ݽ�ʦid��ȡ��ʦ������ԤԼ��Ϣ������ԤԼʱ�䵹����
	public List<Orders> findOrdersByTeacherId(String tid);
	
	//���ݽ�ʦid��ȡ�Ѿ��ɹ�ԤԼ��������Ϣ������ʹ��ʱ�䵹����
	public List<Orders> findSecceedOrderByTeacher(String tid);
	
	//���ݽ�ʦ��ѡ�����ڡ�ѡ��ʱ����жϵ�ǰʱ���Ƿ��������ʵ����
	public boolean judgeIsOk(String tid,Timestamp useDay,int useTime,String ostate);
}
