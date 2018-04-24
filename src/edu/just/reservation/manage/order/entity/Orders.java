package edu.just.reservation.manage.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.skgx.entity.TeachRelation;

/**
 * ԤԼ��Ϣʵ����
 * ԤԼ��Ϣ������
 * 		�ڿι�ϵ����ʦ���γ̡��༶��
 * 		ʵ����
 * 		ʹ������+ʹ��ʱ��Σ�1������1~2�ڿΣ�2������3~4�ڿΣ�3������5~6�ڿΣ�4������7~8�ڿΣ�5������9~10�ڿΣ�
 * 		ԤԼʱ��
 * 		ԤԼ״̬���ύ���롢���ͨ����ԤԼʧ�ܡ�ʧЧ��
 * 		
 * @author ֣����
 *
 */
public class Orders implements Serializable {

	// Fields

	private String orderId;
	
	private TeachRelation r_tea;//�ڿι�ϵ
	private Room room;//ʵ����
	private Timestamp useDay;//ʹ������
	private int useTime;//ʹ��ʱ���
	private Timestamp orderTime;// ԤԼʱ��
	private String OState;// ԤԼ״̬
	
	private String useTimeStr;
	private String orderTimeStr;

	public static String ORDER_STATE_APPLYED = "1";// �Ѿ�����
	public static String ORDER_STATE_CHECKED = "2";// ��˳ɹ�
	public static String ORDER_STATE_FAILED = "0";// ���ʧ��
	public static String ORDER_STATE_INVALID = "3";// ��Ч

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
		case 1:str="�����1~2�ڿ�";break;
		case 2:str="�����3~4�ڿ�";break;
		case 3:str="�����5~6�ڿ�";break;
		case 4:str="�����7~8�ڿ�";break;
		case 5:str="���ϵ�9~10�ڿ�";break;
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