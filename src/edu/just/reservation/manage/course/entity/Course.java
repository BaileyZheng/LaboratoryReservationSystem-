package edu.just.reservation.manage.course.entity;

/**
 * �γ�ʵ����
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private String courseId;//�γ�ID
	private String CNumber;//�γ̺�
	private String CName;//�γ���
	private String courseState;//�γ�״̬
	
	private static String COURSE_STATE_VALID = "1";//��Ч
	private static String COURSE_STATE_INVALID = "0";//��Ч

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** full constructor */
	public Course(String CNumber, String CName, String courseState) {
		this.CNumber = CNumber;
		this.CName = CName;
		this.courseState = courseState;
	}

	// Property accessors

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCNumber() {
		return this.CNumber;
	}

	public void setCNumber(String CNumber) {
		this.CNumber = CNumber;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCourseState() {
		return this.courseState;
	}

	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}

}