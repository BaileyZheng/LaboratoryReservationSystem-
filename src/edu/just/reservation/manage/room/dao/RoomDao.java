package edu.just.reservation.manage.room.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.room.entity.Room;

public interface RoomDao extends BaseDao<Room> {
	//��ѯ������ͼƬ��Ϣ��ʵ����
	public List<Room> findAllRoomsWithPicture();
	//����ʵ�������Ͳ�ѯ���и����͵�ʵ����
	public List<Room> findAllRoomsByType(String type);
	//����ʵ���ҺŻ�ȡʵ����
	public Room findRoomByRNumber(String rNumber);
}
