package com.time;

import java.util.Calendar;

public class TimeUtil {
	
	/**
	 * getTimeNDate()
	 * @return time and date in a string format of hour-minute-second-millisecond-date-month-year
	 * Example: 1564650924102012 for 15:06:46:509 on the 24-10-2012 
	 */
	public static String getTimeNDate() {
		// Get the current time
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);	
		// Extract the time components
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisec = calendar.get(Calendar.MILLISECOND);
		int date = calendar.get(Calendar.DATE); 
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		// Build time as a string
		StringBuilder timestamp = new StringBuilder();
		timestamp.setLength(0);
		timestamp.append(hour);
		timestamp.append(minute);
		timestamp.append(second);
		timestamp.append(millisec);
		timestamp.append(date);
		timestamp.append(month);
		timestamp.append(year);
		// Return the time as a string
		return timestamp.toString();
	}
	
	/**
	 * getTimeNDateLong()
	 * @return time and date as a long format of hour-minute-second-millisecond-date-month-year
	 * Example: 1564650924102012 for 15:06:46:509 on the 24-10-2012 
	 */
	public static long getTimeNDateLong() {
		// Get the current time
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);	
		// Extract the time components
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisec = calendar.get(Calendar.MILLISECOND);
		int date = calendar.get(Calendar.DATE); 
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		// Build time as a string
		StringBuilder timestamp = new StringBuilder();
		timestamp.setLength(0);
		timestamp.append(hour);
		timestamp.append(minute);
		timestamp.append(second);
		timestamp.append(millisec);
		timestamp.append(date);
		timestamp.append(month);
		timestamp.append(year);
		// Return the time as a string
		return Long.valueOf(timestamp.toString());
	}
	
	/**
	 * getTime()
	 * @return time in a string format of hour-minute-second-millisecond
	 * Example: 15646509 for 15:06:46:509
	 */
	public static String getTime() {
		// Get the current time
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);	
		// Extract the time components
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisec = calendar.get(Calendar.MILLISECOND);
		// Build time as a string
		StringBuilder timestamp = new StringBuilder();
		timestamp.setLength(0);
		timestamp.append(hour);
		timestamp.append(minute);
		timestamp.append(second);
		timestamp.append(millisec);
		// Return the time as a string
		return timestamp.toString();
	}
	
	/**
	 * getTimeLong()
	 * @return time as a long format of hour-minute-second-millisecond
	 * Example: 15646509 for 15:06:46:509
	 */
	public static long getTimeLong() {
		// Get the current time
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);	
		// Extract the time components
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int millisec = calendar.get(Calendar.MILLISECOND);
		// Build time as a string
		StringBuilder timestamp = new StringBuilder();
		timestamp.setLength(0);
		timestamp.append(hour);
		timestamp.append(minute);
		timestamp.append(second);
		timestamp.append(millisec);
		// Return the time as a long
		return Long.valueOf(timestamp.toString());
	}
	
	/**
	 * getDate()
	 * @return date in a string format of date-month-year
	 * Example: 24102012 for 24-10-2012 
	 */
	public static String getDate() {
		// Get the current time
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);	
		// Extract the time components
		int date = calendar.get(Calendar.DATE); 
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		// Build time as a string
		StringBuilder timestamp = new StringBuilder();
		timestamp.setLength(0);
		timestamp.append(date);
		timestamp.append(month);
		timestamp.append(year);
		// Return the time as a string
		return timestamp.toString();
	}
	
	/**
	 * getDateLong()
	 * @return date in a long format of date-month-year
	 * Example: 24102012 for 24-10-2012 
	 */
	public static long getDateLong() {
		// Get the current time
		long time = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);	
		// Extract the time components
		int date = calendar.get(Calendar.DATE); 
		int month = calendar.get(Calendar.MONTH)+1;
		int year = calendar.get(Calendar.YEAR);
		// Build time as a string
		StringBuilder timestamp = new StringBuilder();
		timestamp.setLength(0);
		timestamp.append(date);
		timestamp.append(month);
		timestamp.append(year);
		// Return the time as a long
		return Long.valueOf(timestamp.toString());
	}
				
}
