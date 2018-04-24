package edu.just.reservation.manage.teacher.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
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
import edu.just.reservation.manage.role.service.RoleService;
import edu.just.reservation.manage.teacher.dao.TeacherDao;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.service.UserService;

@Service("teacherService")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher> implements
		TeacherService {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	private TeacherDao teacherDao;

	@Resource
	public void setTeacherDao(TeacherDao teacherDao) {
		super.setBaseDao(teacherDao);
		this.teacherDao = teacherDao;
	}

	public Teacher findTeacherByAccount(String account) {
		return teacherDao.findTeacherByAccount(account);
	}

	public void add(Teacher teacher) {
		// 将教师的工号作为账号，教师名作为用户名，初始密码为123456，添加到用户表
		User user = new User();
		user.setAccount(teacher.getAccount());
		user.setName(teacher.getName());
		user.setPassword("123456");
		user.setState("1");
		// 为教师用户添加教师角色
		String roleId = roleService.getRoleIdByRoleName("教师");
		userService.addUserAndRole(user, roleId);
		// 将用户信息设置到teacher中
		teacher.setUser(user);
		teacherDao.add(teacher);
	}

	public List<Teacher> getAllTeachers() {
		return teacherDao.getAllTeachers();
	}

	public Teacher findTeacherByAccountAndId(String account, String id) {
		return teacherDao.findTeacherByAccountAndId(account, id);
	}

	public void exportExcel(List<Teacher> list, ServletOutputStream outputStream) {
		ExcelUtil.exportExcelTeacher(list, outputStream);
	}

	/**
	 * excel 默认格式： 
	 * 1、标题行
	 * 2、教师工号 教师姓名 教师职称
	 */
	public void importExcel(File excel, String excelFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(excel);
			boolean is03excel = excelFileName.matches("^.+\\.(?i)(xls)$");
			Workbook workbook = is03excel ? new HSSFWorkbook(inputStream)
					: new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Teacher teacher = null;
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					// 将每一条记录封装成一个教师对象
					teacher = new Teacher();
					Row row = sheet.getRow(i);
					Cell cell0 = row.getCell(0);
					try{
						teacher.setAccount(cell0.getStringCellValue());
					}catch(Exception e){
						String acc = Integer.toString((int)cell0.getNumericCellValue());
						teacher.setAccount(acc);
					}
					Cell cell1 = row.getCell(1);
					teacher.setName(cell1.getStringCellValue());
					Cell cell2 = row.getCell(2);
					teacher.setProTitle(cell2.getStringCellValue());
					// 当前教师信息在数据库中不存在才进行导入操作，否则不导入到数据库
					if (teacherDao.isNotExists(teacher)) {
						// 如果当前教师账号已经存在，那么进行更新操作
						Teacher temp = teacherDao.findTeacherByAccount(teacher
								.getAccount());
						if ( temp != null&&temp.getUser()!=null) {
							temp = teacherDao.findById(temp.getId());
							User user = userService.findById(temp.getUser().getId());
							teacher.setId(temp.getId());
							update(teacher, user);
						} else {
							add(teacher);
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

	public void update(Teacher teacher, User user) {
		teacher.setUser(user);
		teacherDao.update(teacher);
	}

	public Teacher findTeacherByUser(User user) {
		return teacherDao.findTeacherByUser(user);
	}

}
