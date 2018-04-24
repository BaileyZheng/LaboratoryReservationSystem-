package edu.just.reservation.manage.student.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.user.entity.User;

public interface StudentService extends BaseService<Student>{

	//����ѧ��ѧ�Ų���ѧ��
	public Student findStudentByAccount(String account);
	
	//��ѧ����Ϣ��ӵ����ݿ⣬ͬʱΪ��ѧ������û���Ϣ������ѧ����ɫ���ø���ѧ����Ӧ���û�
	public void add(Student student);
	
	//����ѧ����Ϣ������User����Ϣ��������
	public void update(Student student,User user);
	
	//��ȡ����ѧ����Ϣ������ѧ����Ӧ��User��
	public List<Student> getAllStudents();
	
	//��ȡѧ����accountƥ�䣬id�봫��id��ƥ���ѧ��
	public Student findStudentByAccountAndId(String account,String id);
	
	//��ѧ����Ϣ������excel
	public void exportExcel(List<Student> list,ServletOutputStream outputStream);
	//��ѧ����Ϣ��excel����
	public void importExcel(File excel, String excelFileName);

	//��ѧ������������excel�����ڳɼ��Ǽ�
	public void exportStudentExcel(List<Student> stuList,ServletOutputStream outputStream);
	
}
