package edu.just.reservation.manage.room.entity;

import java.util.HashMap;
import java.util.Map;
/**
 * ʵ����ʵ����
 * ʵ���Ұ�������Ϣ��
 * 		ʵ���Һš�ʵ��������ʵ�������ʵ��������
 * 		ʵ���ҽ��ܡ�ͼƬ����Ƶ��״̬
 * @author ֣����
 *
 */
public class Room implements java.io.Serializable {

	// Fields

	private String roomId;// ʵ����id
	private Integer RCapacity;// ʵ��������
	private String RImgPath;// ʵ����ͼƬ·��
	private String RFilePath;// ʵ������Ƶ·��
	private String RMemo;// ʵ���ҽ���
	private String RState;// ʵ����״̬
	private String RNumber;// ʵ���Һ�
	private String RName;// ʵ������
	private String RType;// ʵ�������

	public static String ROOM_STATE_VALID = "1";// ��Ч
	public static String ROOM_STATE_INVALID = "0";// ��Ч

	public String ROOM_TYPE_JF = "jf";// ����
	public String ROOM_TYPE_DQSYS = "dqsys";// ����ʵ����

	public static Map<String, String> ROOM_TYPE_MAP;

	static {
		ROOM_TYPE_MAP = new HashMap<String, String>();
		ROOM_TYPE_MAP.put("jf", "����");
		ROOM_TYPE_MAP.put("dqsys", "����ʵ����");
	}

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */

	public Room(String roomId, Integer rCapacity, String rImgPath,
			String rFilePath, String rMemo, String rState, String rNumber,
			String rName, String rType) {
		this.roomId = roomId;
		RCapacity = rCapacity;
		RImgPath = rImgPath;
		RFilePath = rFilePath;
		RMemo = rMemo;
		RState = rState;
		RNumber = rNumber;
		RName = rName;
		RType = rType;
	}

	// Property accessors

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getRCapacity() {
		return this.RCapacity;
	}

	public void setRCapacity(Integer RCapacity) {
		this.RCapacity = RCapacity;
	}

	public String getRImgPath() {
		return this.RImgPath;
	}

	public void setRImgPath(String RImgPath) {
		this.RImgPath = RImgPath;
	}

	public String getRFilePath() {
		return this.RFilePath;
	}

	public void setRFilePath(String RFilePath) {
		this.RFilePath = RFilePath;
	}

	public String getRMemo() {
		return this.RMemo;
	}

	public void setRMemo(String RMemo) {
		this.RMemo = RMemo;
	}

	public String getRState() {
		return this.RState;
	}

	public void setRState(String RState) {
		this.RState = RState;
	}

	public String getRNumber() {
		return this.RNumber;
	}

	public void setRNumber(String RNumber) {
		this.RNumber = RNumber;
	}

	public String getRName() {
		return this.RName;
	}

	public void setRName(String RName) {
		this.RName = RName;
	}

	public String getRType() {
		return RType;
	}

	public void setRType(String rType) {
		RType = rType;
	}

}