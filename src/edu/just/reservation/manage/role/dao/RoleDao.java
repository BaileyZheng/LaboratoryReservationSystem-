package edu.just.reservation.manage.role.dao;


import edu.just.reservation.core.dao.BaseDao;
import edu.just.reservation.manage.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	void deleteRolePrivilegeByRoleId(String roleId);
	
	String getRoleIdByRoleName(String name);
}
