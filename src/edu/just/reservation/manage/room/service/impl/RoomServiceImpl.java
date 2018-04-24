package edu.just.reservation.manage.room.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.manage.room.dao.RoomDao;
import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.room.service.RoomService;

@Service("roomService")
public class RoomServiceImpl extends BaseServiceImpl<Room> implements
		RoomService {
	private RoomDao roomDao;

	@Resource
	public void setRoomDao(RoomDao roomDao) {
		super.setBaseDao(roomDao);
		this.roomDao = roomDao;
	}

	public List<Room> findAllRoomsWithPicture() {
		return roomDao.findAllRoomsWithPicture();
	}

	public List<Room> findAllRoomsByType(String type) {
		return roomDao.findAllRoomsByType(type);
	}

	public Room findRoomByRNumber(String rNumber) {
		return roomDao.findRoomByRNumber(rNumber);
	}
}
