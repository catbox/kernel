/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-09-30
 * Project: Opus Dei
 * Package: com.opusdei.common
 * File: DateConverter.java
 * Description: Thread safe SimpleDateFormat to avoid a wrong date format
 *
 */
package com.opusdei.common;

import java.lang.ref.SoftReference;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateConverter {

	private static final ThreadLocal<SoftReference<DateFormat>> threadLocal = new ThreadLocal<SoftReference<DateFormat>>();

	private static DateFormat getDateFormat() {
		SoftReference<DateFormat> ref = threadLocal.get();
		if (ref != null) {
			DateFormat result = ref.get();
			if (result != null) {
				return result;
			}
		}
		DateFormat result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ref = new SoftReference<DateFormat>(result);
		threadLocal.set(ref);
		return result;
	}

	static public String convertToTimeAndTimeFormat(long time) {
		Date date = new Date(time);
		DateFormat df = getDateFormat();
		return df.format(date);
	}
	
	
}
