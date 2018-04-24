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
		// ����ʦ�Ĺ�����Ϊ�˺ţ���ʦ����Ϊ�û�������ʼ����Ϊ123456����ӵ��û���
		User user = new User();
		user.setAccount(teacher.getAccount());
		user.setName(teacher.getName());
		user.setPassword("123456");
		user.setState("1");
		// Ϊ��ʦ�û���ӽ�ʦ��ɫ
		String roleId = roleService.getRoleIdByRoleName("��ʦ");
		userService.addUserAndRole(user, roleId);
		// ���û���Ϣ���õ�teacher��
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
	 * excel Ĭ�ϸ�ʽ�� 
	 * 1��������
	 * 2����ʦ���� ��ʦ���� ��ʦְ��
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
					// ��ÿһ����¼��װ��һ����ʦ����
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
					// ��ǰ��ʦ��Ϣ�����ݿ��в����ڲŽ��е�����������򲻵��뵽���ݿ�
					if (teacherDao.isNotExists(teacher)) {
						// �����ǰ��ʦ�˺��Ѿ����ڣ���ô���и��²���
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
