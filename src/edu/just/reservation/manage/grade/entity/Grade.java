package edu.just.reservation.manage.grade.entity;

import java.io.Serializable;

import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;
/**
 * 成绩实体类
 * 成绩包含的信息有：
 * 		 学生、
 * 		 预约信息（包括授课关系、上课地点、上课日期、上课时间段（1：上午1~2节课，2：上午3~4节课，3：下午5~6节课，4：下午7~8节课，5：晚上9~10节课））、
 * 		 出勤分数、课堂表现分数、实验情况分数、总分数、备注
 * 		 状态（用于判断是否已经提交，如果仅仅是保存状态，则允许对分数进行修改，否则，提交状态不允许修改）
 * 用于授课过程管理，登记学生的平时成绩，比如出勤、课堂表现、实验情况之类的
 * @author 郑蓓蕾
 *
 */
public class Grade implements Serializable{
	
	private String id;
	private Student student;
	private Orders order;
	private int cq;
	private int kt;
	private int sy;
	private int grade;
	private String memo;
	private String state;
	
	public static String GRADE_STATE_SAVE = "save";
	public static String GRADE_STATE_SUBMIT = "submit";
	public Grade() {}
	public Grade(String id, Student student, Orders order, int cq, int kt,
			int sy, int grade, String memo, String state) {
		this.id = id;
		this.student = student;
		this.order = order;
		this.cq = cq;
		this.kt = kt;
		this.sy = sy;
		this.grade = grade;
		this.memo = memo;
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public int getCq() {
		return cq;
	}
	public void setCq(int cq) {
		this.cq = cq;
	}
	public int getKt() {
		return kt;
	}
	public void setKt(int kt) {
		this.kt = kt;
	}
	public int getSy() {
		return sy;
	}
	public void setSy(int sy) {
		this.sy = sy;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
