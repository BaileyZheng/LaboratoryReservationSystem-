package edu.just.reservation.manage.skgx.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.teacher.entity.Teacher;

public interface TeachRelatService extends BaseService<TeachRelation> {

	//���ݴ�����ڿι�ϵ����鿴��ǰ�ڿι�ϵ�����ݿ����Ƿ��Ѿ�����
	public boolean isNotExists(TeachRelation relation);
		
	//���ڿ���Ϣ������excel
	public void exportExcel(List<TeachRelation> list, ServletOutputStream outputStream);
	
	//���ڿ���Ϣ��excel����
	public void importExcel(File excel, String excelFileName);
	
	//���ݽ�ʦ��ȡ��ǰ��ʦ�������ڿι�ϵ
	public List<TeachRelation> findAllByTeacher(Teacher teacher);
	
	//���ݽ�ʦ���γ̺Ͱ༶��ȡ�ڿ���Ϣ
	public TeachRelation findByTeaAndClzAndCour(String tid,String clzId,String couId);
}
