package edu.just.reservation.manage.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.role.entity.Role;
import edu.just.reservation.manage.role.entity.RolePrivilege;
import edu.just.reservation.manage.role.entity.RolePrivilegeId;
import edu.just.reservation.manage.role.service.RoleService;

public class RoleAction extends BaseAction {
	@Resource
	private RoleService roleService;
	private Role role;
	private String[] privilegeIds;

	// �б�ҳ��
	public String listUI() {
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		QueryHelper queryHelper = new QueryHelper(Role.class, "r");
		try {
			if (role != null) {
				if (StringUtils.isNotBlank(role.getName())) {
					role.setName(URLDecoder.decode(role.getName(), "utf-8"));
					queryHelper.addCondition("r.name like ?",
							"%" + role.getName() + "%");
				}

			}
			pageResult = roleService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}
		return "listUI";
	}

	// ��ת������ҳ��
	public String addUI() {
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}

	// ��������
	public String add() {
		try {
			if (role != null) {
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role,
								privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// ��ת���༭ҳ��
	public String editUI() {
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			role = roleService.findById(role.getRoleId());
			if (role.getRolePrivileges() != null) {
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i = 0;
				for (RolePrivilege rp : role.getRolePrivileges()) {
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}

	// ����༭
	public String saveEdit() {
		try {
			if (role != null) {
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role,
								privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// ɾ��
	public String delete() {
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
	}

	// ����ɾ��
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
