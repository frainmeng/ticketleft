/**
 * 
 */
package com.frain.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kalven.meng
 *
 */
public class DateUtils {
	private static final String FORMAT_SECOND = "yyy-MM-dd HH:mm:ss";
	
	public static String getCurrentDateStr () {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_SECOND);
		return format.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentDateStr());
	}
}
