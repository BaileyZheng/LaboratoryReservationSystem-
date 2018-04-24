package edu.just.reservation.manage.role.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import edu.just.reservation.core.dao.impl.BaseDaoImpl;
import edu.just.reservation.manage.role.dao.RoleDao;
import edu.just.reservation.manage.role.entity.Role;
import edu.just.reservation.manage.role.entity.RolePrivilege;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public void deleteRolePrivilegeByRoleId(String roleId) {
		Query query = getSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.roleId=?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

	public String getRoleIdByRoleName(String name) {
		Query query = getSession().createQuery("FROM Role WHERE name=?");
		query.setParameter(0, name);
		List<Role> list = query.list();
		if(list!=null&&list.size()>0){
			return ((Role)list.get(0)).getRoleId();
		}
		return null;
	}

	public void update(Role role){
		String hql = "UPDATE Role r SET r.name=?,r.state=? WHERE r.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, role.getName());
		query.setParameter(1, role.getState());
		query.setParameter(2, role.getRoleId());
		query.executeUpdate();
		for(RolePrivilege rp:role.getRolePrivileges()){
			SQLQuery query2 = getSession().createSQLQuery("insert into role_privilege values(?,?)");
			query2.setParameter(0, rp.getId().getRole().getRoleId());
			query2.setParameter(1,rp.getId().getCode() );
			query2.executeUpdate();
		}
	}

}
