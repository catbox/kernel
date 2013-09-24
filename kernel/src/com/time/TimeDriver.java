package com.time;

public class TimeDriver {

	public static void main(String[] args) {		
		System.out.println("Time and Date: " + TimeUtil.getTimeNDate());
		System.out.println("Time and Date: " + TimeUtil.getTimeNDateLong());
		System.out.println("Time: " + TimeUtil.getTime());
		System.out.println("Time: " + TimeUtil.getTimeLong());
		System.out.println("Date: " + TimeUtil.getDate());
		System.out.println("Date: " + TimeUtil.getDateLong());
		
		for(long i=1; i<=25; i++) {
			long time = TimeUtil.getTimeLong()+i;
			String timeStr = String.valueOf(time);
			String date = TimeUtil.getDate();
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.setLength(0);
			stringBuilder.append(timeStr);
			stringBuilder.append(date);
			
			System.out.println("Time is: " + stringBuilder.toString());
		}

	}
	
}

