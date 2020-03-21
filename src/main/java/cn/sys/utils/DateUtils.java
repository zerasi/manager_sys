package cn.sys.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getNowDate(Date date) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(date);  
	    return dateString;  
	} 
}
