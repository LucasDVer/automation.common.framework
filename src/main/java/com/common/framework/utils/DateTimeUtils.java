package com.common.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    private DateTimeUtils() {
    }

    public static String parseDateToGivenFormat(String date, String actualFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(actualFormat);
        SimpleDateFormat formatToReturn = new SimpleDateFormat(actualFormat);
        Date dateToFormat = format.parse(date);
        return formatToReturn.format(dateToFormat);
    }
}
