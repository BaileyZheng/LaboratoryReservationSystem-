package edu.just.reservation.manage.course.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.course.dao.CourseDao;
import edu.just.reservation.manage.course.entity.Course;

public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
	
	public Course findCourseByCNum(String cNumber){
		String hql ="FROM Course WHERE CNumber=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, cNumber);
		List<Course> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
