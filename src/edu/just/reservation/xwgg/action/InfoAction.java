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
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//添加查询条件：信息类型为失物招领
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_XWDT);
			// 根据创建时间降序排序
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listXWDT";
	}
	public String toListGZZD(){
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//添加查询条件：信息类型为失物招领
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_GZZD);
			// 根据创建时间降序排序
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listGZZD";
	}
	//跳转到显示所有失物招领信息页面
	public String toListSWZL(){
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//添加查询条件：信息类型为失物招领
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_SWZL);
			// 根据创建时间降序排序
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listSWZL";
	}
	public String toListTZGG(){
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//添加查询条件：信息类型为失物招领
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_TZGG);
			// 根据创建时间降序排序
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}

		return "listTZGG";
	}
	public String toListXWQS(){
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//添加查询条件：信息类型为失物招领
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_XWQS);
			// 根据创建时间降序排序
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
