package edu.just.reservation.manage.teacher.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.user.entity.User;

public interface TeacherDao extends BaseDao<Teacher>{
	//根据教师账号查询教师信息
	public Teacher findTeacherByAccount(String account);
	
	//获取所有教师信息（包括教师对应的User）
	public List<Teacher> getAllTeachers();
	
	//获取工号与account匹配，id与传入id不匹配的教师
	public Teacher findTeacherByAccountAndId(String account,String id);
	
	//根据教师信息判断当前教师是否已经存在（判断依据：教师工号、教师姓名、教师职称）
	public boolean isNotExists(Teacher teacher);

	//根据登陆的用户信息获取到该用户对应的教师信息
	public Teacher findTeacherByUser(User user);
}
