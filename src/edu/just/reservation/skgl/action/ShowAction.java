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

	// ��ҳ����ص�ǰ��ʦ�û��������ڿι�ϵ
	public String welcome() {
		// ��ȡ��ǰ��½�Ľ�ʦ��Ϣ
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.TEACHER);
		// �����ʦ�ǿ�
		if (teacher != null) {
			// ���ݽ�ʦ��Ϣ��ȡ�������е��ڿι�ϵ
			List<TeachRelation> list = teachRelatService
					.findAllByTeacher(teacher);
			// ���ڿι�ϵ�������õ��������
			ActionContext.getContext().put("relationList", list);
		}
		return "welcome";
	}

	// ��ת���ڿι�ϵά��ҳ�棬ҳ�������ṩ����ڿι�ϵ���޸��ڿι�ϵ�Լ���ʾ�����ڿι�ϵ����
	public String skgxwh() {
		/************* ����������ʾ�����ڿι�ϵ ***********/
		// ��ȡ��ǰ��½���û���Ϣ
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// ���ݵ�ǰ���û���Ϣ��ȡ�����Ӧ�Ľ�ʦ��Ϣ
		Teacher teacher = teacherService.findTeacherByUser(user);
		// �����ʦ�ǿ�
		if (teacher != null) {
			// ���ݽ�ʦ��Ϣ��ȡ�������е��ڿι�ϵ
			List<TeachRelation> list = teachRelatService
					.findAllByTeacher(teacher);
			// ���ڿι�ϵ�������õ��������
			ActionContext.getContext().put("relationList", list);
		}
		/************* �������������б���Ϣ ****************/
		// �γ������б�
		List<Course> courseList = courseService.findAll();
		ActionContext.getContext().put("courseList", courseList);
		// �༶�����б�
		List<Clazz> clazzList = clazzService.findAll();
		ActionContext.getContext().put("clazzList", clazzList);
		// �ڿ�ѧ�������б�
		List<String> termList = CreateStringUtil.createTermStrs();
		ActionContext.getContext().put("termList", termList);

		// ��ȡ��ǰѧ��
		String studyYearStr = CreateStringUtil.getStudyYearStr();
		ActionContext.getContext().put("studyYearStr", studyYearStr);
		// ��ȡ��ǰѧ��
		String termInYear = CreateStringUtil.getTermInYear();
		ActionContext.getContext().put("termInYear", termInYear);
		// ��ȡ��ǰ��
		// ��ȡϵͳ�����õı�ѧ�ڿ�ʼʱ��
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
		// ��ȡ���ܵ�һ��
		String firstDay = CreateStringUtil.getFirstInThisWeek();
		ActionContext.getContext().put("firstDay", firstDay);
		// ��ȡ�������һ��
		String lastDay = CreateStringUtil.getLastInThisWeek();
		ActionContext.getContext().put("lastDay", lastDay);

		return "listSKGXUI";
	}

	/************* ������������ڿι�ϵ ****************/
	public String addSkgx() {
		// ��ȡ��ǰ��½���û���Ϣ
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// ���ݵ�ǰ���û���Ϣ��ȡ�����Ӧ�Ľ�ʦ��Ϣ
		Teacher teacher = teacherService.findTeacherByUser(user);
		// ��ǰ�û��ǽ�ʦ����������ڿι�ϵ
		if (teacher != null) {
			// �û�ѡ�����ڿ�ѧ��
			if (relation != null && StringUtils.isNotBlank(relation.getTerm())) {
				// �û�ѡ���˰༶
				if (clazz != null && StringUtils.isNotBlank(clazz.getClazzId())) {
					// �û�ѡ���˿γ�
					if (course != null
							&& StringUtils.isNotBlank(course.getCourseId())) {
						clazz = clazzService.findById(clazz.getClazzId());
						course = courseService.findById(course.getCourseId());
						relation.setClazz(clazz);
						relation.setCourse(course);
						relation.setTeacher(teacher);
						// �жϵ�ǰ�ڿι�ϵ�Ƿ��Ѿ����ڣ�����Ѿ����ڲ������
						if (teachRelatService.isNotExists(relation)) {
							// �������
							teachRelatService.add(relation);
						}
					}
				}
			}
		}
		return "list";
	}

	/************* ���������޸��ڿι�ϵ ****************/
	public String updateSkgx() {
		// ��ȡ��ǰ��½���û���Ϣ
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// ���ݵ�ǰ���û���Ϣ��ȡ�����Ӧ�Ľ�ʦ��Ϣ
		Teacher teacher = teacherService.findTeacherByUser(user);
		// ��ǰ�û��ǽ�ʦ�������޸��Լ����ڿι�ϵ
		if (teacher != null) {
			// �û�ѡ�����ڿ�ѧ��
			if (relation != null && StringUtils.isNotBlank(relation.getTerm())) {
				// �û�ѡ���˰༶
				if (clazz != null && StringUtils.isNotBlank(clazz.getClazzId())) {
					// �û�ѡ���˿γ�
					if (course != null
							&& StringUtils.isNotBlank(course.getCourseId())) {
						clazz = clazzService.findById(clazz.getClazzId());
						course = courseService.findById(course.getCourseId());
						relation.setClazz(clazz);
						relation.setCourse(course);
						relation.setTeacher(teacher);
						// �жϵ�ǰ�ڿι�ϵ�Ƿ��Ѿ����ڣ�����Ѿ����ڣ���������
						if (teachRelatService.isNotExists(relation)) {
							teachRelatService.update(relation);
						}
					}
				}
			}
		}
		return "list";
	}

	/********** ��������ɾ���ڿι�ϵ ************/
	public String deleteSkgx() {
		// ��ȡ��ǰ��½���û���Ϣ
		User user = (User) ActionContext.getContext().getSession()
				.get(Constant.USER);
		// ���ݵ�ǰ���û���Ϣ��ȡ�����Ӧ�Ľ�ʦ��Ϣ
		Teacher teacher = teacherService.findTeacherByUser(user);
		// ��ǰ�û��ǽ�ʦ������ɾ���Լ����ڿι�ϵ
		if (teacher != null) {
			if (relation != null && StringUtils.isNotBlank(relation.getId())) {
				teachRelatService.delete(relation.getId());
			}
		}
		return "list";
	}

	public String skgcgl() {
		// ��ȡ��ǰ��½�Ľ�ʦ��Ϣ
		Teacher teacher = (Teacher) ActionContext.getContext().getSession().get(Constant.TEACHER);
		// �����ʦ�ǿ�
		if (teacher != null) {
			//���ݽ�ʦ����ɹ���ԤԼ��Ϣ��ʹ��ʱ�䵹������
			List<Orders> list = orderService.findSecceedOrderByTeacher(teacher.getId());
			// ��ԤԼ��Ϣ�������õ��������
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
			//����ԤԼ��Ϣ¼��ɼ�
			if(order!=null&&StringUtils.isNotBlank(order.getOrderId())){
				Orders o = orderService.findById(order.getOrderId());
				List<Grade> grades = gradeService.findByOrder(o.getOrderId());
				if(grades!=null&&grades.size()>0){
					ActionContext.getContext().put("gradeList", grades);
					ActionContext.getContext().put("gradeState",grades.get(0).getState());
				}
				students = o.getR_tea().getClazz().getStudents();
				List<Student> studentList = new ArrayList(students);
				//��ѧ��ѧ������Ƚ���
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
				//��ȡ��ǰ�ڿι�ϵ���������ͨ����ԤԼ��Ϣ
				List<Orders> orderList = orderService.findSucceedOrdersByRelat(relation.getId());
				ActionContext.getContext().put("orderList", orderList);
				ActionContext.getContext().put("rid", relation.getId());
			}
			List<Student> studentList = null;
			if(students!=null){
				studentList = new ArrayList(students);
				//��ѧ��ѧ������Ƚ���
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
	
	//��ȡ�ɼ�¼�뷽ʽ����������
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
	
	//��ȡ�Ʒַ�ʽ
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
	
	//����ɼ���������ɺ���ת����ǰ�༭�ɼ�ҳ��
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
				//���жϵ�ǰ�ɼ��Ƿ��Ѿ����������ݿ��У�������ڣ�����£�������ӵ����ݿ���
				if(gradeService.isExists(g)){
					Grade gExists = gradeService.findByGrade(g);
					gradeService.delete(gExists.getId());
					g.setId(gExists.getId());
					gradeService.add(g);
				}else{
					//����ǰ�ɼ�����������ݿ�
					gradeService.add(g);
				}
			}
		}
		return addGradeUI();
	}
	
	//�ύ�ɼ����ύ��ɺ���ת����ʾ�ɼ�ҳ��
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
				//���жϵ�ǰ�ɼ��Ƿ��Ѿ����������ݿ��У�������ڣ�����£�������ӵ����ݿ���
				if(gradeService.isExists(g)){
					Grade gExists = gradeService.findByGrade(g);
					gradeService.delete(gExists.getId());
					g.setId(gExists.getId());
					gradeService.add(g);
				}else{
					//����ǰ�ɼ�����������ݿ�
					gradeService.add(g);
				}
			}
		}
		return "listGrade";
	}

	//���üƷ�ģʽ
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
	
	//��ѧ����Ϣ������excel
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
					+ new String("ѧ��.xls".getBytes("UTF-8"), "ISO-8859-1"));
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
	
	//����ǰ�ɼ�������excel
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
					+ new String("�ɼ�.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			gradeService.exportExcel(gradeList, outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//����ǰҳ���ϻ��ߵ�ǰ�ڿι�ϵ�����гɼ�������excel
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
				//��ѧ��ѧ������Ƚ���
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
						+ new String("ѧ��.xls".getBytes("UTF-8"), "ISO-8859-1"));
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
					//��ѧ��ѧ������Ƚ���
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
