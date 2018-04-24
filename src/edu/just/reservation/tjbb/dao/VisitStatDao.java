package edu.just.reservation.tjbb.dao;

import java.sql.Timestamp;
import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.tjbb.entity.DataStat;
import edu.just.reservation.tjbb.entity.VisitStat;

public interface VisitStatDao extends BaseDao<VisitStat>{

	//����ʱ��λ�ȡ����
	List<VisitStat> findByTime(Timestamp start, Timestamp end);
}
