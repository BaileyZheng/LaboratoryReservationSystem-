package edu.just.reservation.manage.graderate.dao;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.order.entity.Orders;

public interface GradeRateDao extends BaseDao<GradeRate> {
	//����ԤԼ��Ϣ��ȡ��ǰԤԼ��Ϣ�ļƷֱ���
	public GradeRate finByOrder(Orders o);
}
