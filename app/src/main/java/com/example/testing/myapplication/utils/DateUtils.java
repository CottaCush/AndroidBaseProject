package com.example.testing.myapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Adegoke Obasa <goke@cottacush.com>
 */

public class DateUtils {
    public static final String MYSQL_FORMAT = "yyyy-M-dd HH:mm:ss";
    public static final String MYSQL_DATE_ONLY = "yyyy-MM-dd";
    public static final String MONTH_DATE = "MMM dd";
    public static final String FULL_DATE = "dd MMMM yyyy";
    public static final String FULL_DATE_AND_TIME = "dd MMMM yyyy 'at' hh:mma";


    /**
     * @param date
     * @return
     */
    public static String formatDate(String date) {
        SimpleDateFormat sFrom = new SimpleDateFormat(MYSQL_DATE_ONLY, Locale.getDefault());
        SimpleDateFormat sTo = new SimpleDateFormat(FULL_DATE, Locale.getDefault());
        String result = null;
        try {
            result = sTo.format(sFrom.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param Time
     * @return
     */
    public static String getRelativeTime(String Time) {
        String relativeTime = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MYSQL_FORMAT, Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(Time);
            relativeTime = (String) android.text.format.DateUtils.getRelativeTimeSpanString(date.getTime(), System.currentTimeMillis(), android.text.format.DateUtils.DAY_IN_MILLIS, android.text.format.DateUtils.FORMAT_ABBREV_ALL);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return relativeTime;
    }

    /**
     * @param dateOfBirth
     * @param format      e.g yyyy-mm-dd
     * @return
     */
    public static int getAge(String dateOfBirth, String format) {
        Date birthdate;
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            birthdate = df.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(birthdate);
        today.setTime(Calendar.getInstance().getTime());

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
}