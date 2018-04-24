package edu.just.reservation.tjbb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import edu.just.reservation.manage.user.entity.User;
/**
 * 系统访问量统计实体类
 * 		包括以下字段：
 * 			用户名、访问时间
 * @author 郑蓓蕾
 *
 */
public class VisitStat implements Serializable {
	private String id;
	private User user;
	private Timestamp visitTime;
	public VisitStat() {
	}
	public VisitStat(String id, User user, Timestamp visitTime) {
		super();
		this.id = id;
		this.user = user;
		this.visitTime = visitTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Timestamp visitTime) {
		this.visitTime = visitTime;
	}
	
}
