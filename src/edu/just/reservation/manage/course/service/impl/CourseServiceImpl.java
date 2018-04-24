package edu.just.reservation.manage.course.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.manage.course.dao.CourseDao;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.course.service.CourseService;

@Service("courseService")
public class CourseServiceImpl extends BaseServiceImpl<Course> implements
		CourseService {
	private CourseDao courseDao;

	@Resource
	public void setCourseDao(CourseDao courseDao) {
		super.setBaseDao(courseDao);
		this.courseDao = courseDao;
	}

	public Course findCourseByCNum(String cNumber) {
		return courseDao.findCourseByCNum(cNumber);
	}
}
