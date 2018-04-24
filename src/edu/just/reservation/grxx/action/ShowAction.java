package edu.just.reservation.grxx.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.manage.role.entity.Role;
import edu.just.reservation.manage.role.service.RoleService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;
import edu.just.reservation.manage.user.service.UserService;

public class ShowAction extends ActionSupport {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	
	private User user;

	public String frame(){
		return "frame";
	}
	public String left(){
		return "left";
	}
	public String top(){
		return "top";
	}
	public String welcome(){
		User user = (User) ActionContext.getContext().getSession().get(Constant.USER);
		if (user != null && user.getId() != null) {
			user = userService.findById(user.getId());
			ActionContext.getContext().getSession().put(Constant.USER, user);
			List<UserRole> userRoleList = userService.getUserRolesByUserId(user
					.getId());
			String[] roles = null;
			if (userRoleList != null && userRoleList.size() > 0) {
				roles = new String[userRoleList.size()];
				int i = 0;
				for (UserRole ur : userRoleList) {
					roles[i++] = roleService.findById(ur.getId().getRole().getRoleId()).getName();
				}
			}
			ActionContext.getContext().getContextMap().put("roles", roles);
		}
		return "welcome";
	}
	public String editGRXX(){
		user = (User) ActionContext.getContext().getSession().get(Constant.USER);
		if (user != null && user.getId() != null) {
			List<UserRole> userRoleList = userService.getUserRolesByUserId(user
					.getId());
			String[] roles = null;
			if (userRoleList != null && userRoleList.size() > 0) {
				roles = new String[userRoleList.size()];
				int i = 0;
				for (UserRole ur : userRoleList) {
					roles[i++] = roleService.findById(ur.getId().getRole().getRoleId()).getName();
				}
			}
			ActionContext.getContext().getContextMap().put("roles", roles);
		}
		return "editGRXXUI";
	}
	
	// ±£´æ±à¼­
	public String saveEdit() {
		String[] userRoleIds=null;
		String oldPwd = "";
		String newPwd = "";
		if (user != null && user.getId() != null) {
			oldPwd = userService.findById(user.getId()).getPassword();
			newPwd = user.getPassword();
			System.out.println(oldPwd+"----"+newPwd);
			List<UserRole> userRoleList = userService.getUserRolesByUserId(user
					.getId());
			if (userRoleList != null && userRoleList.size() > 0) {
				userRoleIds = new String[userRoleList.size()];
				int i = 0;
				for (UserRole ur : userRoleList) {
					userRoleIds[i++] = ur.getId().getRole().getRoleId();
				}
			}
		}
		try {
			if (user != null) {
				if (headImg != null) {
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString()
							.replace("-", "")
							+ headImgFileName.substring(headImgFileName
									.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(filePath, fileName));

					user.setHeadImg("user/" + fileName);
				}
				userService.updateUserAndRole(user, userRoleIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!oldPwd.equals(newPwd)){
			ActionContext.getContext().getSession().remove(Constant.USER);
		}
		return "toWelcome";
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	
}
