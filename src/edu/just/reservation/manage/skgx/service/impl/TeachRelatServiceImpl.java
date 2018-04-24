package edu.just.reservation.manage.skgx.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.core.util.ExcelUtil;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.clazz.service.ClazzService;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.course.service.CourseService;
import edu.just.reservation.manage.skgx.dao.TeachRelatDao;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.skgx.service.TeachRelatService;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;
import edu.just.reservation.manage.user.entity.User;

@Service("teachRelatService")
public class TeachRelatServiceImpl extends BaseServiceImpl<TeachRelation>
		implements TeachRelatService {

	private TeachRelatDao teachRelatDao;
	@Resource
	public void setTeachRelatDao(TeachRelatDao teachRelatDao) {
		setBaseDao(teachRelatDao);
		this.teachRelatDao = teachRelatDao;
	}
	
	@Resource
	private TeacherService teacherService;
	@Resource 
	private ClazzService clazzService;
	@Resource
	private CourseService courseService;
	
	//���ڿ���Ϣ������excel
	public void exportExcel(List<TeachRelation> list,
			ServletOutputStream outputStream) {
		ExcelUtil.exportExcelTeachRelation(list,outputStream);
	}
	/**
	 * ��excel�е��ڿ���Ϣ���뵽���ݿ���
	 * excelĬ�ϸ�ʽ��
	 * 1��������
	 * 2����ʦ���š��γ̺š��༶���š�ѧ��(ѧ�ڸ�ʽʾ����"2016-2017-2")
	 */
	public void importExcel(File excel, String excelFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(excel);
			boolean is03excel = excelFileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook = is03excel ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() > 2) {
				TeachRelation relation = null;
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					// ��ÿһ����¼��װ��һ���ڿι�ϵ����
					relation = new TeachRelation();
					Row row = sheet.getRow(i);
					//��ʦ���Ŵ���
					Cell cell0 = row.getCell(0);
					String teaAcc = "";
					try{
						teaAcc = cell0.getStringCellValue();
					}catch(Exception e){
						teaAcc = Integer.toString((int)cell0.getNumericCellValue());
					}
					//���ݽ�ʦ���Ż�ȡ����ʦ����
					Teacher teacher = teacherService.findTeacherByAccount(teaAcc);
					//�����ʦ�����ڣ����������
					//--------------��Ҫ��������-���쳣����---------------------
					if(teacher==null){
						continue;
					}
					//����ʦ��Ϣ���õ��ڿι�ϵ��
					relation.setTeacher(teacher);
					//�γ̺Ŵ���
					Cell cell1 = row.getCell(1);
					String courNum = "";
					try{
						courNum = cell1.getStringCellValue();
					}catch(Exception e){
						courNum = Integer.toString((int)cell1.getNumericCellValue());
					}
					//���ݿγ̺Ż�ȡ���γ̶���
					Course course = courseService.findCourseByCNum(courNum);
					//����γ̲����ڣ����������
					//--------------��Ҫ��������-���쳣����---------------------
					if(course==null){
						continue;
					}
					//���γ̶������õ��ڿι�ϵ��
					relation.setCourse(course);
					//�༶�Ŵ���
					Cell cell2 = row.getCell(2);
					String clazzNum = "";
					try{
						clazzNum = cell2.getStringCellValue();
					}catch(Exception e){
						clazzNum = Integer.toString((int)cell2.getNumericCellValue());
					}
					//���ݰ༶�Ż�ȡ���༶����
					Clazz clazz = clazzService.findClazzByCNum(clazzNum);
					//����༶�����ڣ����������
					//--------------��Ҫ��������-���쳣����---------------------
					if(clazz==null){
						continue;
					}
					//���༶�������õ��ڿι�ϵ��
					relation.setClazz(clazz);
					//ѧ�ڴ���
					Cell cell3 = row.getCell(3);
					relation.setTerm(cell3.getStringCellValue());
					//�жϵ�ǰ�ڿι�ϵ�Ƿ��Ѿ����ڣ�����Ѿ����ڣ������е���
					if(teachRelatDao.isNotExists(relation)){//��ǰ�ڿι�ϵ������
						//�������
						teachRelatDao.add(relation);
					}
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public List<TeachRelation> findAllByTeacher(Teacher teacher) {
		return teachRelatDao.findAllByTeacher(teacher);
	}
	public boolean isNotExists(TeachRelation relation) {
		return teachRelatDao.isNotExists(relation);
	}
	public TeachRelation findByTeaAndClzAndCour(String tid, String clzId,
			String couId) {
		return teachRelatDao.findByTeaAndClzAndCour(tid,clzId,couId);
	}
	
}
