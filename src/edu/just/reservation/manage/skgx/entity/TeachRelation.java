package edu.just.reservation.manage.skgx.entity;

import java.io.Serializable;

import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.teacher.entity.Teacher;

/**
 * 授课关系实体类
 * 授课关系包含的信息有：
 * 		授课主体：教师、班级
 * 		授课内容：课程
 * 		授课时间：学期
 * 授课关系主要是为了方便教师预约实验室	
 * @author 郑蓓蕾
 *
 */
public class TeachRelation implements Serializable{
	
	private String id;
	private Teacher teacher;
	private Clazz clazz;
	private Course course;
	private String term;
	
	public TeachRelation() {
	}
	public TeachRelation(String id, Teacher teacher, Clazz clazz,
			Course course, String term) {
		this.id = id;
		this.teacher = teacher;
		this.clazz = clazz;
		this.course = course;
		this.term = term;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
}
