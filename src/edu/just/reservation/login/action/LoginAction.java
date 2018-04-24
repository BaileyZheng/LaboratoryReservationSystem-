package edu.just.reservation.login.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.permission.PermissionCheck;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.service.UserService;
import edu.just.reservation.tjbb.entity.VisitStat;
import edu.just.reservation.tjbb.service.VisitStatService;

public class LoginAction extends ActionSupport {
	@Resource
	private UserService userService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private VisitStatService visitStatService;
	@Resource
	private PermissionCheck pcService;
	private User user;
	private String resultValue;

	// ��ת����¼ҳ��
	public String toLoginUI() {
		return "loginUI";
	}

	// ��½
	public String login() {
		if(user!=null){
			if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
				List<User> list = userService.findUserByAccountAndPassword(user.getAccount(),user.getPassword());
				if(list!=null&&list.size()>0){
					User userInfo = list.get(0);
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, userInfo);
					if(pcService.isAccessible(userInfo, "sjgl")){
						ServletActionContext.getRequest().getSession().setAttribute(Constant.ADMIN, userInfo);
					}
					Teacher teacher = teacherService.findTeacherByUser(userInfo);
					// �����ʦ�ǿ�
					if (teacher != null) {
						ServletActionContext.getRequest().getSession().setAttribute(Constant.TEACHER, teacher);
					}
					user.setUserRoles(userService.getUserRolesByUserId(userInfo.getId()));
					VisitStat vs = new VisitStat();
					vs.setUser(userInfo);
					vs.setVisitTime(new Timestamp(new Date().getTime()));
					visitStatService.add(vs);
					Log log = LogFactory.getLog(getClass());
					log.info("�û���"+userInfo.getName()+"��½��ϵͳ��");
					return "home";
				}else{
					resultValue="�˺Ż��������";
				}
			}else{
				resultValue="�˺Ż����벻��Ϊ�գ�";
			}
		}else{
			resultValue="�������˺ź����룡";
		}
		return toLoginUI();
	}

	// �˳���ע��
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		if(ServletActionContext.getRequest().getSession().getAttribute(Constant.TEACHER)!=null){
			ServletActionContext.getRequest().getSession().removeAttribute(Constant.TEACHER);
		}
		if(ServletActionContext.getRequest().getSession().getAttribute(Constant.ADMIN)!=null){
			ServletActionContext.getRequest().getSession().removeAttribute(Constant.ADMIN);
		}
		return toLoginUI();
	}

	//û��Ȩ��
	public String toNoPermissionUI(){
		return "noPermissionUI";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	
	
}
