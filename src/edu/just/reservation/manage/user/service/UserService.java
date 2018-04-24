package edu.just.reservation.manage.user.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;

public interface UserService extends BaseService<User>{

	//������excel
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream);

	//����excel
	public void importExcel(File headImg, String headImgFileName);

	//�����˺ź�id��ȡ�û��б�
	public List<User> findUsersByAccountAndId(String account, String id);

	//�����û������Ӧ�Ľ�ɫ
	public void addUserAndRole(User user, String... roleIds);

	//�����û������Ӧ�Ľ�ɫ
	public void updateUserAndRole(User user, String... roleIds);

	//�����û�id��ȡ���е��û���ɫ��ϵ
	public List<UserRole> getUserRolesByUserId(String id);

	//�����˺ź���������û�
	public List<User> findUserByAccountAndPassword(String account,
			String password);
}
