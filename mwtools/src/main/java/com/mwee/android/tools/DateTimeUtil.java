package com.mwee.android.tools;


import com.mwee.android.tools.DateUtil;
import com.mwee.android.tools.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by qinwei on 2017/1/18.
 */

public class DateTimeUtil {
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    public static long getMillisBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_VISUAL14FORMAT, Locale.SIMPLIFIED_CHINESE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        return time2 - time1;
    }


    /**
     * 通过时间获得long
     * @param time
     * @return
     * @throws ParseException
     */
    public static long getLongByTime(String time,String pattern)  {
        Calendar cal = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
            cal = Calendar.getInstance();
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = cal.getTimeInMillis()/1000;
        return time1;
    }


    public static int getMMBetween(String smdate, String bdate) throws ParseException {
        long millis = getMillisBetween(smdate, bdate);
        long between_mm = millis % 60000L > 0 ? millis / 60000L + 1 : millis / 60000L;
        return StringUtil.toInt(String.valueOf(between_mm));
    }

    public static Date strToDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date strToDate(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateFormat(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(strToDate(dateStr));
    }

    public static String dateFormat(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    public static String getTime(String dateStr) {
        return dateFormat(dateStr, "HH:mm");
    }

    public static String getTime(String dateStr, String pattern) {
        return dateFormat(dateStr, pattern);
    }

    public static String getTime(long time) {
        Date date = new Date(time);
        return dateFormat(date, YYYYMMDDHHMM);
    }

    public static String getTime(long time, String pattern) {
        Date date = new Date(time);
        return dateFormat(date, pattern);
    }


    /**
     * 判断 time 是否在 startTime 和 end Time 之间
     *
     * @return
     */
    public static boolean isBetweenMillis(long startTime, long endTime) {
        long date = DateUtil.getCurrentTimeInMills();
        return date >= startTime && date <= endTime;
    }

    /**
     * 判断 time 是否在 startTime 和 end Time 之间
     *
     * @return
     */
    public static boolean isBetweenMillis(long startTime, long endTime, long date) {
        return date >= startTime && date <= endTime;
    }


    /**
     * 得到给定日期N天后的日期
     *
     * @param num
     * @return
     */
    public static void doMillisAfter(String datestr, int num) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date1 = format.parse(datestr);
            long time = date1.getTime() + (1000L * 60 * 60 * 24 * num);
            Date date = new Date();
            if (time > 0) {
                date.setTime(time);
            }
            System.out.println(format.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 得到给定日期N天前的日期
     *
     * @param num
     * @return
     */
    public static String doMillisBefore(String datestr, int num) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date1 = format.parse(datestr);
            long time = date1.getTime() - (1000L * 60 * 60 * 24 * num);
            Date date = new Date();
            if (time > 0) {
                date.setTime(time);
            }
            System.out.println(format.format(date));
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
