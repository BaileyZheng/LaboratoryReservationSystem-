package edu.just.reservation.manage.student.entity;

import java.io.Serializable;

import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.user.entity.User;

/**
 * 学生实体类 学生的信息：学生所在班级，学生学号，学生姓名，学生状态（在校，休学，当兵，请假....)
 * 与用户表的联系：每当插入一条学生信息，同时插入一条用户信息，学生有用户的id作为外键
 * 在根据学生信息插入用户信息时，将学生的学号作为用户的账号，学生的姓名作为用户名，初始密码是123456，其他不做初始化
 * 
 * @author 郑蓓蕾
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
