package edu.just.reservation.manage.course.dao;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.course.entity.Course;

public interface CourseDao extends BaseDao<Course> {
	//���ݿγ̺Ż�ȡ�γ̶���
	public Course findCourseByCNum(String cNumber);
}
