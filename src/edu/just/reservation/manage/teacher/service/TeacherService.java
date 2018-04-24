package edu.just.reservation.manage.teacher.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.user.entity.User;

public interface TeacherService extends BaseService<Teacher>{

	//根据教师账号查找教师
	public Teacher findTeacherByAccount(String account);
	
	//将教师信息添加到数据库，同时为此教师添加用户信息，并将教师角色设置给此教师对应的用户
	public void add(Teacher teacher);
	
	//更新教师信息，并将User的信息继续保存
	public void update(Teacher teacher,User user);
	
	//获取所有教师信息（包括教师对应的User）
	public List<Teacher> getAllTeachers();
	
	//获取工号与account匹配，id与传入id不匹配的教师
	public Teacher findTeacherByAccountAndId(String account,String id);
	
	//将教师信息导出到excel
	public void exportExcel(List<Teacher> list,ServletOutputStream outputStream);
	//将教师信息从excel导入
	public void importExcel(File excel, String excelFileName);
	
	//根据登陆的用户信息获取到该用户对应的教师信息
	public Teacher findTeacherByUser(User user);
	
}
