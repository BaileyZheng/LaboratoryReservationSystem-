package edu.just.reservation.manage.grade.entity;

import java.io.Serializable;

import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;
/**
 * �ɼ�ʵ����
 * �ɼ���������Ϣ�У�
 * 		 ѧ����
 * 		 ԤԼ��Ϣ�������ڿι�ϵ���Ͽεص㡢�Ͽ����ڡ��Ͽ�ʱ��Σ�1������1~2�ڿΣ�2������3~4�ڿΣ�3������5~6�ڿΣ�4������7~8�ڿΣ�5������9~10�ڿΣ�����
 * 		 ���ڷ��������ñ��ַ�����ʵ������������ܷ�������ע
 * 		 ״̬�������ж��Ƿ��Ѿ��ύ����������Ǳ���״̬��������Է��������޸ģ������ύ״̬�������޸ģ�
 * �����ڿι��̹����Ǽ�ѧ����ƽʱ�ɼ���������ڡ����ñ��֡�ʵ�����֮���
 * @author ֣����
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
