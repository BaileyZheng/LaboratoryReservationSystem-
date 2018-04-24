package edu.just.reservation.manage.role.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.manage.role.dao.RoleDao;
import edu.just.reservation.manage.role.entity.Role;
import edu.just.reservation.manage.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}

	public void update(Role role) {
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		roleDao.update(role);
	}

	public String getRoleIdByRoleName(String name) {
		return roleDao.getRoleIdByRoleName(name);
	}

}
