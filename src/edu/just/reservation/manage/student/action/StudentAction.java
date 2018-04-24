package edu.just.reservation.manage.student.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.clazz.service.ClazzService;
import edu.just.reservation.manage.role.service.RoleService;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.student.service.StudentService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.service.UserService;

public class StudentAction extends BaseAction {
	@Resource
	private StudentService studentService;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private ClazzService clazzService;
	private Student student;
	private String strTitle;
	
	private Clazz clazz;
	//�����ļ�
	private File excel;
	private String excelContentType;
	private String excelFileName;
	// �б�ҳ��
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(Student.class, "s");
		try {
			if(student!=null&&StringUtils.isNotBlank(student.getAccount())){
				queryHelper.addCondition("s.account like ?", "%"+student.getAccount()+"%");
			}
			pageResult = studentService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listUI";
	}

	// ��ת������ҳ��
	public String addUI() {
		//��ȡ���а༶��Ϣ
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		return "addUI";
	}

	// ��������
	public String add() {
		try {
			if (student != null) {
				//��ȡ�༶��Ϣ
				if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
					Clazz clz = clazzService.findById(clazz.getClazzId());
					//���༶���õ�ѧ����
					student.setClazz(clz);
				}
				//��ѧ����Ϣ���浽ѧ������
				studentService.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// ��ת���༭ҳ��
	public String editUI() {
		//��ȡ���а༶��Ϣ
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		if (student != null && student.getId() != null) {
			student = studentService.findById(student.getId());
			if(student.getClazz()!=null){
				clazz = student.getClazz();
			}
		}
		return "editUI";
	}

	// ����༭
	public String saveEdit() throws Exception{
		try {
			if (student != null) {
				if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
					student.setClazz(clazzService.findById(clazz.getClazzId()));
				}
				//����ѧ��֮ǰ���û���Ϣ���б���
				Student temp = studentService.findById(student.getId());
				studentService.update(student,temp.getUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// ɾ��
	public String delete() {
		if(student!=null&&!"".equals(student.getId())){
			System.out.println(student.getId());
			//ɾ��ѧ����Ϣʱ������ѧ����Ӧ���û�����Ϊ��Ч״̬
			student = studentService.findById(student.getId());
			User user = student.getUser();
			user.setState(User.USER_STATE_INVALID);
			userService.update(user);
			studentService.delete(student.getId());
		}
		return "list";
	}

	// ����ɾ��
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				studentService.delete(id);
			}
		}
		return "list";
	}

	//У��Ǵ�id���˺��Ƿ����
	//���༭ѧ����Ϣʱ������༭��ѧ��ѧ���Ѿ����ڲ��Ҳ��ǵ�ǰѧ�����������ʾ��������༭
	public void verifyAccountAndId(){
		try{
			if(student!=null&&StringUtils.isNotBlank(student.getAccount())&&StringUtils.isNotBlank(student.getId())){
				Student temp = studentService.findStudentByAccountAndId(student.getAccount(),student.getId());
				if (temp != null) {
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response
							.getOutputStream();
					outputStream.write("true".getBytes());
					outputStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// У���˺��Ƿ����
	public void verifyAccount() {
		try {
			if (student != null && StringUtils.isNotBlank(student.getAccount())) {
				Student temp = studentService.findStudentByAccount(
						student.getAccount());
				if (temp != null) {
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response
							.getOutputStream();
					outputStream.write("true".getBytes());
					outputStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ������excel
	public void exportExcel() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-xls");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String("ѧ��.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			studentService.exportExcel(studentService.findAll(), outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����excel
	public String importExcel() {
		if (excel != null) {
			if (excelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				studentService.importExcel(excel, excelFileName);
			}
		}
		return "list";
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelContentType() {
		return excelContentType;
	}

	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
}
