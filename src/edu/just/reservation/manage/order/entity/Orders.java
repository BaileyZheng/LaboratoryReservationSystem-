package edu.just.reservation.manage.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.skgx.entity.TeachRelation;

/**
 * 预约信息实体类
 * 预约信息包括：
 * 		授课关系（教师、课程、班级）
 * 		实验室
 * 		使用日期+使用时间段（1：上午1~2节课，2：上午3~4节课，3：下午5~6节课，4：下午7~8节课，5：晚上9~10节课）
 * 		预约时间
 * 		预约状态（提交申请、审核通过、预约失败、失效）
 * 		
 * @author 郑蓓蕾
 *
 */
public class Orders implements Serializable {

	// Fields

	private String orderId;
	
	private TeachRelation r_tea;//授课关系
	private Room room;//实验室
	private Timestamp useDay;//使用日期
	private int useTime;//使用时间段
	private Timestamp orderTime;// 预约时间
	private String OState;// 预约状态
	
	private String useTimeStr;
	private String orderTimeStr;

	public static String ORDER_STATE_APPLYED = "1";// 已经申请
	public static String ORDER_STATE_CHECKED = "2";// 审核成功
	public static String ORDER_STATE_FAILED = "0";// 审核失败
	public static String ORDER_STATE_INVALID = "3";// 无效

	public Orders() {
	}

	public Orders(String orderId, TeachRelation r_tea, Room room,
			Timestamp useDay, int useTime, Timestamp orderTime, String oState) {
		super();
		this.orderId = orderId;
		this.r_tea = r_tea;
		this.room = room;
		this.useDay = useDay;
		this.useTime = useTime;
		this.orderTime = orderTime;
		OState = oState;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public TeachRelation getR_tea() {
		return r_tea;
	}

	public void setR_tea(TeachRelation r_tea) {
		this.r_tea = r_tea;
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

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public String getOState() {
		return OState;
	}

	public void setOState(String oState) {
		OState = oState;
	}

	public String getUseTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDay = sdf.format(useDay);
		String str = "";
		switch(useTime){
		case 1:str="上午第1~2节课";break;
		case 2:str="上午第3~4节课";break;
		case 3:str="下午第5~6节课";break;
		case 4:str="下午第7~8节课";break;
		case 5:str="晚上第9~10节课";break;
		}
		return strDay+" "+str;
	}

	public void setUseTimeStr(String useTimeStr) {
		this.useTimeStr = useTimeStr;
	}

	public String getOrderTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(orderTime);
	}

	public void setOrderTimeStr(String orderTimeStr) {
		this.orderTimeStr = orderTimeStr;
	}

	
}