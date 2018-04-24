package edu.just.reservation.manage.student.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.user.entity.User;

public interface StudentService extends BaseService<Student>{

	//根据学生学号查找学生
	public Student findStudentByAccount(String account);
	
	//将学生信息添加到数据库，同时为此学生添加用户信息，并将学生角色设置给此学生对应的用户
	public void add(Student student);
	
	//更新学生信息，并将User的信息继续保存
	public void update(Student student,User user);
	
	//获取所有学生信息（包括学生对应的User）
	public List<Student> getAllStudents();
	
	//获取学号与account匹配，id与传入id不匹配的学生
	public Student findStudentByAccountAndId(String account,String id);
	
	//将学生信息导出到excel
	public void exportExcel(List<Student> list,ServletOutputStream outputStream);
	//将学生信息从excel导入
	public void importExcel(File excel, String excelFileName);

	//将学生名单导出到excel，用于成绩登记
	public void exportStudentExcel(List<Student> stuList,ServletOutputStream outputStream);
	
}
