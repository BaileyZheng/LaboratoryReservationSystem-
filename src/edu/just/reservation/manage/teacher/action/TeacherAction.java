package edu.just.reservation.manage.teacher.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.page.PageResult;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.role.service.RoleService;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;
import edu.just.reservation.manage.user.service.UserService;

public class TeacherAction extends BaseAction {
	@Resource
	private TeacherService teacherService;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private Teacher teacher;
	private String strTitle;
	//导入文件
	private File excel;
	private String excelContentType;
	private String excelFileName;
	// 列表页面
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(Teacher.class, "t");
		try {
			if(teacher!=null&&StringUtils.isNotBlank(teacher.getAccount())){
				queryHelper.addCondition("t.account like ?", "%"+teacher.getAccount()+"%");
			}
			pageResult = teacherService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (teacher != null) {
				//将教师信息保存到教师表中
				teacherService.add(teacher);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (teacher != null && teacher.getId() != null) {
			teacher = teacherService.findById(teacher.getId());
		}
		return "editUI";
	}

	// 保存编辑
	public String saveEdit() throws Exception{
		try {
			if (teacher != null) {
				//将此教师之前的用户信息进行保存
				Teacher temp = teacherService.findById(teacher.getId());
				teacherService.update(teacher,temp.getUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if(teacher!=null&&!"".equals(teacher.getId())){
			System.out.println(teacher.getId());
			//删除教师信息时，将此教师对应的用户设置为无效状态
			teacher = teacherService.findById(teacher.getId());
			User user = teacher.getUser();
			user.setState(User.USER_STATE_INVALID);
			userService.update(user);
			teacherService.delete(teacher.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				teacherService.delete(id);
			}
		}
		return "list";
	}

	//校验非此id的账号是否存在
	//即编辑教师信息时，如果编辑的教师工号已经存在并且不是当前教师，则给出提示不允许保存编辑
	public void verifyAccountAndId(){
		try{
			if(teacher!=null&&StringUtils.isNotBlank(teacher.getAccount())&&StringUtils.isNotBlank(teacher.getId())){
				Teacher temp = teacherService.findTeacherByAccountAndId(teacher.getAccount(),teacher.getId());
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
			if (teacher != null && StringUtils.isNotBlank(teacher.getAccount())) {
				Teacher temp = teacherService.findTeacherByAccount(
						teacher.getAccount());
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
					+ new String("教师列表.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			teacherService.exportExcel(teacherService.findAll(), outputStream);
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
				teacherService.importExcel(excel, excelFileName);
			}
		}
		return "list";
	}
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
	
}
