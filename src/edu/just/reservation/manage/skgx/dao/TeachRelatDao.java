package edu.just.reservation.manage.skgx.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.teacher.entity.Teacher;

public interface TeachRelatDao extends BaseDao<TeachRelation> {
	//���ݴ�����ڿι�ϵ����鿴��ǰ�ڿι�ϵ�����ݿ����Ƿ��Ѿ�����
	public boolean isNotExists(TeachRelation relation);
	//���ݽ�ʦ��ȡ��ǰ��ʦ�������ڿι�ϵ
	public List<TeachRelation> findAllByTeacher(Teacher teacher);
	//���ݽ�ʦ���γ̺Ͱ༶��ȡ�ڿ���Ϣ
	public TeachRelation findByTeaAndClzAndCour(String tid,String clzId,String couId);
}
