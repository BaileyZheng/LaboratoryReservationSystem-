package edu.just.reservation.manage.room.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.room.entity.Room;

public interface RoomDao extends BaseDao<Room> {
	//查询所有有图片信息的实验室
	public List<Room> findAllRoomsWithPicture();
	//根据实验室类型查询所有该类型的实验室
	public List<Room> findAllRoomsByType(String type);
	//根据实验室号获取实验室
	public Room findRoomByRNumber(String rNumber);
}
