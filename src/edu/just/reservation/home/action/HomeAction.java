package edu.just.reservation.home.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.info.entity.Info;
import edu.just.reservation.manage.info.service.InfoService;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.order.service.OrderService;
import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.room.service.RoomService;

public class HomeAction extends BaseAction {
	@Resource
	private InfoService infoService;
	@Resource
	private RoomService roomService;
	@Resource
	private OrderService orderService;
	private List<Room> roomPictureList;

	@Override
	public String execute() throws Exception {
		//��ѯ��Ϣ
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Info.INFO_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Info.class, "i");
		try {
			queryHelper.addCondition("i.state=?", Info.INFO_STATUS_DEPLOY);
			// ���ݴ���ʱ�併������
			queryHelper.addOrderBy("i.createTime", QueryHelper.ORDER_BY_DESC);
			pageResult = infoService.getPageResult(queryHelper, 1, 5);
			//��ѯ��ͼƬ��ʵ����
			roomPictureList = roomService.findAllRoomsWithPicture();
			//��ѯ����ԤԼ��Ϣ
			List<Orders> orderList = orderService.findOrdersByOTime();
			ActionContext.getContext().put("orderList", orderList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "home";
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public List<Room> getRoomList() {
		return roomPictureList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomPictureList = roomList;
	}
	
	
}
