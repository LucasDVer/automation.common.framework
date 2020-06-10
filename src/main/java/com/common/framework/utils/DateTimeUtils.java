package com.common.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public static Date getDateFromString(String date, String format) throws ParseException {
        SimpleDateFormat actualFormat = new SimpleDateFormat(format);
        return actualFormat.parse(date);
    }

    public static boolean isActualDateBeforeASpecifiedDate(Date date) {
        Date newDate = new Date();
        return newDate.before(date);
    }
}
