package edu.just.reservation.manage.info.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.info.entity.Info;
import edu.just.reservation.manage.info.service.InfoService;

public class InfoAction extends BaseAction {
	@Resource
	private InfoService infoService;
	private Info info;
	private String strTitle;

	// �б�ҳ��
	public String listUI() throws Exception {
		// ���ط��༯��
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			if (info != null) {
				if (StringUtils.isNotBlank(info.getTitle())) {
					info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
					queryHelper.addCondition("i.title like ?",
							"%" + info.getTitle() + "%");
				}

			}
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";
	}

	// ��ת������ҳ��
	public String addUI() {
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && StringUtils.isNotBlank(info.getTitle())) {
			strTitle = info.getTitle();
		}
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}

	// ��������
	public String add() {
		try {
			if (info != null) {
				infoService.add(info);
			}
			info.setTitle(strTitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// ��ת���༭ҳ��
	public String editUI() {
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && info.getInfoId() != null) {
			strTitle = info.getTitle();
			info = infoService.findById(info.getInfoId());
		}
		return "editUI";
	}

	// ����༭
	public String saveEdit() {
		try {
			if (info != null) {
				infoService.update(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// ɾ��
	public String delete() {
		if (info != null && info.getInfoId() != null) {
			strTitle = info.getTitle();
			infoService.delete(info.getInfoId());
		}
		return "list";
	}

	// ����ɾ��
	public String deleteSelected() {
		if (selectedRow != null) {
			strTitle = info.getTitle();
			for (String id : selectedRow) {
				infoService.delete(id);
			}
		}
		return "list";
	}

	// ����״̬
	public void publicInfo() {
		try {
			if (info != null) {
				Info temp = infoService.findById(info.getInfoId());
				temp.setState(info.getState());
				infoService.update(temp);

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("����״̬�ɹ�".getBytes("UTF-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

}
