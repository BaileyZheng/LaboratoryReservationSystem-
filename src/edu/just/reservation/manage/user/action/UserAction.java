package edu.just.reservation.manage.user.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.role.service.RoleService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;
import edu.just.reservation.manage.user.service.UserService;

public class UserAction extends BaseAction {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private User user;
	private String[] userRoleIds;

	private File headImg;
	private String headImgContentType;
	private String headImgFileName;

	private String strName;

	// 列表页面
	public String listUI() {
		QueryHelper queryHelper = new QueryHelper(User.class, "u");
		try {
			if (user != null) {
				if (StringUtils.isNotBlank(user.getName())) {
					user.setName(URLDecoder.decode(user.getName(), "utf-8"));
					queryHelper.addCondition("u.name like ?",
							"%" + user.getName() + "%");
				}

			}
			pageResult = userService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载角色列表
		ActionContext.getContext().getContextMap()
				.put("roleList", roleService.findAll());
		if (user != null && StringUtils.isNotBlank(user.getName())) {
			strName = user.getName();
			user.setName(null);
		}
		return "addUI";
	}

	// 保存新增
	public String add() {
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
				userService.addUserAndRole(user, userRoleIds);
				user.setName(strName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (user != null && user.getId() != null) {
			ActionContext.getContext().getContextMap()
					.put("roleList", roleService.findAll());
			strName = user.getName();
			user = userService.findById(user.getId());
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
		return "editUI";
	}

	// 保存编辑
	public String saveEdit() {
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
		return "list";
	}

	// 删除
	public String delete() {
		if (user != null && user.getId() != null) {
			if (StringUtils.isNotBlank(user.getName())) {
				strName = user.getName();
			}
			userService.delete(user.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			if (user != null && StringUtils.isNotBlank(user.getName())) {
				strName = user.getName();
			}
			for (String id : selectedRow) {
				userService.delete(id);
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
					+ new String("用户列表.xls".getBytes("UTF-8"), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userService.findAll(), outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 导入excel
	public String importExcel() {
		if (user != null && StringUtils.isNotBlank(user.getName())) {
			strName = user.getName();
		}
		if (headImg != null) {
			if (headImgFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				userService.importExcel(headImg, headImgFileName);
			}
		}
		return "list";
	}

	// 校验账号和id是否存在
	public void verifyAccount() {
		try {
			if (user != null && StringUtils.isNotBlank(user.getAccount())) {
				List<User> userList = userService.findUsersByAccountAndId(
						user.getAccount(), user.getId());
				if (userList != null && userList.size() > 0) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
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

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

}
