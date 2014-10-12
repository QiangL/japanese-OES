package org.ssdut.japanese.oes.util;

import org.apache.log4j.Logger;

/*
 * 日志记录类
 * */
public class LogRecord {

	private static Logger info = Logger.getLogger("InfoLogger");
	
	private static Logger error = Logger.getLogger("ErrorLogger");
	
	public LogRecord(){}
	
	public static void info(String information){
		info.info(information);
	}
	
	public static void error(String information){	
		error.error(information);
	}
}
