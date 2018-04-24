package edu.just.reservation.manage.skgx.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.skgx.dao.TeachRelatDao;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.teacher.entity.Teacher;

public class TeachRelatDaoImpl extends BaseDaoImpl<TeachRelation> implements
		TeachRelatDao {

	public boolean isNotExists(TeachRelation relation) {
		String hql = "FROM TeachRelation WHERE teacher.id=? AND course.courseId=? AND clazz.clazzId=? AND term=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, relation.getTeacher().getId());
		query.setParameter(1, relation.getCourse().getCourseId());
		query.setParameter(2, relation.getClazz().getClazzId());
		query.setParameter(3, relation.getTerm());
		List<TeachRelation> list = query.list();
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}

	public List<TeachRelation> findAllByTeacher(Teacher teacher) {
		String hql="FROM TeachRelation WHERE teacher.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, teacher.getId());
		return query.list();
	}

	public TeachRelation findByTeaAndClzAndCour(String tid, String clzId,
			String couId) {
		String hql = "FROM TeachRelation WHERE teacher.id=? AND clazz.clazzId=? AND course.courseId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, tid);
		query.setParameter(1, clzId);
		query.setParameter(2, couId);
		return (TeachRelation) query.list().get(0);
	}

}
