package edu.just.reservation.manage.user.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;

public interface UserService extends BaseService<User>{

	//导出到excel
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream);

	//导入excel
	public void importExcel(File headImg, String headImgFileName);

	//根据账号和id获取用户列表
	public List<User> findUsersByAccountAndId(String account, String id);

	//保存用户及其对应的角色
	public void addUserAndRole(User user, String... roleIds);

	//更新用户及其对应的角色
	public void updateUserAndRole(User user, String... roleIds);

	//根据用户id获取所有的用户角色关系
	public List<UserRole> getUserRolesByUserId(String id);

	//根据账号和密码查找用户
	public List<User> findUserByAccountAndPassword(String account,
			String password);
}
