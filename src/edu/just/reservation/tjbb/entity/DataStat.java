package edu.just.reservation.tjbb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import edu.just.reservation.manage.room.entity.Room;

/**
 * 数据统计实体类---对应数据统计表
 * 数据统计包含：
 * 		参与统计的实验室
 * 		实验室使用日期（用于按日期统计数据）
 * 		实验室使用时间段（用于按使用时间段统计）
 * 		实验室使用的人数（用于按人数统计）
 * 
 * 统计数据在管理员审核通过预约信息时生成,当教师用户取消申请预约时删除
 * @author 郑蓓蕾
 *
 */
public class DataStat implements Serializable{
	private String id;
	private Room room;
	private Timestamp useDay;
	private int useTime;
	private int useNumber;
	
	public DataStat() {
	}
	
	public DataStat(String id, Room room, Timestamp useDay, int useTime,
			int useNumber) {
		this.id = id;
		this.room = room;
		this.useDay = useDay;
		this.useTime = useTime;
		this.useNumber = useNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Timestamp getUseDay() {
		return useDay;
	}
	public void setUseDay(Timestamp useDay) {
		this.useDay = useDay;
	}
	public int getUseTime() {
		return useTime;
	}
	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}
	public int getUseNumber() {
		return useNumber;
	}
	public void setUseNumber(int useNumber) {
		this.useNumber = useNumber;
	}
	
	
	
}
