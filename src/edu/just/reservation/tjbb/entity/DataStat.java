package edu.just.reservation.tjbb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import edu.just.reservation.manage.room.entity.Room;

/**
 * ����ͳ��ʵ����---��Ӧ����ͳ�Ʊ�
 * ����ͳ�ư�����
 * 		����ͳ�Ƶ�ʵ����
 * 		ʵ����ʹ�����ڣ����ڰ�����ͳ�����ݣ�
 * 		ʵ����ʹ��ʱ��Σ����ڰ�ʹ��ʱ���ͳ�ƣ�
 * 		ʵ����ʹ�õ����������ڰ�����ͳ�ƣ�
 * 
 * ͳ�������ڹ���Ա���ͨ��ԤԼ��Ϣʱ����,����ʦ�û�ȡ������ԤԼʱɾ��
 * @author ֣����
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
