package edu.just.reservation.tjbb.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.core.util.CreateStringUtil;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.tjbb.dao.DataStatDao;
import edu.just.reservation.tjbb.entity.DataStat;
import edu.just.reservation.tjbb.service.DataStatService;

@Service("dataStatService")
public class DataStatServiceImpl extends BaseServiceImpl<DataStat> implements
		DataStatService {
	private DataStatDao dataStatDao;
	@Resource
	public void setDataStatDao(DataStatDao dataStatDao) {
		super.setBaseDao(dataStatDao);
		this.dataStatDao = dataStatDao;
	}
	public void deleteByOrder(Orders order) {
		dataStatDao.deleteByOrder(order);
	}
	public Map<String, String> getRoomsByTime(Timestamp start, Timestamp end,
			String type) {
		Map<String,String> roomMap = new HashMap<String,String>();
		List<DataStat> dataList = dataStatDao.findByTime(start, end);
		for(int i = 0;i<dataList.size();i++){
			DataStat ds = dataList.get(i);
			String key = ds.getRoom().getRNumber();
			if(roomMap.containsKey(key)){
				String value = roomMap.get(key);
				if("time".equals(type)){
					int val = Integer.parseInt(value);
					val = val+1;
					roomMap.put(key, val+"");
				}else if("people".equals(type)){
					int val = Integer.parseInt(value);
					val = val + ds.getUseNumber();
					roomMap.put(key, val+"");
				}
			}else{
				if("time".equals(type)){
					roomMap.put(key, "1");
				}else if("people".equals(type)){
					roomMap.put(key, ds.getUseNumber()+"");
				}
			}
		}
		return roomMap;
	}
	public Map<String, String> getMapByRoom(String type, String period, String roomId) throws ParseException {
		Map<String,String> map = new LinkedHashMap<String,String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		if("day".equals(period)){
			for(int i = 6;i>=0;i--){
				cal.add(Calendar.DATE, -i);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Timestamp start = new Timestamp(cal.getTimeInMillis());
				cal.add(Calendar.DATE, 1);
				Timestamp end = new Timestamp(cal.getTimeInMillis());
				List<DataStat> list = dataStatDao.findByTimeAndRoom(start, end, roomId);
				if("time".equals(type)){
					map.put(sdf.format(new Date(start.getTime())), ""+list.size());
				}else if("people".equals(type)){
					int count = 0;
					for(DataStat ds:list){
						count+=ds.getUseNumber();
					}
					map.put(sdf.format(new Date(start.getTime())), ""+count);
				}
				cal = Calendar.getInstance();
			}
		}else if("week".equals(period)){
			String timeStr = (String) ServletActionContext.getContext().getApplication().get(Constant.SYS_TIME);
			Date date = sdf.parse(timeStr);
			for(int i = 1;i<=20;i++){
				String startStr = CreateStringUtil.getFirstInOneWeek(i, date);
				String endStr = CreateStringUtil.getLastInOneWeek(i, date);
				Timestamp start = new Timestamp(sdf.parse(startStr).getTime());
				Timestamp end = new Timestamp(sdf.parse(endStr).getTime());
				List<DataStat> list = dataStatDao.findByTimeAndRoom(start, end, roomId);
				if("time".equals(type)){
					map.put("第"+i+"周", list.size()+"");
				}else if("people".equals(type)){
					int count = 0;
					for(DataStat ds:list){
						count+=ds.getUseNumber();
					}
					map.put("第"+i+"周", count+"");
				}
			}
		}else if("month".equals(period)){
			for(int i = 1;i<=12;i++){
				String startStr="";
				if(i<10){
					startStr = year+"-0"+i+"-01";
				}else{
					startStr = year+"-"+i+"-01";
				}
				String endStr = "";
				if(i==1||i==3||i==5||i==7||i==8){
					endStr = year+"-0"+i+"-31";
				}else if(i==10||i==12){
					endStr = year+"-"+i+"-31";
				}else if(i==2&&(year%400==0||(year%4==0&&year%100!=0))){
					endStr = year+"-02-29";
				}else if(i==2){
					endStr = year+"-02-28";
				}else if(i<10){
					endStr = year+"-0"+i+"-30";
				}else{
					endStr = year+"-"+i+"-30";
				}
				Timestamp start = new Timestamp(sdf.parse(startStr).getTime());
				Timestamp end = new Timestamp(sdf.parse(endStr).getTime());
				List<DataStat> list = dataStatDao.findByTimeAndRoom(start, end, roomId);
				if("time".equals(type)){
					map.put(i+"月", list.size()+"");
				}else if("people".equals(type)){
					int count = 0;
					for(DataStat ds:list){
						count+=ds.getUseNumber();
					}
					map.put(i+"月", count+"");
				}
			}
		}
			
		return map;
	}
}
