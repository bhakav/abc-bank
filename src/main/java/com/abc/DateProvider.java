package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * SingleTon implementation
 * 
 * @author Bharat
 * 
 */
public class DateProvider {

	private static DateProvider instance = null;

	/**
	 * get instance of date
	 * 
	 * @return
	 */
	public static DateProvider getInstance() {
		if (instance == null) {
			synchronized (DateProvider.class) {
				if (instance == null) {

					instance = new DateProvider();
				}
			}
		}
		return instance;
	}

	/**
	 * Get current date
	 * 
	 * @return
	 */
	public Date now() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Difference between two dates in TimeUnits
	 * 
	 * @param date1
	 * @param date2
	 * @param timeUnit
	 * @return
	 */
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.DAYS);
	}
}