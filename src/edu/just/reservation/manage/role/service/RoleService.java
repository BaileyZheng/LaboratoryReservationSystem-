package edu.just.reservation.manage.role.service;


import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.role.entity.Role;

public interface RoleService extends BaseService<Role>{

	//���ݽ�ɫ���ƻ�ȡ��ɫid
	public String getRoleIdByRoleName(String name);
	
	
}
