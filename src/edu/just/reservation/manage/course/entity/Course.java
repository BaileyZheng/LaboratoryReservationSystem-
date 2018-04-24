package edu.just.reservation.manage.course.entity;

/**
 * 课程实体类
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private String courseId;//课程ID
	private String CNumber;//课程号
	private String CName;//课程名
	private String courseState;//课程状态
	
	private static String COURSE_STATE_VALID = "1";//有效
	private static String COURSE_STATE_INVALID = "0";//无效

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