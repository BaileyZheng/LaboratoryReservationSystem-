package edu.just.reservation.manage.graderate.entity;

import java.io.Serializable;

import edu.just.reservation.manage.order.entity.Orders;
/**
 * �Ʒֱ���ʵ����
 * 		�������ڱ��ʡ����ñ��ֱ��ʺ�ʵ���������
 * 		�Ʒ���ʽ���弶���ƺͰٷ��ƣ�
 * 		�Լ�ԤԼ��Ϣ�������жϵ�ǰԤԼ��Ϣ�Ƿ��Ѿ����ñ��ʣ�
 * @author ֣����
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
