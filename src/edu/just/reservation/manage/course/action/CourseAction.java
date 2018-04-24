package edu.just.reservation.manage.course.action;

import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.course.service.CourseService;

public class CourseAction extends BaseAction {
	@Resource
	private CourseService courseService;
	private Course course;
	private String strCName;

	// 列表页面
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(Course.class, "c");
		try {
			if (course != null) {
				if (course.getCName()!=null&&!"".equals(course.getCName())) {
					course.setCName(URLDecoder.decode(course.getCName(),
							"utf-8"));
					queryHelper.addCondition("c.CName like ?",
							"%" + course.getCName() + "%");
				}
			}
			pageResult = courseService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		if (course != null && StringUtils.isNotBlank(course.getCName())) {
			strCName = course.getCName();
		}
		course = null;
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (course != null) {
				courseService.add(course);
			}
			course.setCName(strCName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (course != null && course.getCourseId() != null) {
			strCName = course.getCName();
			course = courseService.findById(course.getCourseId());
		}
		return "editUI";
	}

	// 保存编辑
	public String saveEdit() {
		try {
			if (course != null) {
				courseService.update(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (course != null && course.getCourseId() != null) {
			strCName = course.getCName();
			courseService.delete(course.getCourseId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			strCName = course.getCName();
			for (String id : selectedRow) {
				courseService.delete(id);
			}
		}
		return "list";
	}

	// 更新状态
	public void publicCourse() {
		try {
			if (course != null) {
				Course temp = courseService.findById(course.getCourseId());
				temp.setCourseState(course.getCourseState());
				courseService.update(temp);

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("更新状态成功".getBytes("UTF-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getStrCName() {
		return strCName;
	}

	public void setStrCName(String strCName) {
		this.strCName = strCName;
	}

}
