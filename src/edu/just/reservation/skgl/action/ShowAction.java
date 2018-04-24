package edu.just.reservation.skgl.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.util.CreateStringUtil;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.clazz.service.ClazzService;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.course.service.CourseService;
import edu.just.reservation.manage.grade.entity.Grade;
import edu.just.reservation.manage.grade.service.GradeService;
import edu.just.reservation.manage.graderate.entity.GradeRate;
import edu.just.reservation.manage.graderate.service.GradeRateService;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.order.service.OrderService;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.skgx.service.TeachRelatService;
import edu.just.reservation.manage.student.entity.Student;
import edu.just.reservation.manage.student.service.StudentService;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;
import edu.just.reservation.manage.user.entity.User;

public class ShowAction extends ActionSupport {
	@Resource
	private TeachRelatService teachRelatService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private CourseService courseService;
	@Resource
	private OrderService orderService;
	@Resource
	private StudentService studentService;
	@Resource
	private GradeService gradeService;
	@Resource
	private GradeRateService gradeRateService;

	private TeachRelation relation;
	private Clazz clazz;
	private Course course;
	private Orders order;
	private int cqrate; 
	private int ktrate; 
	private int syrate; 
	private String mode;
	private boolean flag=true;

	public String frame() {
		return "frame";
	}

	public String left() {
		return "left";
	}

	public String top() {
		return "top";
	}

