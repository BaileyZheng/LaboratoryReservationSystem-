package edu.just.reservation.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.just.reservation.core.util.CreateStringUtil;


public class TestSmall {

	public static void main(String[] args) throws ParseException {
		  /* // �� calendar ����ʱ��ķ���  
		34.      // ���ô����ʱ���ʽ  
		35.      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");  
		36.      // ָ��һ������  
		37.      Date date = dateFormat.parse("2013-6-1 13:24:16");  
		38.      // �� calendar ����Ϊ date ����������  
		39.      calendar.setTime(date);  
		40.  
		41.      // ���ض���ʽ��ʾ�����õ�ʱ��  
		42.      str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(calendar.getTime());  
		43.      System.out.println(str);  */

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		
		Date date = dateFormat.parse("2017-02-20");
		Calendar begin = Calendar.getInstance();
		begin.setTime(date);
		begin.add(Calendar.DATE, 3*7+1);
		System.out.println(begin.getTime());
//		System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
//		long val = cal.getTimeInMillis() - begin.getTimeInMillis();  
//		long day = val / (1000 * 60 * 60 * 24);  
//		int week = (int) (day/7);
//		int weekday = cal.get(Calendar.DAY_OF_WEEK);
		//���ܵ�һ������3.5
		//cal.add(Calendar.DATE, -(weekday-2));
		//�������һ��3.12
		//cal.add(Calendar.DATE, (8-weekday));
		//3.13
//		cal.add(Calendar.DATE, 5);
//		weekday = cal.get(Calendar.DAY_OF_WEEK);
//		cal.add(Calendar.DATE, (8-weekday));
//		System.out.println(cal.getTime());
		//System.out.println(CreateStringUtil.getWeekStr(date));
//		System.out.println(CreateStringUtil.getFirstInThisWeek());
//		System.out.println(CreateStringUtil.getLastInThisWeek());
	}
}
