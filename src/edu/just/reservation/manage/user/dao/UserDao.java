package edu.just.reservation.manage.user.dao;


import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;

public interface UserDao extends BaseDao<User>{

	//根据账号和id获取用户列表
	List<User> findUsersByAccountAndId(String account, String id);

	//添加用户角色关系
	void addUserRole(UserRole userRole);

	//根据用户的id删除用户角色关系
	void deleteUserRoleByUserId(String id);

	//根据用户id获取所有的用户角色关系
	List<UserRole> getUserRolesByUserId(String id);

	//根据账号和密码查找用户
	List<User> findUserByAccountAndPassword(String account, String password);

	boolean isNotExists(User user);

	//根据userrole删除用户权限关系
	void deleteUserRole(UserRole ur);

}
