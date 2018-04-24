package edu.just.reservation.manage.graderate.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.manage.graderate.dao.GradeRateDao;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.graderate.service.GradeRateService;
import edu.just.reservation.manage.order.entity.Orders;

@Service("gradeRateService")
public class GradeRateServiceImpl extends BaseServiceImpl<GradeRate> implements
		GradeRateService {

	private GradeRateDao gradeRateDao;
	
	@Resource
	public void setGradeRateDao(GradeRateDao gradeRateDao) {
		super.setBaseDao(gradeRateDao);
		this.gradeRateDao = gradeRateDao;
	}

	public GradeRate finByOrder(Orders o) {
		return gradeRateDao.finByOrder(o);
	}
	
}
