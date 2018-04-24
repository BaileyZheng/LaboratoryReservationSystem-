package edu.just.reservation.xwgg.action;

import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.info.entity.Info;
import edu.just.reservation.manage.info.service.InfoService;

public class ShowAction extends BaseAction {
	@Resource
	private InfoService infoService;
	
	private String id;
	
	public String frame(){
		return "frame";
	}
	public String left(){
		return "left";
	}
	public String top(){
		return "top";
	}
	public String welcome(){
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			// 根据创建时间降序排序
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}
		return "welcome";
	}
	
	public String detail(){
		Map<String, String> map = Info.INFO_TYPE_MAP;
		Info info = infoService.findById(id);
		String type = map.get(info.getType());
		ActionContext.getContext().getContextMap().put("info", info);
		ActionContext.getContext().getContextMap().put("type", type);
		return "detailUI";
	}
	public String detailFrame(){
		ActionContext.getContext().getContextMap().put("id", id);
		return "detailFrame";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
