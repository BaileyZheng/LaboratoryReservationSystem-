package edu.just.reservation.xwgg.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.info.entity.Info;
import edu.just.reservation.manage.info.service.InfoService;

public class InfoAction extends BaseAction {
	@Resource
	private InfoService infoService;
	private Info info;
	
	public String toListXWDT(){
		// ���ط��༯��
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//��Ӳ�ѯ��������Ϣ����Ϊʧ������
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_XWDT);
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listXWDT";
	}
	public String toListGZZD(){
		// ���ط��༯��
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//��Ӳ�ѯ��������Ϣ����Ϊʧ������
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_GZZD);
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listGZZD";
	}
	//��ת����ʾ����ʧ��������Ϣҳ��
	public String toListSWZL(){
		// ���ط��༯��
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//��Ӳ�ѯ��������Ϣ����Ϊʧ������
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_SWZL);
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listSWZL";
	}
	public String toListTZGG(){
		// ���ط��༯��
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//��Ӳ�ѯ��������Ϣ����Ϊʧ������
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_TZGG);
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listTZGG";
	}
	public String toListXWQS(){
		// ���ط��༯��
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//��Ӳ�ѯ��������Ϣ����Ϊʧ������
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_XWQS);
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listXWQS";
	}

	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
}
