package edu.just.reservation.core.permission;

import org.springframework.stereotype.Service;

import edu.just.reservation.manage.user.entity.User;
@Service("pcService")
public interface PermissionCheck {

	/**
	 * �жϵ�ǰ�û��Ƿ���з���code��Ӧ����ϵͳ��Ȩ��
	 * @param user �û�
	 * @param code ��ϵͳ��Ӧ�ı�ʶ��
	 * @return true or false
	 */
	public boolean isAccessible(User user, String code);

}
