package edu.just.reservation.tjbb.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.util.CreateStringUtil;
import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.room.service.RoomService;
import edu.just.reservation.tjbb.service.DataStatService;
import edu.just.reservation.tjbb.service.VisitStatService;

public class ShowAction extends ActionSupport {
	@Resource
	private RoomService roomService;
	@Resource
	private DataStatService dataStatService;
	@Resource
	private VisitStatService visitStatService;
	
	private List<Room> roomList;
	private Room room;
	private String period;
	private String chartType;
	private String statType;
	private String detailTime;
	private Map<String,String> periodMap = new LinkedHashMap<String,String>(){{put("day","按天统计");put("week","按周统计");put("month","按月统计");put("year","按年统计");}};
	private Map<String,String> chartTypeMap = new LinkedHashMap<String,String>(){{put("line","折线图");put("column2d","二维柱状图");put("column3d","三维柱状图");put("pie2d","二维饼图");put("pie3d","三维饼图");}};
	private Map<String,String> statTypeMap = new LinkedHashMap<String,String>(){{put("time","使用时长");put("people","使用人数");}};
	private Map<String,String> detailTimeMap = new LinkedHashMap<String,String>();
	
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
		return "welcome";
	}
	
	public String room(){
		try{
			String caption="";
			String subCaption = "";
			String xName = "";
			String yName = "";
			String way = "";
			if(StringUtils.isNotBlank(chartType)&&!"0".equals(chartType)){
				ActionContext.getContext().put("chartType", chartType);
			}
			if(StringUtils.isNotBlank(period)&&!"0".equals(period)){
				ActionContext.getContext().put("period", period);
			}
			if(StringUtils.isNotBlank(detailTime)){
				ActionContext.getContext().put("detailTime", detailTime);
			}
			if(StringUtils.isNotBlank(statType)&&!"0".equals(statType)){
				ActionContext.getContext().put("statType", statType);
				if("time".equals(statType)){
					way = "使用时长统计";
					yName ="使用时长";
				}else if("people".equals(statType)){
					yName = "使用人数";
					way = "使用人数统计";
				}
			}
			if(!StringUtils.isNotBlank(way)){
				way = "使用时长统计";
			}
			if(room!=null&&StringUtils.isNotBlank(room.getRoomId())&&!"0".equals(room.getRoomId())){
				room = roomService.findById(room.getRoomId());
				caption = "实验室"+room.getRNumber()+way;
				if("day".equals(period)||"0".equals(period)){
					subCaption = "本周每日报表";
					xName = "日期";
				}else if("week".equals(period)){
					subCaption = "本学期每周报表";
					xName ="周次";
				}else if("month".equals(period)){
					subCaption = "本年度每月报表";
					xName = "月份";
				}
			}else{
				room = null;
				caption = "实验室"+way;
				xName = "实验室";
				if("day".equals(period)){
					subCaption ="今日报表";
				}else if("year".equals(period)){
					subCaption = "今年报表";
				}else if("week".equals(period)){
					if(StringUtils.isNotBlank(detailTime)&&!"0".equals(detailTime)){
						subCaption = "第"+detailTime+"周报表";
					}else{
						subCaption = "本周报表";
					}
				}else if("month".equals(period)){
					if(StringUtils.isNotBlank(detailTime)&&!"0".equals(detailTime)){
						subCaption = detailTime+"月报表";
					}else{
						subCaption = "本月报表";
					}
				}
			}
			ActionContext.getContext().put("caption", caption);
			ActionContext.getContext().put("subCaption", subCaption);
			ActionContext.getContext().put("xName", xName);
			ActionContext.getContext().put("yName", yName);
			roomList = roomService.findAll();
			return "room";
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	public void getData(){
		try {
			//当前时间
			Calendar cal = Calendar.getInstance();
			//当前时间所在的年
			int year = cal.get(Calendar.YEAR);
			//当前时间所在的月
			int month = cal.get(Calendar.MONTH)+1;
			String m = getMonthOrDayStr(month);
			//当前时间所在的日
			int day = cal.get(Calendar.DATE);
			String d = getMonthOrDayStr(day);
			Timestamp end = new Timestamp(cal.getTimeInMillis());
			Timestamp start=new Timestamp(cal.getTimeInMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,String> dataMap = new LinkedHashMap<String,String>();
			//用户选择了参与统计的实验室
			if(room!=null&&StringUtils.isNotBlank(room.getRoomId())&&!"0".equals(room.getRoomId())){
				//用户没有选择统计类型，默认按时间统计
				if(!StringUtils.isNotBlank(statType)||"0".equals(statType)){
					statType = "time";
				}
				if(!StringUtils.isNotBlank(period)||"0".equals(period)){
					period = "day";
				}
				dataMap = dataStatService.getMapByRoom(statType, period, room.getRoomId());
			}else{//没有选择实验室
				//用户选择了统计时间
				if(StringUtils.isNotBlank(period)&&!"0".equals(period)){
					//用户选择了统计的具体时间
					if(StringUtils.isNotBlank(detailTime)){
						//统计时间是按周统计
						if("week".equals(period)){
							String timeStr = (String) ActionContext.getContext().getApplication().get(Constant.SYS_TIME);
							Date date = sdf.parse(timeStr);
							int weekNum = Integer.parseInt(detailTime);
							start = new Timestamp(sdf.parse(CreateStringUtil.getFirstInOneWeek(weekNum, date)).getTime());
							end = new Timestamp(sdf.parse(CreateStringUtil.getLastInOneWeek(weekNum, date)).getTime());
						}else if("month".equals(period)){//统计时间是按月统计
							String monNum = detailTime;
							cal.setTimeInMillis(sdf.parse(year+"-"+getMonthOrDayStr(Integer.parseInt(monNum))+"-01").getTime());
							start = new Timestamp(cal.getTimeInMillis());
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							end = new Timestamp(cal.getTimeInMillis());
						}
					}else{//用户没有选择统计的具体时间
						//统计时间是按天计算的情况
						if("day".equals(period)){
							//默认显示今天的实验室使用情况，即今天所有实验室使用时长的三维柱形图
							start = new Timestamp(sdf.parse(year+"-"+m+"-"+d).getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}else if("week".equals(period)){//统计时间是按周计算的情况
							//默认显示本周的实验室使用情况，即本周所有实验室使用时长的三维柱形图
							String timeStr = CreateStringUtil.getFirstInThisWeek();
							start = new Timestamp(sdf.parse(timeStr).getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}else if("month".equals(period)){//统计时间是按月计算的情况
							//默认显示本月的实验室使用情况，即本月所有实验室使用时长的三维柱形图
							start = new Timestamp(sdf.parse(year+"-"+m+"-01").getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}else if("year".equals(period)){//统计时间是按年计算的情况
							//默认显示本年度的实验室使用情况,即本年所有实验室使用时长的三维柱形图
							start = new Timestamp(sdf.parse(year+"-01-01").getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}
						
					}
				}else{//用户没有选择统计时间
					//默认显示本周所有实验室使用时长的三维柱形图
					end = new Timestamp(cal.getTimeInMillis());
					cal.add(Calendar.DATE, -7);
					start = new Timestamp(cal.getTimeInMillis());
				}
				if(!StringUtils.isNotBlank(statType)||"0".equals(statType)){
					statType = "time";
				}
				dataMap = dataStatService.getRoomsByTime(start, end, statType);
			}
			//以JSON形式返回数据集
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(JSONArray.fromObject(dataMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String getMonthOrDayStr(int month) {
		String m="";
		if(month<10){
			m = "0"+month;
		}else{
			m = ""+month;
		}
		return m;
	}

	public void getDetailTimeMaps(){
		try {
			if(StringUtils.isNotBlank(period)&&!period.equals("0")){
				if(period.equals("week")){
					detailTimeMap = new LinkedHashMap<String,String>();
					detailTimeMap.put("0", "---请选择周---");
					for(int i = 1;i<=20;i++){
						detailTimeMap.put(i+"", "第"+i+"周");
					}
				}else if(period.equals("month")){
					detailTimeMap = new LinkedHashMap<String,String>();
					detailTimeMap.put("0", "---请选择月---");
					for(int i = 1;i<=12;i++){
						detailTimeMap.put(i+"", i+"月");
					}
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(JSONArray.fromObject(detailTimeMap));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getVisitData(){
		try {
			Map<String,String> dataMap = new LinkedHashMap<String,String>();
			dataMap = visitStatService.getDataByPeriod(period);
			//以JSON形式返回数据集
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(JSONArray.fromObject(dataMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String visit(){
		try{
			String subCaption = "";
			if(StringUtils.isNotBlank(chartType)&&!"0".equals(chartType)){
				ActionContext.getContext().put("chartType", chartType);
			}
			if(StringUtils.isNotBlank(period)&&!"0".equals(period)){
				ActionContext.getContext().put("period", period);
				if("day".equals(period)){
					subCaption = "最近一周每天统计报表";
				}else if("week".equals(period)){
					subCaption = "本学期每周统计报表";
				}else if("month".equals(period)){
					subCaption = "本年度每月统计报表";
				}
			}
			ActionContext.getContext().put("subCaption", subCaption);
			return "visit";
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public List<Room> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getStatType() {
		return statType;
	}
	public void setStatType(String statType) {
		this.statType = statType;
	}
	
	public String getDetailTime() {
		return detailTime;
	}
	public void setDetailTime(String detailTime) {
		this.detailTime = detailTime;
	}
	public Map<String, String> getPeriodMap() {
		return periodMap;
	}
	public void setPeriodMap(Map<String, String> periodMap) {
		this.periodMap = periodMap;
	}
	public Map<String, String> getChartTypeMap() {
		return chartTypeMap;
	}
	public void setChartTypeMap(Map<String, String> chartTypeMap) {
		this.chartTypeMap = chartTypeMap;
	}
	public Map<String, String> getStatTypeMap() {
		return statTypeMap;
	}
	public void setStatTypeMap(Map<String, String> statTypeMap) {
		this.statTypeMap = statTypeMap;
	}
	public Map<String, String> getDetailTimeMap() {
		return detailTimeMap;
	}
	public void setDetailTimeMap(Map<String, String> detailTimeMap) {
		this.detailTimeMap = detailTimeMap;
	}
	
}
