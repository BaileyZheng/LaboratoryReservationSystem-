package edu.just.reservation.manage.clazz.entity;

import java.util.HashSet;
import java.util.Set;

import edu.just.reservation.manage.student.entity.Student;

/**
 * �༶ʵ���� Clazz entity. @author MyEclipse Persistence Tools
 */

public class Clazz implements java.io.Serializable {

	// Fields

	private String clazzId;// �༶id
	private String cnumber;// �༶��
	private String cname;// רҵ
	private Integer snumber;// �༶����
	private String clazzState;// �༶״̬
	
	private Set<Student> students = new HashSet<Student>();

	private static String CLAZZ_STATE_VALID = "1";// ��Ч
	private static String CLAZZ_STATE_INVALID = "0";// ��Ч

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