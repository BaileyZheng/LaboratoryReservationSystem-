package edu.just.reservation.manage.info.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Info implements Serializable {
	private String infoId;
	private String type;
	private String source;
	private String title;
	private String content;
	private String creator;
	private String memo;
	private Timestamp createTime;
	private String state;

	public static String INFO_STATUS_DEPLOY = "1";
	public static String INFO_STATUS_STOP = "0";

	public static String INFO_TYPE_TZGG = "tzgg";
	public static String INFO_TYPE_CGZS = "cgzs";
	public static String INFO_TYPE_XWQS = "xwqs";
	public static String INFO_TYPE_SWZL = "swzl";
	public static String INFO_TYPE_XWDT = "xwdt";
	public static String INFO_TYPE_GZZD = "gzzd";

	public static Map<String, String> INFO_TYPE_MAP;

	static {
		INFO_TYPE_MAP = new HashMap<String, String>();
		INFO_TYPE_MAP.put(INFO_TYPE_TZGG, "通知公告");
		INFO_TYPE_MAP.put(INFO_TYPE_CGZS, "成果展示");
		INFO_TYPE_MAP.put(INFO_TYPE_XWQS, "寻物启事");
		INFO_TYPE_MAP.put(INFO_TYPE_SWZL, "失物招领");
		INFO_TYPE_MAP.put(INFO_TYPE_XWDT, "新闻动态");
		INFO_TYPE_MAP.put(INFO_TYPE_GZZD, "规章制度");
	}

	public Info() {
	}

	public Info(String infoId, String type, String source, String title,
			String content, String creator, String memo, Timestamp createTime,
			String state) {
		this.infoId = infoId;
		this.type = type;
		this.source = source;
		this.title = title;
		this.content = content;
		this.creator = creator;
		this.memo = memo;
		this.createTime = createTime;
		this.state = state;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
