package edu.just.reservation.manage.order.action;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.order.service.OrderService;
import edu.just.reservation.tjbb.entity.DataStat;
import edu.just.reservation.tjbb.service.DataStatService;

public class OrderAction extends BaseAction {
	@Resource
	private OrderService orderService;
	@Resource
	private DataStatService dataStatService;
	private Orders order;
	private String strRNumber;
	private String type;
	private String way;

	// 列表页面
	public String listUI() {
		QueryHelper queryHelper = new QueryHelper(Orders.class, "o");
		try {
			if (StringUtils.isNotBlank(type)) {
				queryHelper.addCondition("o.room.RType=? ", type);
			}
			// 根据指定方式查询
			if (StringUtils.isNotBlank(way)) {
				if ("0".equals(way)) {// 查找无效
					queryHelper.addCondition("o.OState=?",
							Orders.ORDER_STATE_INVALID);
				} else if ("1".equals(way)) {// 查找全部
				} else if ("2".equals(way)) {// 查找有效
					queryHelper.addCondition("o.OState!=?",
							Orders.ORDER_STATE_INVALID);
				} else if ("3".equals(way)) {// 查找待审核
					queryHelper.addCondition("o.OState=?",
							Orders.ORDER_STATE_APPLYED);
				} else if ("4".equals(way)) {// 查找审核未通过
					queryHelper.addCondition("o.OState=?",
							Orders.ORDER_STATE_FAILED);
				} else if ("5".equals(way)) {// 查找审核通过
					queryHelper.addCondition("o.OState=?",
							Orders.ORDER_STATE_CHECKED);
				}
			}
			// 根据创建时间降序排序
			queryHelper.addOrderBy("o.orderTime", QueryHelper.ORDER_BY_DESC);
			pageResult = orderService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
		}
		return "listUI";
	}

	// 删除
	public String delete() {
		if (order != null && order.getOrderId() != null) {
			orderService.deleteByOrderId(order.getOrderId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				orderService.deleteByOrderId(id);
			}
		}
		return "list";
	}

	// 更新状态
	public void doSomething() {
		try {
			if (order != null) {
				Orders temp = orderService.findById(order.getOrderId());
				boolean isOk = true;
				if(order.getOState().equals(Orders.ORDER_STATE_CHECKED)){
					//首先判断当前教师、当前时间点是否已经有了预约成功的信息
					isOk = orderService.judgeIsOk(temp.getR_tea().getTeacher().getId(), temp.getUseDay(), temp.getUseTime(), Orders.ORDER_STATE_CHECKED);
					if(isOk){
						//如果状态是审核通过，将此预约信息添加到统计数据中
						DataStat ds = new DataStat();
						ds.setRoom(temp.getRoom());
						ds.setUseDay(temp.getUseDay());
						ds.setUseTime(temp.getUseTime());
						ds.setUseNumber(temp.getR_tea().getClazz().getSnumber());
						dataStatService.add(ds);
					}
				}
				if(isOk){
					temp.setOState(order.getOState());
					orderService.update(temp);
	
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write("更新状态成功".getBytes("UTF-8"));
					outputStream.close();
				}else{
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write("同一教师不可在同一时间预约成功两个实验室！".getBytes("UTF-8"));
					outputStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getStrRNumber() {
		return strRNumber;
	}

	public void setStrRNumber(String strRNumber) {
		this.strRNumber = strRNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

}
