package edu.just.reservation.manage.grade.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.grade.dao.GradeDao;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;

public class GradeDaoImpl extends BaseDaoImpl<Grade> implements GradeDao {

	public List<Grade> findByOrder(String oid) {
		String hql="FROM Grade WHERE order.orderId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, oid);
		return query.list();
	}

	public boolean isExists(Grade grade) {
		String hql="FROM Grade WHERE student.id=? AND order.orderId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, grade.getStudent().getId());
		query.setParameter(1, grade.getOrder().getOrderId());
		List<Grade> list = query.list();
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

	public Grade findByGrade(Grade grade) {
		String hql="FROM Grade WHERE student.id=? AND order.orderId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, grade.getStudent().getId());
		query.setParameter(1, grade.getOrder().getOrderId());
		List<Grade> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Grade> findByRelation(String rid) {
		String hql="FROM Grade WHERE order.r_tea.id=? GROUP BY order.orderId ORDER BY student.account ASC";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, rid);
		return query.list();
	}

	public Grade findByStudentAndOrder(Student s, Orders orders) {
		String hql="FROM Grade WHERE student.id=? AND order.orderId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, s.getId());
		query.setParameter(1, orders.getOrderId());
		return (Grade) query.list().get(0);
	}

}
