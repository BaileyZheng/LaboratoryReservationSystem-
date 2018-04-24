package edu.just.reservation.manage.clazz.entity;

import java.util.HashSet;
import java.util.Set;

import edu.just.reservation.manage.student.entity.Student;

/**
 * 班级实体类 Clazz entity. @author MyEclipse Persistence Tools
 */

public class Clazz implements java.io.Serializable {

	// Fields

	private String clazzId;// 班级id
	private String cnumber;// 班级号
	private String cname;// 专业
	private Integer snumber;// 班级人数
	private String clazzState;// 班级状态
	
	private Set<Student> students = new HashSet<Student>();

	private static String CLAZZ_STATE_VALID = "1";// 有效
	private static String CLAZZ_STATE_INVALID = "0";// 无效

	// Constructors

	/** default constructor */
	public Clazz() {
	}

	/** full constructor */
	public Clazz(String clazzId, String cnumber, String cname,
			Integer snumber, String clazzState) {
		this.clazzId = clazzId;
		this.cnumber = cnumber;
		this.cname = cname;
		this.snumber = snumber;
		this.clazzState = clazzState;
	}

	// Property accessors

	public String getClazzId() {
		return this.clazzId;
	}

	public void setClazzId(String clazzId) {
		this.clazzId = clazzId;
	}

	public String getCnumber() {
		return this.cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public Integer getSnumber() {
		return this.snumber;
	}

	public void setSnumber(Integer snumber) {
		this.snumber = snumber;
	}

	public String getClazzState() {
		return this.clazzState;
	}

	public void setClazzState(String clazzState) {
		this.clazzState = clazzState;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	
}