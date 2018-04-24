package edu.just.reservation.manage.graderate.dao.impl;

import java.util.List;

import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.graderate.dao.GradeRateDao;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.order.entity.Orders;

public class GradeRateDaoImpl extends BaseDaoImpl<GradeRate> implements GradeRateDao {

	public GradeRate finByOrder(Orders o) {
		String hql="FROM GradeRate WHERE order.orderId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, o.getOrderId());
		List<GradeRate> list = query.list();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
