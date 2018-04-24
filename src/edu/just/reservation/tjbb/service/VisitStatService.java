package edu.just.reservation.tjbb.service;

import java.text.ParseException;
import java.util.Map;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.tjbb.entity.VisitStat;

public interface VisitStatService extends BaseService<VisitStat> {

	//����ʱ���ȡ��������
	Map<String, String> getDataByPeriod(String period) throws ParseException;
	
}
