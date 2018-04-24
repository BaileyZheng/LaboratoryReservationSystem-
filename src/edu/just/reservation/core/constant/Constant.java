package edu.just.reservation.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	//用户登录信息
	public static String USER = "SYS_USER";
	//教师用户登录
	public static String TEACHER = "SYS_TEACHER";
	//管理员
	public static String ADMIN = "SYS_ADMIN";
	//学期开始时间
	public static String SYS_TIME = "TERM_START_TIME";
	
	/*-------------系统权限集合--------------*/
	public static String PRIVILEGE_XTSY="xtsy";//系统首页
	public static String PRIVILEGE_CGZS="cgzs";//成果展示
	public static String PRIVILEGE_YYXX="yyxx";//预约信息
	public static String PRIVILEGE_SJGL="sjgl";//数据管理
	public static String PRIVILEGE_GRXX="grxx";//个人信息
	public static String PRIVILEGE_SKGL="skgl";//授课管理
	public static String PRIVILEGE_TJBB="tjbb";//统计报表
	public static String PRIVILEGE_XWGG="xwgg";//新闻公告
	
	public static Map<String,String> PRIVILEGE_MAP;
	static{
		PRIVILEGE_MAP = new HashMap<String,String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XTSY, "系统首页");
		PRIVILEGE_MAP.put(PRIVILEGE_CGZS, "成果展示");
		PRIVILEGE_MAP.put(PRIVILEGE_YYXX, "预约信息");
		PRIVILEGE_MAP.put(PRIVILEGE_SJGL, "数据管理");
		PRIVILEGE_MAP.put(PRIVILEGE_GRXX, "个人信息");
		PRIVILEGE_MAP.put(PRIVILEGE_SKGL, "授课管理");
		PRIVILEGE_MAP.put(PRIVILEGE_TJBB, "统计报表");
		PRIVILEGE_MAP.put(PRIVILEGE_XWGG, "新闻公告");
	}
	
}
