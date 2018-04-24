package edu.just.reservation.tjbb.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.service.impl.BaseServiceImpl;
import edu.just.reservation.core.util.CreateStringUtil;
import edu.just.reservation.tjbb.dao.VisitStatDao;
import edu.just.reservation.tjbb.entity.DataStat;
import edu.just.reservation.tjbb.entity.VisitStat;
import edu.just.reservation.tjbb.service.VisitStatService;

@Service("visitStatService")
public class VisitStatServiceImpl extends BaseServiceImpl<VisitStat> implements
		VisitStatService {
	private VisitStatDao visitStatDao;
	@Resource
	public void setVisitStatDao(VisitStatDao visitStatDao) {
		super.setBaseDao(visitStatDao);
		this.visitStatDao = visitStatDao;
	}
	public Map<String, String> getDataByPeriod(String period) throws ParseException {
		Map<String,String> map = new LinkedHashMap<String,String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		if("week".equals(period)){
			String timeStr = (String) ServletActionContext.getContext().getApplication().get(Constant.SYS_TIME);
			Date date = sdf.parse(timeStr);
			for(int i = 1;i<=20;i++){
				String startStr = CreateStringUtil.getFirstInOneWeek(i, date);
				String endStr = CreateStringUtil.getLastInOneWeek(i, date);
				Timestamp start = new Timestamp(sdf.parse(startStr).getTime());
				Timestamp end = new Timestamp(sdf.parse(endStr).getTime());
				List<VisitStat> list = visitStatDao.findByTime(start, end);
				map.put("µÚ"+i+"ÖÜ", list.size()+"");
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
				List<VisitStat> list = visitStatDao.findByTime(start, end);
				map.put(i+"ÔÂ", list.size()+"");
			}
		}else{
			for(int i = 6;i>=0;i--){
				cal.add(Calendar.DATE, -i);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Timestamp start = new Timestamp(cal.getTimeInMillis());
				cal.add(Calendar.DATE, 1);
				Timestamp end = new Timestamp(cal.getTimeInMillis());
				List<VisitStat> list = visitStatDao.findByTime(start, end);
				map.put(sdf.format(new Date(start.getTime())), ""+list.size());
				cal = Calendar.getInstance();
			}
		}
		return map;
	}
}
