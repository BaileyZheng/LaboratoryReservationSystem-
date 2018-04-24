package edu.just.reservation.manage.student.service.impl;

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
import edu.just.reservation.manage.role.service.RoleService;
import edu.just.reservation.manage.student.dao.StudentDao;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.student.service.StudentService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.service.UserService;

@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl<Student> implements
		StudentService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private ClazzService clazzService;

	private StudentDao studentDao;

	@Resource
	public void setStudentDao(StudentDao studentDao) {
		super.setBaseDao(studentDao);
		this.studentDao = studentDao;
	}

	public Student findStudentByAccount(String account) {
		return studentDao.findStudentByAccount(account);
	}

	public void add(Student student) {
		// 将学生的学号号作为账号，学生姓名作为用户名，初始密码为123456，添加到用户表
		User user = new User();
		user.setAccount(student.getAccount());
		user.setName(student.getName());
		user.setPassword("123456");
		user.setState("1");
		// 为学生用户添加学生角色
		String roleId = roleService.getRoleIdByRoleName("学生");
		userService.addUserAndRole(user, roleId);
		// 将用户信息设置到student中
		student.setUser(user);
		studentDao.add(student);
	}

	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	public Student findStudentByAccountAndId(String account, String id) {
		return studentDao.findStudentByAccountAndId(account, id);
	}

	public void exportExcel(List<Student> list, ServletOutputStream outputStream) {
		ExcelUtil.exportExcelStudent(list, outputStream);
	}

	/**
	 * excel 默认格式： 
	 * 1、标题行
	 * 2、学生学号 学生姓名 学生所在班级号
	 */
	public void importExcel(File excel, String excelFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(excel);
			boolean is03excel = excelFileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook = is03excel ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Student student = null;
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					// 将每一条记录封装成一个学生对象
					student = new Student();
					Row row = sheet.getRow(i);
					Cell cell0 = row.getCell(0);
					try{
						student.setAccount(cell0.getStringCellValue());
					}catch(Exception e){
						String acc = Integer.toString((int)cell0.getNumericCellValue());
						student.setAccount(acc);
					}
					Cell cell1 = row.getCell(1);
					student.setName(cell1.getStringCellValue());
					Cell cell2 = row.getCell(2);
					String clazzNum = "";
					try{
						clazzNum = cell2.getStringCellValue();
					}catch(Exception e){
						clazzNum = Integer.toString((int)cell2.getNumericCellValue());
					}
					//根据学生所在班级的号查找班级信息
					Clazz clazz = clazzService.findClazzByCNum(clazzNum);
					//班级信息不存在
					if(clazz==null){
						clazz = new Clazz();
						clazz.setCnumber(clazzNum);
						clazz.setClazzState("1");
						clazz.setCname(clazzNum);
						clazz.setSnumber(1);
						//向数据库的班级表中添加班级信息
						clazzService.add(clazz);
					}
					//将班级信息关联到学生信息中
					student.setClazz(clazz);
					// 当前学生信息在数据库中不存在才进行导入操作，否则不导入到数据库
					if (studentDao.isNotExists(student)) {
						// 如果当前学生学号已经存在，那么进行更新操作
						Student temp = studentDao.findStudentByAccount(student
								.getAccount());
						if ( temp != null&&temp.getUser()!=null) {
							temp = studentDao.findById(temp.getId());
							User user = userService.findById(temp.getUser().getId());
							student.setId(temp.getId());
							update(student, user);
						} else {
							add(student);
						}
					}

				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Student student, User user) {
		student.setUser(user);
		studentDao.update(student);
	}

	public void exportStudentExcel(List<Student> stuList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportStudentExcel(stuList, outputStream);
	}

}
