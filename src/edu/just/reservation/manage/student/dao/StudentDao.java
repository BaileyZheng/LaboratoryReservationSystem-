package edu.just.reservation.manage.student.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.student.entity.Student;

public interface StudentDao extends BaseDao<Student>{
	//根据学生学号查询学生信息
	public Student findStudentByAccount(String account);
	
	//获取所有学生信息（包括学生对应的User）
	public List<Student> getAllStudents();
	
	//获取学号与account匹配，id与传入id不匹配的学生
	public Student findStudentByAccountAndId(String account,String id);
	
	//根据学生信息判断当前学生是否已经存在（判断依据：学生学号、学生姓名、学生所在班级）
	public boolean isNotExists(Student student);

}
