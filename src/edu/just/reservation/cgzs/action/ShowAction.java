package edu.just.reservation.cgzs.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.info.entity.Info;
import edu.just.reservation.manage.info.service.InfoService;
import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.room.service.RoomService;

public class ShowAction extends BaseAction {
	@Resource
	private RoomService roomService;
	@Resource
	private InfoService infoService;
	
	private String id;
	private String type;
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
		List<Room> roomList = roomService.findAll();
		ActionContext.getContext().put("roomList", roomList);
		ActionContext.getContext().put("map", Room.ROOM_TYPE_MAP);
		return "welcome";
	}
	public String room(){
		if(type!=null&&!"".equals(type.trim())){
			List<Room> roomList = roomService.findAllRoomsByType(type);
			ActionContext.getContext().put("roomList", roomList);
			ActionContext.getContext().put("map", Room.ROOM_TYPE_MAP);
		}
		return "room";
	}
	public String student(){
		// 加载分类集合
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			//添加查询条件：信息类型为失物招领
			queryHelper.addCondition("i.type=?",Info.INFO_TYPE_CGZS);
			// 根据创建时间降序排序
			queryHelper.addOrderBy("i.createTime",
					QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}
		return "student";
	}
	public String imageFrame(){
		return "imageFrame";
	}
	public String imageUI(){
		if(id!=null&&!"".equals(id.trim())){
			Room room = roomService.findById(id);
			ActionContext.getContext().put("room", room);
			ActionContext.getContext().put("map", Room.ROOM_TYPE_MAP);
		}
		return "imageUI";
	}
	public String detailUI(){
		if(id!=null&&!"".equals(id.trim())){
			Room room = roomService.findById(id);
			ActionContext.getContext().put("room", room);
			ActionContext.getContext().put("map", Room.ROOM_TYPE_MAP);
		}
		return "detailUI";
	}
	
	public String stuDetailUI(){
		Map<String, String> map = Info.INFO_TYPE_MAP;
		Info info = infoService.findById(id);
		String type = map.get(info.getType());
		ActionContext.getContext().getContextMap().put("info", info);
		ActionContext.getContext().getContextMap().put("type", type);
		return "stuDetailUI";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
