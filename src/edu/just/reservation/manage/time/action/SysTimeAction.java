package edu.just.reservation.manage.time.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.core.constant.Constant;

public class SysTimeAction extends ActionSupport {
	private String timeStr;

	// ��ת������ʱ��ҳ��
	public String timeUI() {
		String obj = (String) ActionContext.getContext().getApplication().get(Constant.SYS_TIME);
		if (obj != null) {
			timeStr = obj;
		}
		return "time";
	}

	// ����ʱ��
	public String saveTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("undefined".equals(timeStr)){
			ActionContext.getContext().getApplication().put(Constant.SYS_TIME, sdf.format(new Date()));
		}else if(timeStr.contains("-")){
			ActionContext.getContext().getApplication().put(Constant.SYS_TIME, timeStr);
		}
		return timeUI();
	}
	
	//����ʱ��
	public String reset(){
		ActionContext.getContext().getApplication().remove(Constant.SYS_TIME);
		return timeUI();
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

}
