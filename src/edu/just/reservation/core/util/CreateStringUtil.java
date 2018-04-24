package edu.just.reservation.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CreateStringUtil {
	
	public static List<String> createTermStrs(){
		List<String> termList = new LinkedList<String>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		for(int i = year-3;i<year+10;i++){
			termList.add(i+"-"+(i+1)+"-"+1);
			termList.add(i+"-"+(i+1)+"-"+2);
		}
		return termList;
	}
	
	//��ȡ��ǰѧ���ַ���������"2016-2017"
	public static String getStudyYearStr(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		if(month<8){
			return (year-1)+"-"+year;
		}else{
			return year+"-"+(year+1);
		}
	}
	
	//��ȡ��ǰѧ������ĳһѧ��ĵ�һѧ�ڻ��ǵڶ�ѧ��
	public static String getTermInYear(){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		if(month<8){
			return "2";
		}
		return "1";
	}
	
	//��ȡ��ǰ���ڴ���ĳһѧ�ڵĵڼ���,���ݴ����ѧ�ڿ�ʼʱ�����,date�ĸ�ʽyyyy-MM-dd
	public static String getWeekStr(Date date){
		Calendar now = Calendar.getInstance();
		Calendar begin = Calendar.getInstance();
		begin.setTime(date);
		long val = now.getTimeInMillis() - begin.getTimeInMillis();  
		long day = val / (1000 * 60 * 60 * 24);  
		int week = (int) (day/7)+1;
		return ""+week;
	}
	
	//��ȡ���ܵ�һ��
	public static String getFirstInThisWeek(){
		Calendar now = Calendar.getInstance();
		int weekday = now.get(Calendar.DAY_OF_WEEK);
		if(weekday==1){
			now.add(Calendar.DATE, -7);
		}
		now.add(Calendar.DATE, -(weekday-2));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		return dateFormat.format(now.getTime());
	}
	//��ȡ�������һ��
	public static String getLastInThisWeek(){
		Calendar now = Calendar.getInstance();
		int weekday = now.get(Calendar.DAY_OF_WEEK);
		if(weekday==1){
			now.add(Calendar.DATE, -7);
		}
		now.add(Calendar.DATE, (8-weekday));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		return dateFormat.format(now.getTime());
	}
	
	//����ѧ�ڿ�ʼʱ���ȡָ���ܵĵ�һ��
	public static String getFirstInOneWeek(int week,Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, (week-1)*7);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		return dateFormat.format(cal.getTime());
	}
	
	//����ѧ�ڿ�ʼʱ���ȡָ���ܵ����һ��
	public static String getLastInOneWeek(int week,Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, (week*7-1));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		return dateFormat.format(cal.getTime());
	}
}
