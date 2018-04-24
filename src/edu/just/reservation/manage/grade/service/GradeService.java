package edu.just.reservation.manage.grade.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;

public interface GradeService extends BaseService<Grade> {

	//根据预约信息获取所有成绩
	public List<Grade> findByOrder(String oid);
	//根据成绩到数据库中查到是否存在
	public boolean isExists(Grade grade);
	//根据成绩到数据库中获取成绩对象
	public Grade findByGrade(Grade grade);
	//将成绩信息导出到excel
	public void exportExcel(List<Grade> gradeList,ServletOutputStream outputStream);
	//根据授课关系获取成绩
	public List<Grade> findByRelation(String rid);
	//根据学生和预约信息获取成绩列表
	public Grade findByStudentAndOrder(Student s, Orders orders);
	//将同一个授课关系的学生成绩导出到excel
	public void exportExcelManyGrade(Map<Student, List<Grade>> gradeMap,
			ServletOutputStream outputStream);
	
}
