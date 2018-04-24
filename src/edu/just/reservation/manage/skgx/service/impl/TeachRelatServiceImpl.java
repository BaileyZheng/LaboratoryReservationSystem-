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
	
	//将授课信息导出到excel
	public void exportExcel(List<TeachRelation> list,
			ServletOutputStream outputStream) {
		ExcelUtil.exportExcelTeachRelation(list,outputStream);
	}
	/**
	 * 将excel中的授课信息导入到数据库中
	 * excel默认格式：
	 * 1、标题行
	 * 2、教师工号、课程号、班级代号、学期(学期格式示例："2016-2017-2")
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
					// 将每一条记录封装成一个授课关系对象
					relation = new TeachRelation();
					Row row = sheet.getRow(i);
					//教师工号处理
					Cell cell0 = row.getCell(0);
					String teaAcc = "";
					try{
						teaAcc = cell0.getStringCellValue();
					}catch(Exception e){
						teaAcc = Integer.toString((int)cell0.getNumericCellValue());
					}
					//根据教师工号获取到教师对象
					Teacher teacher = teacherService.findTeacherByAccount(teaAcc);
					//如果教师不存在，不进行添加
					//--------------需要后期完善-加异常处理---------------------
					if(teacher==null){
						continue;
					}
					//将教师信息设置到授课关系中
					relation.setTeacher(teacher);
					//课程号处理
					Cell cell1 = row.getCell(1);
					String courNum = "";
					try{
						courNum = cell1.getStringCellValue();
					}catch(Exception e){
						courNum = Integer.toString((int)cell1.getNumericCellValue());
					}
					//根据课程号获取到课程对象
					Course course = courseService.findCourseByCNum(courNum);
					//如果课程不存在，不进行添加
					//--------------需要后期完善-加异常处理---------------------
					if(course==null){
						continue;
					}
					//将课程对象设置到授课关系中
					relation.setCourse(course);
					//班级号处理
					Cell cell2 = row.getCell(2);
					String clazzNum = "";
					try{
						clazzNum = cell2.getStringCellValue();
					}catch(Exception e){
						clazzNum = Integer.toString((int)cell2.getNumericCellValue());
					}
					//根据班级号获取到班级对象
					Clazz clazz = clazzService.findClazzByCNum(clazzNum);
					//如果班级不存在，不进行添加
					//--------------需要后期完善-加异常处理---------------------
					if(clazz==null){
						continue;
					}
					//将班级对象设置到授课关系中
					relation.setClazz(clazz);
					//学期处理
					Cell cell3 = row.getCell(3);
					relation.setTerm(cell3.getStringCellValue());
					//判断当前授课关系是否已经存在，如果已经存在，不进行导入
					if(teachRelatDao.isNotExists(relation)){//当前授课关系不存在
						//进行添加
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