	// 本页面加载当前教师用户的所有授课关系
	public String welcome() {
		// 获取当前登陆的教师信息
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.TEACHER);
		// 如果教师非空
		if (teacher != null) {
			// 根据教师信息获取到其所有的授课关系
			List<TeachRelation> list = teachRelatService
					.findAllByTeacher(teacher);
			// 将授课关系集合设置到域对象中
			ActionContext.getContext().put("relationList", list);
		}
		return "welcome";
	}

	// 跳转到授课关系维护页面，页面上面提供添加授课关系，修改授课关系以及显示所有授课关系功能
	public String skgxwh() {
		/************* 下面用于显示所有授课关系 ***********/
		// 获取当前登陆的用户信息
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// 根据当前的用户信息获取到其对应的教师信息
		Teacher teacher = teacherService.findTeacherByUser(user);
		// 如果教师非空
		if (teacher != null) {
			// 根据教师信息获取到其所有的授课关系
			List<TeachRelation> list = teachRelatService
					.findAllByTeacher(teacher);
			// 将授课关系集合设置到域对象中
			ActionContext.getContext().put("relationList", list);
		}
		/************* 下面用于下拉列表信息 ****************/
		// 课程下拉列表
		List<Course> courseList = courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);
		// 班级下拉列表
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		// 授课学期下拉列表
		List<String> termList = CreateStringUtil.createTermStrs();
		ActionContext.getContext().put("termList", termList);

		// 获取当前学年
		String studyYearStr = CreateStringUtil.getStudyYearStr();
		ActionContext.getContext().put("studyYearStr", studyYearStr);
		// 获取当前学期
		String termInYear = CreateStringUtil.getTermInYear();
		ActionContext.getContext().put("termInYear", termInYear);
		// 获取当前周
		// 获取系统中设置的本学期开始时间
		try {
			String timeStr = (String) ActionContext.getContext()
					.getApplication().get(Constant.SYS_TIME);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			date = sdf.parse(timeStr);
			String week = CreateStringUtil.getWeekStr(date);
			ActionContext.getContext().put("weekNum", week);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取本周第一天
		String firstDay = CreateStringUtil.getFirstInThisWeek();
		ActionContext.getContext().put("firstDay", firstDay);
		// 获取本周最后一天
		String lastDay = CreateStringUtil.getLastInThisWeek();
		ActionContext.getContext().put("lastDay", lastDay);

		return "listSKGXUI";
	}

	/************* 下面用于添加授课关系 ****************/
	public String addSkgx() {
		// 获取当前登陆的用户信息
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// 根据当前的用户信息获取到其对应的教师信息
		Teacher teacher = teacherService.findTeacherByUser(user);
		// 当前用户是教师，可以添加授课关系
		if (teacher != null) {
			// 用户选择了授课学期
			if (relation != null && StringUtils.isNotBlank(relation.getTerm())) {
				// 用户选择了班级
				if (clazz != null && StringUtils.isNotBlank(clazz.getClazzId())) {
					// 用户选择了课程
					if (course != null
							&& StringUtils.isNotBlank(course.getCourseId())) {
						clazz = clazzService.findById(clazz.getClazzId());
						course = courseService.findById(course.getCourseId());
						relation.setClazz(clazz);
						relation.setCourse(course);
						relation.setTeacher(teacher);
						// 判断当前授课关系是否已经存在，如果已经存在不做添加
						if (teachRelatService.isNotExists(relation)) {
							// 添加数据
							teachRelatService.add(relation);
						}
					}
				}
			}
		}
		return "list";
	}

	/************* 下面用于修改授课关系 ****************/
	public String updateSkgx() {
		// 获取当前登陆的用户信息
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// 根据当前的用户信息获取到其对应的教师信息
		Teacher teacher = teacherService.findTeacherByUser(user);
		// 当前用户是教师，可以修改自己的授课关系
		if (teacher != null) {
			// 用户选择了授课学期
			if (relation != null && StringUtils.isNotBlank(relation.getTerm())) {
				// 用户选择了班级
				if (clazz != null && StringUtils.isNotBlank(clazz.getClazzId())) {
					// 用户选择了课程
					if (course != null
							&& StringUtils.isNotBlank(course.getCourseId())) {
						clazz = clazzService.findById(clazz.getClazzId());
						course = courseService.findById(course.getCourseId());
						relation.setClazz(clazz);
						relation.setCourse(course);
						relation.setTeacher(teacher);
						// 判断当前授课关系是否已经存在，如果已经存在，则不做处理
						if (teachRelatService.isNotExists(relation)) {
							teachRelatService.update(relation);
						}
					}
				}
			}
		}
		return "list";
	}

	/********** 下面用于删除授课关系 ************/
	public String deleteSkgx() {
		// 获取当前登陆的用户信息
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// 根据当前的用户信息获取到其对应的教师信息
		Teacher teacher = teacherService.findTeacherByUser(user);
		// 当前用户是教师，可以删除自己的授课关系
		if (teacher != null) {
			if (relation != null && StringUtils.isNotBlank(relation.getId())) {
				teachRelatService.delete(relation.getId());
			}
		}
		return "list";
	}

	public String skgcgl() {
		// 获取当前登陆的教师信息
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.TEACHER);
		// 如果教师非空
		if (teacher != null) {
			//根据教师将其成功的预约信息按使用时间倒序排列
			List<Orders> list = orderService.findSecceedOrderByTeacher(teacher.getId());
			// 将预约信息集合设置到域对象中
			ActionContext.getContext().put("list", list);
		}
		return "listSKGCUI";
	}

	public String skjh() {
		return "listSKJHUI";
	}
	
	public String addGradeUI(){
		try{
			Set<Student> students = null;
			//根据预约信息录入成绩
			if(order!=null&&StringUtils.isNotBlank(order.getOrderId())){
				Orders o = orderService.findById(order.getOrderId());
				List<Grade> grades = gradeService.findByOrder(o.getOrderId());
				if(grades!=null&&grades.size()>0){
					ActionContext.getContext().put("gradeList", grades);
					ActionContext.getContext().put("gradeState",grades.get(0).getState());
				}
				students = o.getR_tea().getClazz().getStudents();
				List<Student> studentList = new ArrayList(students);
				//按学生学号排序比较器
				Collections.sort(studentList,new Comparator<Student>(){
						 public int compare(Student s1, Student s2) {
							 return s1.getAccount().compareTo(s2.getAccount());
			             }
			    });
				ActionContext.getContext().put("o", o);
				ActionContext.getContext().put("oid", o.getOrderId());
				
				GradeRate gr = gradeRateService.finByOrder(o);
				if(gr!=null){
					flag = false;
					cqrate = gr.getCqrate();
					ktrate = gr.getKtrate();
					syrate = gr.getSyrate();
					mode = gr.getMode();
				}
			}else if(relation!=null&&StringUtils.isNotBlank(relation.getId())){
				TeachRelation tr = teachRelatService.findById(relation.getId());
				students = tr.getClazz().getStudents();
				//获取当前授课关系的所有审核通过的预约信息
				List<Orders> orderList = orderService.findSucceedOrdersByRelat(relation.getId());
				ActionContext.getContext().put("orderList", orderList);
				ActionContext.getContext().put("rid", relation.getId());
			}
			List<Student> studentList = null;
			if(students!=null){
				studentList = new ArrayList(students);
				//按学生学号排序比较器
				Collections.sort(studentList,new Comparator<Student>(){
					public int compare(Student s1, Student s2) {
						return s1.getAccount().compareTo(s2.getAccount());
					}
				});
			}
			ActionContext.getContext().put("studentList", studentList);
			
			ActionContext.getContext().put("flag", flag);
			if(!flag){
				ActionContext.getContext().put("cqrate", cqrate);
				ActionContext.getContext().put("ktrate", ktrate);
				ActionContext.getContext().put("syrate", syrate);
				ActionContext.getContext().put("mode1", mode);
			}
			return "addGradeUI";
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//获取成绩录入方式弹出框内容
	public String selectGradeTypeUI(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String oid = request.getParameter("oid");
		String rid = request.getParameter("rid");
		if(StringUtils.isNotBlank(oid)){
			ActionContext.getContext().put("oid", oid);
		}
		if(StringUtils.isNotBlank(rid)){
			ActionContext.getContext().put("rid", rid);
		}
		return "selectGradeTypeUI";
	}
	
	//获取计分方式
	public String getMode(){
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			cqrate = Integer.parseInt(request.getParameter("cqrate"));
			ktrate = Integer.parseInt(request.getParameter("ktrate"));
			syrate = Integer.parseInt(request.getParameter("syrate"));
			mode = request.getParameter("mode");
			flag = false;
			String rid = request.getParameter("rid");
			String oid = request.getParameter("oid");
			if(StringUtils.isNotBlank(rid)&&!rid.equals("undefined")){
				relation = new TeachRelation();
				relation.setId(rid);
			}
			if(StringUtils.isNotBlank(oid)&&!oid.equals("undefined")){
				order=orderService.findById(oid);
				GradeRate gr = new GradeRate();
				gr.setCqrate(cqrate);
				gr.setKtrate(ktrate);
				gr.setSyrate(syrate);
				gr.setMode(mode);
				gr.setOrder(order);
				gradeRateService.add(gr);
			}
			return addGradeUI();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	//保存成绩，保存完成后跳转到当前编辑成绩页面
	public String saveGrade(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String oid = request.getParameter("oid");
		order = orderService.findById(oid);
		for(int i = 1;;i++){
			String cqGrade = request.getParameter("cqGrade_"+i);
			String ktGrade = request.getParameter("ktGrade_"+i);
			String syGrade = request.getParameter("syGrade_"+i);
			String grade = request.getParameter("grade_"+i);
			String memo = request.getParameter("memo_"+i);
			if(cqGrade==null&&ktGrade==null&&syGrade==null&&grade==null&&memo==null){
				break;
			}else{
				Grade g = new Grade();
				String sId = request.getParameter("stu_"+i);
				Student s = studentService.findById(sId);
				if(cqGrade!=null){
					g.setCq(Integer.parseInt(cqGrade));
				}
				if(grade!=null){
					g.setGrade(Integer.parseInt(grade));
				}
				if(ktGrade!=null){
					g.setKt(Integer.parseInt(ktGrade));
				}
				g.setMemo(memo);
				g.setOrder(order);
				g.setState(Grade.GRADE_STATE_SAVE);
				g.setStudent(s);
				if(syGrade!=null){
					g.setSy(Integer.parseInt(syGrade));
				}
				//先判断当前成绩是否已经存在在数据库中，如果存在，则更新，否则，添加到数据库中
				if(gradeService.isExists(g)){
					Grade gExists = gradeService.findByGrade(g);
					gradeService.delete(gExists.getId());
					g.setId(gExists.getId());
					gradeService.add(g);
				}else{
					//将当前成绩对象存入数据库
					gradeService.add(g);
				}
			}
		}
		return addGradeUI();
	}
	
	//提交成绩，提交完成后跳转到显示成绩页面
	public String submitGrade(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String oid = request.getParameter("oid");
		order = orderService.findById(oid);
		for(int i = 1;;i++){
			String cqGrade = request.getParameter("cqGrade_"+i);
			String ktGrade = request.getParameter("ktGrade_"+i);
			String syGrade = request.getParameter("syGrade_"+i);
			String grade = request.getParameter("grade_"+i);
			String memo = request.getParameter("memo_"+i);
			if(cqGrade==null&&ktGrade==null&&syGrade==null&&grade==null&&memo==null){
				break;
			}else{
				Grade g = new Grade();
				String sId = request.getParameter("stu_"+i);
				Student s = studentService.findById(sId);
				if(cqGrade!=null){
					g.setCq(Integer.parseInt(cqGrade));
				}
				if(grade!=null){
					g.setGrade(Integer.parseInt(grade));
				}
				if(ktGrade!=null){
					g.setKt(Integer.parseInt(ktGrade));
				}
				g.setMemo(memo);
				g.setOrder(order);
				g.setState(Grade.GRADE_STATE_SUBMIT);
				g.setStudent(s);
				if(syGrade!=null){
					g.setSy(Integer.parseInt(syGrade));
				}
				//先判断当前成绩是否已经存在在数据库中，如果存在，则更新，否则，添加到数据库中
				if(gradeService.isExists(g)){
					Grade gExists = gradeService.findByGrade(g);
					gradeService.delete(gExists.getId());
					g.setId(gExists.getId());
					gradeService.add(g);
				}else{
					//将当前成绩对象存入数据库
					gradeService.add(g);
				}
			}
		}
		return "listGrade";
	}

	//重置计分模式
	public String resetGradeMode(){
		flag = true;
		HttpServletRequest request = ServletActionContext.getRequest();
		String oid = request.getParameter("oid");
		order = orderService.findById(oid);
		GradeRate gr = gradeRateService.finByOrder(order);
		gradeRateService.delete(gr.getId());
		List<Grade> gradeList = gradeService.findByOrder(oid);
		if(gradeList!=null&&gradeList.size()>0){
			for(int i = 0;i<gradeList.size();i++){
				Grade g = gradeList.get(i);
				gradeService.delete(g.getId());
			}
		}
		return addGradeUI();
	}
	
	//将学生信息导出到excel
	public void exportStudent(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String oid = request.getParameter("oid");
			if(!StringUtils.isNotBlank(oid)){
				if(order!=null&&StringUtils.isNotBlank(order.getOrderId())){
					oid = order.getOrderId();
				}
			}
			order = orderService.findById(oid);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-xls");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String("学生.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			Set<Student> students = order.getR_tea().getClazz().getStudents();
			List<Student> stuList = new ArrayList<Student>(students);
			studentService.exportStudentExcel(stuList, outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//将当前成绩导出到excel
	public void exportGrade(){
		try {
			List<Grade> gradeList = new LinkedList<Grade>();
			HttpServletRequest request = ServletActionContext.getRequest();
			String oid = request.getParameter("oid");
			if(!StringUtils.isNotBlank(oid)){
				if(order!=null&&StringUtils.isNotBlank(order.getOrderId())){
					oid = order.getOrderId();
				}
			}
			order = orderService.findById(oid);
			for(int i = 1;;i++){
				String cqGrade = request.getParameter("cqGrade_"+i);
				String ktGrade = request.getParameter("ktGrade_"+i);
				String syGrade = request.getParameter("syGrade_"+i);
				String grade = request.getParameter("grade_"+i);
				String memo = request.getParameter("memo_"+i);
				if(cqGrade==null&&ktGrade==null&&syGrade==null&&grade==null&&memo==null){
					break;
				}else{
					Grade g = new Grade();
					String sId = request.getParameter("stu_"+i);
					Student s = studentService.findById(sId);
					if(cqGrade!=null){
						g.setCq(Integer.parseInt(cqGrade));
					}
					if(grade!=null){
						g.setGrade(Integer.parseInt(grade));
					}
					if(ktGrade!=null){
						g.setKt(Integer.parseInt(ktGrade));
					}
					g.setMemo(memo);
					g.setOrder(order);
					g.setStudent(s);
					if(syGrade!=null){
						g.setSy(Integer.parseInt(syGrade));
					}
					gradeList.add(g);
				}
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-xls");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String("成绩.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			gradeService.exportExcel(gradeList, outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//将当前页面上或者当前授课关系的所有成绩导出到excel
	public void exportManyGrade(){
		try {
			if(relation!=null&&StringUtils.isNotBlank(relation.getId())){
				List<Grade> grades = gradeService.findByRelation(relation.getId());
				int count = grades.size();
				ActionContext.getContext().put("count", count);
				Map<Student,List<Grade>> gradeMap = new LinkedHashMap<Student,List<Grade>>();
				List<Orders> oList = new LinkedList<Orders>();
				for(int i = 0;i<count;i++){
					Grade g = grades.get(i);
					oList.add(g.getOrder());
				}
				relation = teachRelatService.findById(relation.getId());
				List<Student> students = new ArrayList<Student>(relation.getClazz().getStudents());
				//按学生学号排序比较器
				Collections.sort(students,new Comparator<Student>(){
						 public int compare(Student s1, Student s2) {
							 return s1.getAccount().compareTo(s2.getAccount());
			             }
			    });
				for(int i = 0;i<students.size();i++){
					Student s = students.get(i);
					List<Grade> gl = new LinkedList<Grade>();
					for(int j=0;j<count;j++){
						Grade g = gradeService.findByStudentAndOrder(s,oList.get(j));
						gl.add(g);
					}
					gradeMap.put(s, gl);
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				String oid = request.getParameter("oid");
				order = orderService.findById(oid);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("application/x-xls");
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Disposition", "attachment;fileName="
						+ new String("学生.xls".getBytes("UTF-8"), "ISO-8859-1"));
				ServletOutputStream outputStream = response.getOutputStream();
				gradeService.exportExcelManyGrade(gradeMap, outputStream);
				if (outputStream != null) {
					outputStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String listGradeUI(){
		try{
			if(order!=null&&StringUtils.isNotBlank(order.getOrderId())){
				List<Grade> gradeList = gradeService.findByOrder(order.getOrderId());
				GradeRate gr = gradeRateService.finByOrder(order);
				if(gr!=null){
					mode = gr.getMode();
					cqrate = gr.getCqrate();
					ktrate = gr.getKtrate();
					syrate = gr.getSyrate();
					ActionContext.getContext().put("mode1", mode);
					ActionContext.getContext().put("cq", cqrate!=0?1:null);
					ActionContext.getContext().put("kt", ktrate!=0?1:null);
					ActionContext.getContext().put("sy", syrate!=0?1:null);
					ActionContext.getContext().put("gradeList", gradeList);
				}
				ActionContext.getContext().put("o", order);
			}else if(relation!=null&&StringUtils.isNotBlank(relation.getId())){
				List<Grade> grades = gradeService.findByRelation(relation.getId());
				int count = grades.size();
				if(count!=0){
					ActionContext.getContext().put("count", count);
					Map<Student,List<Grade>> gradeMap = new LinkedHashMap<Student,List<Grade>>();
					List<Orders> oList = new LinkedList<Orders>();
					for(int i = 0;i<count;i++){
						Grade g = grades.get(i);
						oList.add(g.getOrder());
					}
					relation = teachRelatService.findById(relation.getId());
					List<Student> students = new ArrayList<Student>(relation.getClazz().getStudents());
					//按学生学号排序比较器
					Collections.sort(students,new Comparator<Student>(){
							 public int compare(Student s1, Student s2) {
								 return s1.getAccount().compareTo(s2.getAccount());
				             }
				    });
					for(int i = 0;i<students.size();i++){
						Student s = students.get(i);
						List<Grade> gl = new LinkedList<Grade>();
						for(int j=0;j<count;j++){
							Grade g = gradeService.findByStudentAndOrder(s,oList.get(j));
							gl.add(g);
						}
						gradeMap.put(s, gl);
					}
					ActionContext.getContext().put("gradeMap", gradeMap);
					ActionContext.getContext().put("o", oList.get(0));
					ActionContext.getContext().put("rid", relation.getId());
				}
			}
			return "listGradeUI";
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public TeachRelation getRelation() {
		return relation;
	}

	public void setRelation(TeachRelation relation) {
		this.relation = relation;
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

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}
