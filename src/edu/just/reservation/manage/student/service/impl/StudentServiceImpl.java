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
		// ��ѧ����ѧ�ź���Ϊ�˺ţ�ѧ��������Ϊ�û�������ʼ����Ϊ123456����ӵ��û���
		User user = new User();
		user.setAccount(student.getAccount());
		user.setName(student.getName());
		user.setPassword("123456");
		user.setState("1");
		// Ϊѧ���û����ѧ����ɫ
		String roleId = roleService.getRoleIdByRoleName("ѧ��");
		userService.addUserAndRole(user, roleId);
		// ���û���Ϣ���õ�student��
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
	 * excel Ĭ�ϸ�ʽ�� 
	 * 1��������
	 * 2��ѧ��ѧ�� ѧ������ ѧ�����ڰ༶��
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
					// ��ÿһ����¼��װ��һ��ѧ������
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
					//����ѧ�����ڰ༶�ĺŲ��Ұ༶��Ϣ
					Clazz clazz = clazzService.findClazzByCNum(clazzNum);
					//�༶��Ϣ������
					if(clazz==null){
						clazz = new Clazz();
						clazz.setCnumber(clazzNum);
						clazz.setClazzState("1");
						clazz.setCname(clazzNum);
						clazz.setSnumber(1);
						//�����ݿ�İ༶������Ӱ༶��Ϣ
						clazzService.add(clazz);
					}
					//���༶��Ϣ������ѧ����Ϣ��
					student.setClazz(clazz);
					// ��ǰѧ����Ϣ�����ݿ��в����ڲŽ��е�����������򲻵��뵽���ݿ�
					if (studentDao.isNotExists(student)) {
						// �����ǰѧ��ѧ���Ѿ����ڣ���ô���и��²���
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
