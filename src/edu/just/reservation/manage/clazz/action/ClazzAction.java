package edu.just.reservation.manage.clazz.action;

import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.clazz.service.ClazzService;

public class ClazzAction extends BaseAction {
	@Resource
	private ClazzService clazzService;
	private Clazz clazz;
	private String strCnumber;

	// 列表页面
	public String listUI() throws Exception {
		QueryHelper queryHelper = new QueryHelper(Clazz.class, "c");
		try {
			if (clazz != null) {
				if (clazz.getCname()!=null&&!"".equals(clazz.getCname())) {
					clazz.setCname(URLDecoder.decode(clazz.getCname(),
							"utf-8"));
					queryHelper.addCondition("c.cname like ?",
							"%" + clazz.getCname() + "%");
				}
			}
			pageResult = clazzService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		if (clazz != null && StringUtils.isNotBlank(clazz.getCnumber())) {
			strCnumber = clazz.getCnumber();
		}
		clazz = null;
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (clazz != null) {
				clazzService.add(clazz);
			}
			clazz.setCnumber(strCnumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (clazz != null && clazz.getClazzId() != null) {
			strCnumber = clazz.getCnumber();
			clazz = clazzService.findById(clazz.getClazzId());
		}
		return "editUI";
	}

	// 保存编辑
	public String saveEdit() {
		try {
			if (clazz != null) {
				clazzService.update(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (clazz != null && clazz.getClazzId() != null) {
			strCnumber = clazz.getCnumber();
			clazzService.delete(clazz.getClazzId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			strCnumber = clazz.getCnumber();
			for (String id : selectedRow) {
				clazzService.delete(id);
			}
		}
		return "list";
	}

	// 更新状态
	public void publicClazz() {
		try {
			if (clazz != null) {
				Clazz temp = clazzService.findById(clazz.getClazzId());
				temp.setClazzState(clazz.getClazzState());
				clazzService.update(temp);

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("更新状态成功".getBytes("UTF-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getStrCnumber() {
		return strCnumber;
	}

	public void setStrCnumber(String strCnumber) {
		this.strCnumber = strCnumber;
	}

}
