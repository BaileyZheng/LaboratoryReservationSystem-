package edu.just.reservation.manage.student.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.student.dao.StudentDao;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.teacher.entity.Teacher;

public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	public Student findStudentByAccount(String account) {
		String hql = "FROM Student WHERE account=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		List<Student> list = query.list();
		Student student = null;
		if (list != null && list.size() > 0) {
			student = list.get(0);
		}
		return student;
	}

	public List<Student> getAllStudents() {
		String hql = "FROM Student";
		Query query = getSession().createQuery(hql);
		List<Student> list = query.list();
		return list;
	}

	public Student findStudentByAccountAndId(String account, String id) {
		String hql = "FROM Student WHERE account=? AND id!=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		query.setParameter(1, id);
		List<Student> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public boolean isNotExists(Student student) {
		String hql = "FROM Student WHERE account=? AND name=? AND clazz.clazzId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, student.getAccount());
		query.setParameter(1, student.getName());
		query.setParameter(2, student.getClazz().getClazzId());
		List<Student> list = query.list();
		if(list!=null&&list.size()>0){
			return false;
		}
		return true;
	}
	
	public void update(Student student){
		String hql = "UPDATE Student s SET s.account=?,s.name=?,s.clazz.clazzId=?,s.state=? WHERE s.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, student.getAccount());
		query.setParameter(1, student.getName());
		query.setParameter(2, student.getClazz().getClazzId());
		query.setParameter(3, student.getState());
		query.setParameter(4, student.getId());
		query.executeUpdate();
	}

}
