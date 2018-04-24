package edu.just.reservation.manage.grade.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;

public interface GradeDao extends BaseDao<Grade> {

	//����ԤԼ��Ϣ��ȡ���гɼ�
	public List<Grade> findByOrder(String oid);
	//���ݳɼ������ݿ��в鵽�Ƿ����
	public boolean isExists(Grade grade);
	//���ݳɼ������ݿ��л�ȡ�ɼ�����
	public Grade findByGrade(Grade grade);
	//�����ڿι�ϵ��ȡ�ɼ�
	public List<Grade> findByRelation(String rid);
	//����ѧ����ԤԼ��Ϣ��ȡ�ɼ��б�
	public Grade findByStudentAndOrder(Student s, Orders orders);
}
