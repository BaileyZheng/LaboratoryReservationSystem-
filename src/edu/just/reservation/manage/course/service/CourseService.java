package edu.just.reservation.manage.course.service;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.course.entity.Course;

public interface CourseService extends BaseService<Course> {

	//根据课程号获取课程对象
	public Course findCourseByCNum(String cNumber);
	
}
