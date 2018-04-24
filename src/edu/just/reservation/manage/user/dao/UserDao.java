package edu.just.reservation.manage.user.dao;


import java.util.List;

import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;

public interface UserDao extends BaseDao<User>{

	//�����˺ź�id��ȡ�û��б�
	List<User> findUsersByAccountAndId(String account, String id);

	//����û���ɫ��ϵ
	void addUserRole(UserRole userRole);

	//�����û���idɾ���û���ɫ��ϵ
	void deleteUserRoleByUserId(String id);

	//�����û�id��ȡ���е��û���ɫ��ϵ
	List<UserRole> getUserRolesByUserId(String id);

	//�����˺ź���������û�
	List<User> findUserByAccountAndPassword(String account, String password);

	boolean isNotExists(User user);

	//����userroleɾ���û�Ȩ�޹�ϵ
	void deleteUserRole(UserRole ur);

}
