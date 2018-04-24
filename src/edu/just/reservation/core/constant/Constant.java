package edu.just.reservation.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	//�û���¼��Ϣ
	public static String USER = "SYS_USER";
	//��ʦ�û���¼
	public static String TEACHER = "SYS_TEACHER";
	//����Ա
	public static String ADMIN = "SYS_ADMIN";
	//ѧ�ڿ�ʼʱ��
	public static String SYS_TIME = "TERM_START_TIME";
	
	/*-------------ϵͳȨ�޼���--------------*/
	public static String PRIVILEGE_XTSY="xtsy";//ϵͳ��ҳ
	public static String PRIVILEGE_CGZS="cgzs";//�ɹ�չʾ
	public static String PRIVILEGE_YYXX="yyxx";//ԤԼ��Ϣ
	public static String PRIVILEGE_SJGL="sjgl";//���ݹ���
	public static String PRIVILEGE_GRXX="grxx";//������Ϣ
	public static String PRIVILEGE_SKGL="skgl";//�ڿι���
	public static String PRIVILEGE_TJBB="tjbb";//ͳ�Ʊ���
	public static String PRIVILEGE_XWGG="xwgg";//���Ź���
	
	public static Map<String,String> PRIVILEGE_MAP;
	static{
		PRIVILEGE_MAP = new HashMap<String,String>();
		PRIVILEGE_MAP.put(PRIVILEGE_XTSY, "ϵͳ��ҳ");
		PRIVILEGE_MAP.put(PRIVILEGE_CGZS, "�ɹ�չʾ");
		PRIVILEGE_MAP.put(PRIVILEGE_YYXX, "ԤԼ��Ϣ");
		PRIVILEGE_MAP.put(PRIVILEGE_SJGL, "���ݹ���");
		PRIVILEGE_MAP.put(PRIVILEGE_GRXX, "������Ϣ");
		PRIVILEGE_MAP.put(PRIVILEGE_SKGL, "�ڿι���");
		PRIVILEGE_MAP.put(PRIVILEGE_TJBB, "ͳ�Ʊ���");
		PRIVILEGE_MAP.put(PRIVILEGE_XWGG, "���Ź���");
	}
	
}
