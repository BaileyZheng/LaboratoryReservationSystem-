package edu.just.reservation.manage.student.entity;

import java.io.Serializable;

import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.user.entity.User;

/**
 * ѧ��ʵ���� ѧ������Ϣ��ѧ�����ڰ༶��ѧ��ѧ�ţ�ѧ��������ѧ��״̬����У����ѧ�����������....)
 * ���û������ϵ��ÿ������һ��ѧ����Ϣ��ͬʱ����һ���û���Ϣ��ѧ�����û���id��Ϊ���
 * �ڸ���ѧ����Ϣ�����û���Ϣʱ����ѧ����ѧ����Ϊ�û����˺ţ�ѧ����������Ϊ�û�������ʼ������123456������������ʼ��
 * 
 * @author ֣����
 * 
 */
public class Student implements Serializable {
	private String id;
	private String account;
	private String name;
	private String state;
	private User user;
	private Clazz clazz;

	public Student() {
	}

	public Student(String id, String account, String name, String state,
			User user, Clazz clazz) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.state = state;
		this.user = user;
		this.clazz = clazz;
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

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

}
