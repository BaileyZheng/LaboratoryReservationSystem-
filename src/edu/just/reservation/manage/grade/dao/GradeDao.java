package edu.just.reservation.manage.grade.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;

public interface GradeDao extends BaseDao<Grade> {

	//根据预约信息获取所有成绩
	public List<Grade> findByOrder(String oid);
	//根据成绩到数据库中查到是否存在
	public boolean isExists(Grade grade);
	//根据成绩到数据库中获取成绩对象
	public Grade findByGrade(Grade grade);
	//根据授课关系获取成绩
	public List<Grade> findByRelation(String rid);
	//根据学生和预约信息获取成绩列表
	public Grade findByStudentAndOrder(Student s, Orders orders);
}
