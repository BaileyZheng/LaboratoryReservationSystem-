package edu.just.reservation.manage.skgx.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.teacher.entity.Teacher;

public interface TeachRelatService extends BaseService<TeachRelation> {

	//根据传入的授课关系对象查看当前授课关系在数据库中是否已经存在
	public boolean isNotExists(TeachRelation relation);
		
	//将授课信息导出到excel
	public void exportExcel(List<TeachRelation> list, ServletOutputStream outputStream);
	
	//将授课信息从excel导入
	public void importExcel(File excel, String excelFileName);
	
	//根据教师获取当前教师的所有授课关系
	public List<TeachRelation> findAllByTeacher(Teacher teacher);
	
	//根据教师、课程和班级获取授课信息
	public TeachRelation findByTeaAndClzAndCour(String tid,String clzId,String couId);
}
