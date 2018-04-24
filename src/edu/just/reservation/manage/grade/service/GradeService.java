package edu.just.reservation.manage.grade.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;

public interface GradeService extends BaseService<Grade> {

	//����ԤԼ��Ϣ��ȡ���гɼ�
	public List<Grade> findByOrder(String oid);
	//���ݳɼ������ݿ��в鵽�Ƿ����
	public boolean isExists(Grade grade);
	//���ݳɼ������ݿ��л�ȡ�ɼ�����
	public Grade findByGrade(Grade grade);
	//���ɼ���Ϣ������excel
	public void exportExcel(List<Grade> gradeList,ServletOutputStream outputStream);
	//�����ڿι�ϵ��ȡ�ɼ�
	public List<Grade> findByRelation(String rid);
	//����ѧ����ԤԼ��Ϣ��ȡ�ɼ��б�
	public Grade findByStudentAndOrder(Student s, Orders orders);
	//��ͬһ���ڿι�ϵ��ѧ���ɼ�������excel
	public void exportExcelManyGrade(Map<Student, List<Grade>> gradeMap,
			ServletOutputStream outputStream);
	
}
