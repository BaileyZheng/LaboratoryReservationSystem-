package edu.just.reservation.core.permission;

import org.springframework.stereotype.Service;

import edu.just.reservation.manage.user.entity.User;
@Service("pcService")
public interface PermissionCheck {

	/**
	 * 判断当前用户是否具有访问code对应的子系统的权限
	 * @param user 用户
	 * @param code 子系统对应的标识符
	 * @return true or false
	 */
	public boolean isAccessible(User user, String code);

}
