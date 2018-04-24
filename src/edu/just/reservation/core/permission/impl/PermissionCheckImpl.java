package edu.just.reservation.core.permission.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import edu.just.reservation.core.permission.PermissionCheck;
import edu.just.reservation.manage.role.entity.RolePrivilege;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;
import edu.just.reservation.manage.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {

	@Resource
	private UserService userService;

	public boolean isAccessible(User user, String code) {
		List<UserRole> list = user.getUserRoles();
		if (list == null || list.size() <= 0) {
			list = userService.getUserRolesByUserId(user.getId());
		}
		for (UserRole ur : list) {
			Set<RolePrivilege> privileges = ur.getId().getRole()
					.getRolePrivileges();
			for (RolePrivilege rp : privileges) {
				if (code.equals(rp.getId().getCode())) {
					return true;
				}
			}
		}
		return false;
	}

}
