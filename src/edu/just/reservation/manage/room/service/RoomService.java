package edu.just.reservation.manage.room.service;

import java.util.List;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.room.entity.Room;

public interface RoomService extends BaseService<Room> {
	//��ѯ������ͼƬ��Ϣ��ʵ����
	public List<Room> findAllRoomsWithPicture();
	//�������Ͳ�ѯ����ʵ����
	public List<Room> findAllRoomsByType(String type);
	//����ʵ���ҺŻ�ȡʵ����
	public Room findRoomByRNumber(String rNumber);
}
