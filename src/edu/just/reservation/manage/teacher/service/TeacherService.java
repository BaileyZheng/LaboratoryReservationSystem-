package edu.just.reservation.manage.teacher.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.user.entity.User;

public interface TeacherService extends BaseService<Teacher>{

	//���ݽ�ʦ�˺Ų��ҽ�ʦ
	public Teacher findTeacherByAccount(String account);
	
	//����ʦ��Ϣ��ӵ����ݿ⣬ͬʱΪ�˽�ʦ����û���Ϣ��������ʦ��ɫ���ø��˽�ʦ��Ӧ���û�
	public void add(Teacher teacher);
	
	//���½�ʦ��Ϣ������User����Ϣ��������
	public void update(Teacher teacher,User user);
	
	//��ȡ���н�ʦ��Ϣ��������ʦ��Ӧ��User��
	public List<Teacher> getAllTeachers();
	
	//��ȡ������accountƥ�䣬id�봫��id��ƥ��Ľ�ʦ
	public Teacher findTeacherByAccountAndId(String account,String id);
	
	//����ʦ��Ϣ������excel
	public void exportExcel(List<Teacher> list,ServletOutputStream outputStream);
	//����ʦ��Ϣ��excel����
	public void importExcel(File excel, String excelFileName);
	
	//���ݵ�½���û���Ϣ��ȡ�����û���Ӧ�Ľ�ʦ��Ϣ
	public Teacher findTeacherByUser(User user);
	
}
