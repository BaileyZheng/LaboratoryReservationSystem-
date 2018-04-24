package edu.just.reservation.manage.room.service;

import java.util.List;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.room.entity.Room;

public interface RoomService extends BaseService<Room> {
	//查询所有有图片信息的实验室
	public List<Room> findAllRoomsWithPicture();
	//根据类型查询所有实验室
	public List<Room> findAllRoomsByType(String type);
	//根据实验室号获取实验室
	public Room findRoomByRNumber(String rNumber);
}
