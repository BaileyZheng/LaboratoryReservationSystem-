package edu.just.reservation.manage.teacher.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.teacher.dao.TeacherDao;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.user.entity.User;

public class TeacherDaoImpl extends BaseDaoImpl<Teacher> implements TeacherDao {

	public Teacher findTeacherByAccount(String account) {
		String hql = "FROM Teacher WHERE account=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		List<Teacher> list = query.list();
		Teacher teacher = null;
		if (list != null && list.size() > 0) {
			teacher = list.get(0);
		}
		return teacher;
	}

	public List<Teacher> getAllTeachers() {
		String hql = "FROM Teacher";
		Query query = getSession().createQuery(hql);
		List<Teacher> list = query.list();
		return list;
	}

	public Teacher findTeacherByAccountAndId(String account, String id) {
		String hql = "FROM Teacher WHERE account=? AND id!=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		query.setParameter(1, id);
		List<Teacher> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public boolean isNotExists(Teacher teacher) {
		String hql = "FROM Teacher WHERE account=? AND name=? AND proTitle=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, teacher.getAccount());
		query.setParameter(1, teacher.getName());
		query.setParameter(2, teacher.getProTitle());
		List<Teacher> list = query.list();
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}

	public Teacher findTeacherByUser(User user) {
		String hql="FROM Teacher WHERE user.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, user.getId());
		List<Teacher> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public void update(Teacher teacher){
		String hql = "UPDATE Teacher t SET t.account=?,t.name=?,t.proTitle=?,t.state=? WHERE t.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, teacher.getAccount());
		query.setParameter(1, teacher.getName());
		query.setParameter(2, teacher.getProTitle());
		query.setParameter(3, teacher.getState());
		query.setParameter(4, teacher.getId());
		query.executeUpdate();
	}
}
