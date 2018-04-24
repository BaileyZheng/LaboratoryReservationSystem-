package edu.just.reservation.yyxx.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.order.service.OrderService;

public class ShowAction extends ActionSupport {
	@Resource
	private OrderService orderService;
	
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
		List<Orders> orderList = orderService.findOrdersByOTime();
		ActionContext.getContext().put("orderList", orderList);
		return "welcome";
	}
}
