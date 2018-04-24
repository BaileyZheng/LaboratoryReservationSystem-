package edu.just.reservation.manage.graderate.service;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.order.entity.Orders;

public interface GradeRateService extends BaseService<GradeRate> {

	//根据预约信息获取当前预约信息的计分比率
	public GradeRate finByOrder(Orders o);

}
