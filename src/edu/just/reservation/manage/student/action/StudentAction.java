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
	//导入文件
	private File excel;
	private String excelContentType;
	private String excelFileName;
	// 列表页面
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

	// 跳转到新增页面
	public String addUI() {
		//获取所有班级信息
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (student != null) {
				//获取班级信息
				if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
					Clazz clz = clazzService.findById(clazz.getClazzId());
					//将班级设置到学生中
					student.setClazz(clz);
				}
				//将学生信息保存到学生表中
				studentService.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		//获取所有班级信息
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

	// 保存编辑
	public String saveEdit() throws Exception{
		try {
			if (student != null) {
				if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
					student.setClazz(clazzService.findById(clazz.getClazzId()));
				}
				//将此学生之前的用户信息进行保存
				Student temp = studentService.findById(student.getId());
				studentService.update(student,temp.getUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if(student!=null&&!"".equals(student.getId())){
			System.out.println(student.getId());
			//删除学生信息时，将此学生对应的用户设置为无效状态
			student = studentService.findById(student.getId());
			User user = student.getUser();
			user.setState(User.USER_STATE_INVALID);
			userService.update(user);
			studentService.delete(student.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				studentService.delete(id);
			}
		}
		return "list";
	}

	//校验非此id的账号是否存在
	//即编辑学生信息时，如果编辑的学生学号已经存在并且不是当前学生，则给出提示不允许保存编辑
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
	
	
	// 校验账号是否存在
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
	
	// 导出到excel
	public void exportExcel() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-xls");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String("学生.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			studentService.exportExcel(studentService.findAll(), outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 导入excel
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
