package edu.just.reservation.manage.skgx.action;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.CreateStringUtil;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.clazz.service.ClazzService;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.course.service.CourseService;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.skgx.service.TeachRelatService;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;

public class TeachRelatAction extends BaseAction {
	@Resource
	private TeachRelatService teachRelatService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private CourseService courseService;
	@Resource
	private ClazzService clazzService;
	private TeachRelation teachRelation;

	private File excel;
	private String excelContentType;
	private String excelFileName;
	
	private Teacher teacher;
	private Clazz clazz;
	private Course course;

	private String strName;

	// 列表页面
	public String listUI() {
		ActionContext.getContext().put("teacherList", teacherService.findAll());
		QueryHelper queryHelper = new QueryHelper(TeachRelation.class, "t");
		try {
			if(teacher!=null&&StringUtils.isNotBlank(teacher.getId())&&!"0".equals(teacher.getId())){
				queryHelper.addCondition("t.teacher.id=?", teacher.getId());
			}
			pageResult = teachRelatService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		if(teacher!=null&&StringUtils.isNotBlank(teacher.getId())){
			teacher = null;
		}
		ActionContext.getContext().put("teacherList", teacherService.findAll());
		ActionContext.getContext().put("courseList", courseService.findAll());
		ActionContext.getContext().put("clazzList", clazzService.findAll());
		List<String> termList = CreateStringUtil.createTermStrs();
		ActionContext.getContext().put("termList", termList);
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if(teachRelation!=null&&StringUtils.isNotBlank(teachRelation.getTerm())){
				if(teacher!=null&&StringUtils.isNotBlank(teacher.getId())){
					if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
						if(course!=null&&StringUtils.isNotBlank(course.getCourseId())){
							teacher = teacherService.findById(teacher.getId());
							clazz = clazzService.findById(clazz.getClazzId());
							course = courseService.findById(course.getCourseId());
							teachRelation.setClazz(clazz);
							teachRelation.setCourse(course);
							teachRelation.setTeacher(teacher);
							//将授课关系保存到数据库中
							teachRelatService.add(teachRelation);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (teachRelation != null && teachRelation.getId() != null) {
			teachRelation = teachRelatService.findById(teachRelation.getId());
			teacher = teachRelation.getTeacher();
			clazz = teachRelation.getClazz();
			course = teachRelation.getCourse();
			ActionContext.getContext().put("teacherList", teacherService.findAll());
			ActionContext.getContext().put("courseList", courseService.findAll());
			ActionContext.getContext().put("clazzList", clazzService.findAll());
			List<String> termList = CreateStringUtil.createTermStrs();
			ActionContext.getContext().put("termList", termList);
		}
		return "editUI";
	}

	// 保存编辑
	public String saveEdit() {
		try {
			if(teachRelation!=null&&StringUtils.isNotBlank(teachRelation.getTerm())){
				if(teacher!=null&&StringUtils.isNotBlank(teacher.getId())){
					if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
						if(course!=null&&StringUtils.isNotBlank(course.getCourseId())){
							teacher = teacherService.findById(teacher.getId());
							clazz = clazzService.findById(clazz.getClazzId());
							course = courseService.findById(course.getCourseId());
							teachRelation.setClazz(clazz);
							teachRelation.setCourse(course);
							teachRelation.setTeacher(teacher);
							teachRelatService.update(teachRelation);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (teachRelation != null && teachRelation.getId() != null) {
			teachRelatService.delete(teachRelation.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				teachRelatService.delete(id);
			}
		}
		return "list";
	}

	// 导出到excel
	public void exportExcel() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-xls");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String("授课列表.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			teachRelatService.exportExcel(teachRelatService.findAll(), outputStream);
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
				teachRelatService.importExcel(excel, excelFileName);
			}
		}
		return "list";
	}

	// 校验当前授课关系是否存在
	public void verifyAccount() {
		try {
			if(teachRelation!=null&&StringUtils.isNotBlank(teachRelation.getTerm())){
				if(teacher!=null&&StringUtils.isNotBlank(teacher.getId())){
					if(clazz!=null&&StringUtils.isNotBlank(clazz.getClazzId())){
						if(course!=null&&StringUtils.isNotBlank(course.getCourseId())){
							QueryHelper helper = new QueryHelper(TeachRelation.class,"t");
							helper.addCondition("t.teacher.id=?", teacher.getId());
							helper.addCondition("t.course.courseId=?", course.getCourseId());
							helper.addCondition("t.clazz.clazzId=?", clazz.getClazzId());
							helper.addCondition("t.term=?", teachRelation.getTerm());
							List<TeachRelation> trList = teachRelatService.findByConditions(helper);
							if(trList!=null&&trList.size()>0){
								HttpServletResponse response = ServletActionContext
										.getResponse();
								response.setContentType("text/html");
								ServletOutputStream outputStream = response
										.getOutputStream();
								outputStream.write("true".getBytes());
								outputStream.close();
							}
						}
					}
				}
					
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TeachRelation getTeachRelation() {
		return teachRelation;
	}

	public void setTeachRelation(TeachRelation teachRelation) {
		this.teachRelation = teachRelation;
	}

	public File getHeadImg() {
		return excel;
	}

	public void setHeadImg(File excel) {
		this.excel = excel;
	}

	public String getHeadImgContentType() {
		return excelContentType;
	}

	public void setHeadImgContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}

	public String getHeadImgFileName() {
		return excelFileName;
	}

	public void setHeadImgFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
