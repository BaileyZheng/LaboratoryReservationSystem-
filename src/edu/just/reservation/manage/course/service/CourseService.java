package edu.just.reservation.manage.course.service;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.course.entity.Course;

public interface CourseService extends BaseService<Course> {

	//���ݿγ̺Ż�ȡ�γ̶���
	public Course findCourseByCNum(String cNumber);
	
}
