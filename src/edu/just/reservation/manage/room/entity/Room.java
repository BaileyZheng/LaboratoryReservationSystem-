package edu.just.reservation.manage.room.entity;

import java.util.HashMap;
import java.util.Map;
/**
 * 实验室实体类
 * 实验室包括的信息：
 * 		实验室号、实验室名、实验室类别、实验室容量
 * 		实验室介绍、图片、视频、状态
 * @author 郑蓓蕾
 *
 */
public class Room implements java.io.Serializable {

	// Fields

	private String roomId;// 实验室id
	private Integer RCapacity;// 实验室容量
	private String RImgPath;// 实验室图片路径
	private String RFilePath;// 实验室视频路径
	private String RMemo;// 实验室介绍
	private String RState;// 实验室状态
	private String RNumber;// 实验室号
	private String RName;// 实验室名
	private String RType;// 实验室类别

	public static String ROOM_STATE_VALID = "1";// 有效
	public static String ROOM_STATE_INVALID = "0";// 无效

	public String ROOM_TYPE_JF = "jf";// 机房
	public String ROOM_TYPE_DQSYS = "dqsys";// 电气实验室

	public static Map<String, String> ROOM_TYPE_MAP;

	static {
		ROOM_TYPE_MAP = new HashMap<String, String>();
		ROOM_TYPE_MAP.put("jf", "机房");
		ROOM_TYPE_MAP.put("dqsys", "电气实验室");
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