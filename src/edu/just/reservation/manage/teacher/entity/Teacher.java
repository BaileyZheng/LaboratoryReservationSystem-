package edu.just.reservation.manage.teacher.entity;

import java.io.Serializable;

import edu.just.reservation.manage.user.entity.User;

/**
 * 教师实体类
 * 教师的信息：教师工号，教师姓名，教师职称，教师状态（离职，在职，请假....)
 * 与用户表的联系：每当插入一条教师信息，同时插入一条用户信息，教师有用户的id作为外键
 * 在根据教师信息插入用户信息时，将教师的工号作为用户的账号，教师的姓名作为用户名，初始密码是123456，其他不做初始化
 * @author 郑蓓蕾
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
