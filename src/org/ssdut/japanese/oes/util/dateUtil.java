package org.ssdut.japanese.oes.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtil {

	public static final String format = "yyyy-MM-dd-HH-mm";
	
	public static DateFormat df = new SimpleDateFormat(format);
	
	public static String getCurrentTime(){
		return df.format(new Date());
	}
	
	public static Date toDateFromString(String date) throws ParseException{
		return df.parse(date);
	}
}
