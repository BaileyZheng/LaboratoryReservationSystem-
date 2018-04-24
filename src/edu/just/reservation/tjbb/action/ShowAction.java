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
	private Map<String,String> periodMap = new LinkedHashMap<String,String>(){{put("day","����ͳ��");put("week","����ͳ��");put("month","����ͳ��");put("year","����ͳ��");}};
	private Map<String,String> chartTypeMap = new LinkedHashMap<String,String>(){{put("line","����ͼ");put("column2d","��ά��״ͼ");put("column3d","��ά��״ͼ");put("pie2d","��ά��ͼ");put("pie3d","��ά��ͼ");}};
	private Map<String,String> statTypeMap = new LinkedHashMap<String,String>(){{put("time","ʹ��ʱ��");put("people","ʹ������");}};
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
					way = "ʹ��ʱ��ͳ��";
					yName ="ʹ��ʱ��";
				}else if("people".equals(statType)){
					yName = "ʹ������";
					way = "ʹ������ͳ��";
				}
			}
			if(!StringUtils.isNotBlank(way)){
				way = "ʹ��ʱ��ͳ��";
			}
			if(room!=null&&StringUtils.isNotBlank(room.getRoomId())&&!"0".equals(room.getRoomId())){
				room = roomService.findById(room.getRoomId());
				caption = "ʵ����"+room.getRNumber()+way;
				if("day".equals(period)||"0".equals(period)){
					subCaption = "����ÿ�ձ���";
					xName = "����";
				}else if("week".equals(period)){
					subCaption = "��ѧ��ÿ�ܱ���";
					xName ="�ܴ�";
				}else if("month".equals(period)){
					subCaption = "�����ÿ�±���";
					xName = "�·�";
				}
			}else{
				room = null;
				caption = "ʵ����"+way;
				xName = "ʵ����";
				if("day".equals(period)){
					subCaption ="���ձ���";
				}else if("year".equals(period)){
					subCaption = "���걨��";
				}else if("week".equals(period)){
					if(StringUtils.isNotBlank(detailTime)&&!"0".equals(detailTime)){
						subCaption = "��"+detailTime+"�ܱ���";
					}else{
						subCaption = "���ܱ���";
					}
				}else if("month".equals(period)){
					if(StringUtils.isNotBlank(detailTime)&&!"0".equals(detailTime)){
						subCaption = detailTime+"�±���";
					}else{
						subCaption = "���±���";
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
			//��ǰʱ��
			Calendar cal = Calendar.getInstance();
			//��ǰʱ�����ڵ���
			int year = cal.get(Calendar.YEAR);
			//��ǰʱ�����ڵ���
			int month = cal.get(Calendar.MONTH)+1;
			String m = getMonthOrDayStr(month);
			//��ǰʱ�����ڵ���
			int day = cal.get(Calendar.DATE);
			String d = getMonthOrDayStr(day);
			Timestamp end = new Timestamp(cal.getTimeInMillis());
			Timestamp start=new Timestamp(cal.getTimeInMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Map<String,String> dataMap = new LinkedHashMap<String,String>();
			//�û�ѡ���˲���ͳ�Ƶ�ʵ����
			if(room!=null&&StringUtils.isNotBlank(room.getRoomId())&&!"0".equals(room.getRoomId())){
				//�û�û��ѡ��ͳ�����ͣ�Ĭ�ϰ�ʱ��ͳ��
				if(!StringUtils.isNotBlank(statType)||"0".equals(statType)){
					statType = "time";
				}
				if(!StringUtils.isNotBlank(period)||"0".equals(period)){
					period = "day";
				}
				dataMap = dataStatService.getMapByRoom(statType, period, room.getRoomId());
			}else{//û��ѡ��ʵ����
				//�û�ѡ����ͳ��ʱ��
				if(StringUtils.isNotBlank(period)&&!"0".equals(period)){
					//�û�ѡ����ͳ�Ƶľ���ʱ��
					if(StringUtils.isNotBlank(detailTime)){
						//ͳ��ʱ���ǰ���ͳ��
						if("week".equals(period)){
							String timeStr = (String) ActionContext.getContext().getApplication().get(Constant.SYS_TIME);
							Date date = sdf.parse(timeStr);
							int weekNum = Integer.parseInt(detailTime);
							start = new Timestamp(sdf.parse(CreateStringUtil.getFirstInOneWeek(weekNum, date)).getTime());
							end = new Timestamp(sdf.parse(CreateStringUtil.getLastInOneWeek(weekNum, date)).getTime());
						}else if("month".equals(period)){//ͳ��ʱ���ǰ���ͳ��
							String monNum = detailTime;
							cal.setTimeInMillis(sdf.parse(year+"-"+getMonthOrDayStr(Integer.parseInt(monNum))+"-01").getTime());
							start = new Timestamp(cal.getTimeInMillis());
							cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); 
							end = new Timestamp(cal.getTimeInMillis());
						}
					}else{//�û�û��ѡ��ͳ�Ƶľ���ʱ��
						//ͳ��ʱ���ǰ����������
						if("day".equals(period)){
							//Ĭ����ʾ�����ʵ����ʹ�����������������ʵ����ʹ��ʱ������ά����ͼ
							start = new Timestamp(sdf.parse(year+"-"+m+"-"+d).getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}else if("week".equals(period)){//ͳ��ʱ���ǰ��ܼ�������
							//Ĭ����ʾ���ܵ�ʵ����ʹ�����������������ʵ����ʹ��ʱ������ά����ͼ
							String timeStr = CreateStringUtil.getFirstInThisWeek();
							start = new Timestamp(sdf.parse(timeStr).getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}else if("month".equals(period)){//ͳ��ʱ���ǰ��¼�������
							//Ĭ����ʾ���µ�ʵ����ʹ�����������������ʵ����ʹ��ʱ������ά����ͼ
							start = new Timestamp(sdf.parse(year+"-"+m+"-01").getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}else if("year".equals(period)){//ͳ��ʱ���ǰ����������
							//Ĭ����ʾ����ȵ�ʵ����ʹ�����,����������ʵ����ʹ��ʱ������ά����ͼ
							start = new Timestamp(sdf.parse(year+"-01-01").getTime());
							end = new Timestamp(cal.getTimeInMillis());
						}
						
					}
				}else{//�û�û��ѡ��ͳ��ʱ��
					//Ĭ����ʾ��������ʵ����ʹ��ʱ������ά����ͼ
					end = new Timestamp(cal.getTimeInMillis());
					cal.add(Calendar.DATE, -7);
					start = new Timestamp(cal.getTimeInMillis());
				}
				if(!StringUtils.isNotBlank(statType)||"0".equals(statType)){
					statType = "time";
				}
				dataMap = dataStatService.getRoomsByTime(start, end, statType);
			}
			//��JSON��ʽ�������ݼ�
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
					detailTimeMap.put("0", "---��ѡ����---");
					for(int i = 1;i<=20;i++){
						detailTimeMap.put(i+"", "��"+i+"��");
					}
				}else if(period.equals("month")){
					detailTimeMap = new LinkedHashMap<String,String>();
					detailTimeMap.put("0", "---��ѡ����---");
					for(int i = 1;i<=12;i++){
						detailTimeMap.put(i+"", i+"��");
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
			//��JSON��ʽ�������ݼ�
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
					subCaption = "���һ��ÿ��ͳ�Ʊ���";
				}else if("week".equals(period)){
					subCaption = "��ѧ��ÿ��ͳ�Ʊ���";
				}else if("month".equals(period)){
					subCaption = "�����ÿ��ͳ�Ʊ���";
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
