package edu.just.reservation.manage.teacher.dao;

import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.user.entity.User;

public interface TeacherDao extends BaseDao<Teacher>{
	//���ݽ�ʦ�˺Ų�ѯ��ʦ��Ϣ
	public Teacher findTeacherByAccount(String account);
	
	//��ȡ���н�ʦ��Ϣ��������ʦ��Ӧ��User��
	public List<Teacher> getAllTeachers();
	
	//��ȡ������accountƥ�䣬id�봫��id��ƥ��Ľ�ʦ
	public Teacher findTeacherByAccountAndId(String account,String id);
	
	//���ݽ�ʦ��Ϣ�жϵ�ǰ��ʦ�Ƿ��Ѿ����ڣ��ж����ݣ���ʦ���š���ʦ��������ʦְ�ƣ�
	public boolean isNotExists(Teacher teacher);

	//���ݵ�½���û���Ϣ��ȡ�����û���Ӧ�Ľ�ʦ��Ϣ
	public Teacher findTeacherByUser(User user);
}
