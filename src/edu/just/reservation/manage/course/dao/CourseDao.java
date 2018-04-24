package edu.just.reservation.manage.course.dao;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.course.entity.Course;

public interface CourseDao extends BaseDao<Course> {
	//根据课程号获取课程对象
	public Course findCourseByCNum(String cNumber);
}
