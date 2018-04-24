package edu.just.reservation.manage.user.dao.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.user.dao.UserDao;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.manage.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public List<User> findUsersByAccountAndId(String account, String id) {
		String hql = "FROM User WHERE account=?";
		if(StringUtils.isNotBlank(id)){
			hql+=" and id!=?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1, id);
		}
		return query.list();
	}

	public void addUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	public void deleteUserRoleByUserId(String id) {
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	public List<UserRole> getUserRolesByUserId(String id) {
		Query query = getSession().createQuery("FROM UserRole WHERE id.userId=?");
		query.setParameter(0, id);
		return query.list();
	}

	public List<User> findUserByAccountAndPassword(String account,
			String password) {
		Query query = getSession().createQuery("FROM User WHERE account=? AND password=?");
		query.setParameter(0, account);
		query.setParameter(1, password);
		return query.list();
	}

	public boolean isNotExists(User user) {
		Query query = getSession().createQuery("FROM User WHERE name=? AND account=? AND gender=? AND mobile=? AND email=? AND birthday=?");
		query.setParameter(0, user.getName());
		query.setParameter(1, user.getAccount());
		query.setParameter(2, user.isGender());
		query.setParameter(3, user.getMobile());
		query.setParameter(4, user.getEmail());
		query.setParameter(5, user.getBirthday());
		if(query.list().size()>0){
			return false;
		}
		return true;
	}
	
	public void update(User user){
		String hql = "UPDATE User u SET u.name=?,u.account=?,u.password=?,u.headImg=?,u.gender=?,u.state=?,u.mobile=?,u.email=?,u.birthday=?,u.memo=? WHERE u.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, user.getName());
		query.setParameter(1, user.getAccount());
		query.setParameter(2, user.getPassword());
		query.setParameter(3, user.getHeadImg());
		query.setParameter(4, user.isGender());
		query.setParameter(5, user.getState());
		query.setParameter(6, user.getMobile());
		query.setParameter(7, user.getEmail());
		query.setParameter(8, user.getBirthday());
		query.setParameter(9, user.getMemo());
		query.setParameter(10, user.getId());
		query.executeUpdate();
	}

	public void deleteUserRole(UserRole ur) {
		String hql = "DELETE FROM UserRole WHERE id.userId=? AND id.role.roleId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, ur.getId().getUserId());
		query.setParameter(1, ur.getId().getRole().getRoleId());
		query.executeUpdate();
	}
}
