package edu.just.reservation.manage.graderate.service;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.order.entity.Orders;

public interface GradeRateService extends BaseService<GradeRate> {

	//����ԤԼ��Ϣ��ȡ��ǰԤԼ��Ϣ�ļƷֱ���
	public GradeRate finByOrder(Orders o);

}
