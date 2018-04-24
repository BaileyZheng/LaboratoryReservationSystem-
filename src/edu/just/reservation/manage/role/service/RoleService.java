package edu.just.reservation.manage.role.service;


import edu.just.reservation.core.service.BaseService;
import edu.just.reservation.manage.role.entity.Role;

public interface RoleService extends BaseService<Role>{

	//根据角色名称获取角色id
	public String getRoleIdByRoleName(String name);
	
	
}
