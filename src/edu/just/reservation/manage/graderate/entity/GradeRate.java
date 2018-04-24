package edu.just.reservation.manage.graderate.entity;

import java.io.Serializable;

import edu.just.reservation.manage.order.entity.Orders;
/**
 * 计分比率实体类
 * 		包括出勤比率、课堂表现比率和实验情况比率
 * 		计分形式（五级分制和百分制）
 * 		以及预约信息（用于判断当前预约信息是否已经设置比率）
 * @author 郑蓓蕾
 *
 */
public class GradeRate implements Serializable {
	private String id;
	private int cqrate;
	private int ktrate;
	private int syrate;
	private String mode;
	private Orders order;
	
	public static String GRADE_MODE_WJFZ = "wjfz";
	public static String GRADE_MODE_BFZ = "bfz";
	public GradeRate() {
	}
	public GradeRate(String id, int cqrate, int ktrate, int syrate,
			String mode, Orders order) {
		this.id = id;
		this.cqrate = cqrate;
		this.ktrate = ktrate;
		this.syrate = syrate;
		this.mode = mode;
		this.order = order;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCqrate() {
		return cqrate;
	}
	public void setCqrate(int cqrate) {
		this.cqrate = cqrate;
	}
	public int getKtrate() {
		return ktrate;
	}
	public void setKtrate(int ktrate) {
		this.ktrate = ktrate;
	}
	public int getSyrate() {
		return syrate;
	}
	public void setSyrate(int syrate) {
		this.syrate = syrate;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
