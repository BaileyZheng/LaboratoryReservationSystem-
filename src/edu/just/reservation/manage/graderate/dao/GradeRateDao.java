package edu.just.reservation.manage.graderate.dao;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.order.entity.Orders;

public interface GradeRateDao extends BaseDao<GradeRate> {
	//根据预约信息获取当前预约信息的计分比率
	public GradeRate finByOrder(Orders o);
}
