package edu.just.reservation.manage.grade.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.core.util.ExcelUtil;
import edu.just.reservation.manage.grade.dao.GradeDao;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.grade.service.GradeService;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.student.entity.Student;

@Service("gradeService")
public class GradeServiceImpl extends BaseServiceImpl<Grade> implements
		GradeService {
	private GradeDao gradeDao;

	@Resource
	public void setGradeDao(GradeDao gradeDao) {
		super.setBaseDao(gradeDao);
		this.gradeDao = gradeDao;
	}

	public List<Grade> findByOrder(String oid) {
		return gradeDao.findByOrder(oid);
	}

	public boolean isExists(Grade grade) {
		return gradeDao.isExists(grade);
	}

	public Grade findByGrade(Grade grade) {
		return gradeDao.findByGrade(grade);
	}

	public void exportExcel(List<Grade> gradeList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportExcelGrade(gradeList, outputStream);		
	}

	public List<Grade> findByRelation(String rid) {
		return gradeDao.findByRelation(rid);
	}

	public Grade findByStudentAndOrder(Student s, Orders orders) {
		return gradeDao.findByStudentAndOrder(s, orders);
	}

	public void exportExcelManyGrade(Map<Student, List<Grade>> gradeMap,
			ServletOutputStream outputStream) {
		ExcelUtil.exportExcelManyGrade(gradeMap, outputStream);		
	}
}
