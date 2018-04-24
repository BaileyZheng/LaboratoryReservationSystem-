package edu.just.reservation.manage.teacher.entity;

import java.io.Serializable;

import edu.just.reservation.manage.user.entity.User;

/**
 * ��ʦʵ����
 * ��ʦ����Ϣ����ʦ���ţ���ʦ��������ʦְ�ƣ���ʦ״̬����ְ����ְ�����....)
 * ���û������ϵ��ÿ������һ����ʦ��Ϣ��ͬʱ����һ���û���Ϣ����ʦ���û���id��Ϊ���
 * �ڸ��ݽ�ʦ��Ϣ�����û���Ϣʱ������ʦ�Ĺ�����Ϊ�û����˺ţ���ʦ��������Ϊ�û�������ʼ������123456������������ʼ��
 * @author ֣����
 * 
 */
public class Teacher implements Serializable {

	private String id;
	private String account;
	private String name;
	private String proTitle;
	private String state;
	private User user;
	public Teacher() {
	}
	public Teacher(String id, String account, String name, String proTitle,
			String state, User user) {
		this.id = id;
		this.account = account;
		this.name = name;
		this.proTitle = proTitle;
		this.state = state;
		this.user = user;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProTitle() {
		return proTitle;
	}
	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
