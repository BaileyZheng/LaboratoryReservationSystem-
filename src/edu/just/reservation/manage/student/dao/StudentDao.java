package edu.just.reservation.manage.student.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.student.entity.Student;

public interface StudentDao extends BaseDao<Student>{
	//����ѧ��ѧ�Ų�ѯѧ����Ϣ
	public Student findStudentByAccount(String account);
	
	//��ȡ����ѧ����Ϣ������ѧ����Ӧ��User��
	public List<Student> getAllStudents();
	
	//��ȡѧ����accountƥ�䣬id�봫��id��ƥ���ѧ��
	public Student findStudentByAccountAndId(String account,String id);
	
	//����ѧ����Ϣ�жϵ�ǰѧ���Ƿ��Ѿ����ڣ��ж����ݣ�ѧ��ѧ�š�ѧ��������ѧ�����ڰ༶��
	public boolean isNotExists(Student student);

}
