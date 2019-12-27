package com.msc.demo.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {
	
	/**
	 * Create DateTime object with the date specified as argument.
	 * @param date The date in the format yyyy-MM-dd.
	 * @return DateTime object with the provided date.
	 */
	public static DateTime getDateTime(String date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		return formatter.parseDateTime(date);
	}

}
