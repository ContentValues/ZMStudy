package com.mwee.android.tools;

import android.text.TextUtils;

import com.mwee.android.tools.timesync.TimeCalibrate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("unused")
public class DateUtil {

    /**
     * 默认日期格式：yyyyMMdd
     */
    public static final String DATE_YYYYMMDD = "yyyyMMdd";
    /**
     * 默认日期格式：yyyyMMddHHmmss
     */
    public static final String DATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DATE_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_VISUAL14FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Locale LOCALE = Locale.SIMPLIFIED_CHINESE;
    private static String last_format_type = "";
    private static SimpleDateFormat last_format;

    public static String getTimeString(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", DateUtil.LOCALE);
            Date date = simpleDateFormat.parse(time);
            long formTime = date.getTime();
            long distance = (System.currentTimeMillis() - formTime) / 1000;
            String timestamp;
            if (distance < 0)
                distance = 0;
            if (distance < 60) {
                // timestamp = distance + "秒前";
                timestamp = "刚刚";
            } else if (distance < 60 * 60) {
                distance = distance / 60;
                timestamp = distance + "分钟前";
            } else if (distance < 60 * 60 * 24) {
                distance = distance / 60 / 60;
                timestamp = distance + "小时前";
            } else if (distance < 60 * 60 * 24 * 7) {
                distance = distance / 60 / 60 / 24;
                timestamp = distance + "天前";
            } else if (distance < 60 * 60 * 24 * 29) {
                distance = distance / 60 / 60 / 24 / 7;
                timestamp = distance + "周前";
            } else if (distance < 60 * 60 * 24 * 29 * 3) {
                distance = distance / 60 / 60 / 24 / 30;
                timestamp = distance + "个月前";
            } else {
                timestamp = time;
            }
            return timestamp;
        } catch (Exception e) {
            LogUtil.logError("time=" + time, e);
        }
        return time;
    }

    // 2014-09-11 11:03:33

    /**
     * 获取当前时间,返回的日期格式为{@link #DATE_VISUAL14FORMAT}
     *
     * @return String
     */
    public static String getCurrentTime() {
        return getCurrentDateTime(DATE_VISUAL14FORMAT);
    }

    /**
     * 获取当前时间的毫秒数
     *
     * @return long
     */
    public static long getCurrentTimeInMills() {
        return TimeCalibrate.getCurrentCalendar().getTime();
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return long
     */
    public static long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, DateUtil.LOCALE);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            LogUtil.logError("", e);
        }
        return 0L;
    }

    public static Date string2Date(final String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", DateUtil.LOCALE).parse(date);
        } catch (Exception e) {
            LogUtil.log("", e);
        }
        return null;
    }

    /**
     * 字符串的日期格式的计算
     *
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", DateUtil.LOCALE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static int timesBetween(String smdate, String bdate)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", DateUtil.LOCALE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 60);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static boolean isCloseEnough(String time1, String time2) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", DateUtil.LOCALE);
            Date date = simpleDateFormat.parse(time1);
            long formTime1 = date.getTime();
            Date date2 = simpleDateFormat.parse(time2);
            long formTime2 = date2.getTime();
            long time = formTime2 - formTime1;
            if (time / 1000 < 2 * 60) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public static String getTimeSuffix() {
        Calendar cal = Calendar.getInstance();
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH) + 1;
        int mDay = cal.get(Calendar.DAY_OF_MONTH);
        String month = "" + mMonth;
        if (mMonth < 10) {
            month = "0" + mMonth;
        }
        String day = "" + mDay;
        if (mDay < 10) {
            day = "0" + mDay;
        }
        // int h = cal.get(Calendar.HOUR_OF_DAY);
        // int m = cal.get(Calendar.MINUTE);
        // int s = cal.get(Calendar.SECOND);
        // String hour = "" + h;
        // if (h < 10) {
        // hour = "0" + h;
        // }
        // String minute = "" + m;
        // if (m < 10) {
        // minute = "0" + m;
        // }
        // String second = "" + s;
        // if (s < 10) {
        // second = "0" + s;
        // }
        return String.valueOf(mYear) + month + day + System.currentTimeMillis();
    }

    public static Date formatDateWithDefault(String time) {
        return formatDate(time, "1970-01-01");
    }
    public static Date formatDate(String time) {
        return formatDate(time, DATE_VISUAL14FORMAT, "1970-01-01");
    }

    public static Date formatDateFormat(String time,String format) {
        return formatDate(time, format, "1970-01-01");
    }

    public static Date formatDate(String time, String defaultTime) {
        return formatDate(time, DATE_VISUAL14FORMAT, defaultTime);
    }

    /**
     * 将String类型的时间转换为Date类型
     * 默认格式化时间为{@link #DATE_VISUAL14FORMAT}
     *
     * @param time        String
     * @param defaultTime String
     * @return Date
     */
    public static Date formatDate(String time, String timeFormat, String defaultTime) {
        Date date = null;
        SimpleDateFormat format;
        format = new SimpleDateFormat(timeFormat, DateUtil.LOCALE);
        try {
            date = format.parse(time);
        } catch (Exception e) {
            format = new SimpleDateFormat("yyyy-MM-dd", DateUtil.LOCALE);
            try {
                date = format.parse(time);
            } catch (ParseException e1) {
                try {
                    date = format.parse(defaultTime);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return date;
    }

    /**
     * 返回当前日期，格式为：yyyyMMDD
     *
     * @return String|当前日期
     */
    public static String getCurrentDate() {
        return getCurrentDate(DATE_YYYYMMDD);
    }

    /**
     * 返回当前日期，格式为传入的format_type
     *
     * @param format_type String
     * @return String|当前日期
     */
    public static synchronized String getCurrentDate(String format_type) {
        Date currentDate = TimeCalibrate.getCurrentCalendar();
        if (!TextUtils.isEmpty(last_format_type)) {
            if (TextUtils.equals(last_format_type, format_type) && last_format != null) {
                return last_format.format(currentDate);
            }
        }
        SimpleDateFormat format = new SimpleDateFormat(format_type, DateUtil.LOCALE);
        last_format_type = format_type;
        last_format = format;
        return format.format(currentDate);
    }

    /**
     * 格式化日期
     *
     * @param dateStr String
     * @param type    String
     * @return Date
     */
    public static Date getData(String dateStr, String type) {
        SimpleDateFormat format = new SimpleDateFormat(type, DateUtil.LOCALE);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            LogUtil.logError("", e);
        }
        return date;
    }

    /**
     * 将日期从一种格式转化为另一种格式
     *
     * @param dateStr String
     * @param originFormat String
     * @param targetFormat String
     * @return String
     */
    public static String formartDateStrToTarget(String dateStr, String originFormat, String targetFormat) {
        if(TextUtils.isEmpty(dateStr)){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(originFormat, DateUtil.LOCALE);
        String date = "";

        try {
            Date dateOb = format.parse(dateStr);
            format = new SimpleDateFormat(targetFormat, DateUtil.LOCALE);
            date = format.format(dateOb);
        } catch (ParseException e) {
            LogUtil.logError("", e);
        }
        return date;
    }

    /**
     * 返回当前的日期时间，格式为：yyyyMMDDHHmmss
     *
     * @return String|当前时间
     */
    public static String getCurrentDateTime() {
        return getCurrentDate(DATE_YYYYMMDDHHMMSS);
    }

    public static String getCurrentDateTime(String format_type) {
        return getCurrentDate(format_type);
    }

    public static String addTime(String originDate, String format, @DateLevel int level, int value) {
        String result = originDate;
        try {
            DateFormat df = new SimpleDateFormat(format, DateUtil.LOCALE);
            Calendar calendar = getLocalCalendar();
            Date date = df.parse(originDate);
            Date birthdayDate = DateUtil.formatDateFormat(originDate, format);
            calendar.setTime(birthdayDate);
            calendar.add(level, value);
            date.setTime(calendar.getTimeInMillis());
            result = df.format(date);
        } catch (ParseException e) {
            LogUtil.logError("", e);
            result = originDate;
        }
        return result;
    }

    /**
     * 计算第二个日期减去第一个日期的得到的天数，日期必须为{@link #DATE_YYYYMMDD}格式
     *
     * @param dateStr1 String|日期1，必须为yyyyMMDD格式
     * @param dateStr2 String|日期2，必须为yyyyMMDD格式
     * @return int|天
     */
    public static int compareTwoDateResultDays(String dateStr1, String dateStr2) {
        return (int) compareDate(dateStr1, dateStr2, DATE_YYYYMMDD) / (24 * 3600);
    }


    /**
     * 第二个日期减去第一个日期得到的秒数，日期必须为{@link #DATE_YYYYMMDDHHMMSS}格式
     *
     * @param dateStr1 String|日期1
     * @param dateStr2 String|日期2
     * @return long|秒
     */
    public static long compareTwoDateTime(String dateStr1, String dateStr2) {
        return compareDate(dateStr1, dateStr2, DATE_YYYYMMDDHHMMSS);
    }

    /**
     * 第二个日期减去第一个日期，得到的差值
     *
     * @param dateStr1    String|日期1
     * @param dateStr2    String|日期2
     * @param format_type String|日期格式
     * @return long|如果传入的时间格式精确到毫秒，则返回毫秒；否则，返回到秒
     */
    public static long compareDate(String dateStr1, String dateStr2, String format_type) {
        return comareDateByLevel(dateStr1, dateStr2, format_type, format_type);
    }

    /**
     * 比较两个日期的相差值，支持比较Level
     *
     * @param dateStr1     String|日期1
     * @param dateStr2     String|日期2
     * @param format_type  String|源日期格式
     * @param level_format String|比较level日期格式
     * @return long|如果传入的时间格式精确到毫秒，则返回毫秒；否则，返回到秒
     */
    public static long comareDateByLevel(String dateStr1, String dateStr2, String format_type, String level_format) {
        long minus = 0;
        if (TextUtils.isEmpty(format_type)) {
            format_type = DATE_YYYYMMDD;
        }
        if (TextUtils.isEmpty(level_format)) {
            level_format = DATE_YYYYMMDD;
        }
        if (!TextUtils.isEmpty(dateStr1) && !TextUtils.isEmpty(dateStr2) && dateStr1.length() == dateStr2.length()) {

            try {
                DateFormat df = new SimpleDateFormat(format_type, DateUtil.LOCALE);
                Date date1 = df.parse(dateStr1);
                Date date2 = df.parse(dateStr2);

                DateFormat df_dest = new SimpleDateFormat(level_format, DateUtil.LOCALE);
                Date date1_dest = df_dest.parse(df_dest.format(date1));
                Date date2_dest = df_dest.parse(df_dest.format(date2));
                minus = (date2_dest.getTime() - date1_dest.getTime());
                if (dateStr1.length() <= 14) {
                    minus = minus / 1000;
                }
            } catch (ParseException e) {
                LogUtil.logError("", e);
            }
        }
        return minus;
    }

    /**
     * 传入的日期是否是合法日期,默认为{@link #DATE_YYYYMMDD}格式
     *
     * @param dateStr String | 日期的值
     * @return boolean | true:合法;false:不合法
     */
    public static boolean isDateRight(String dateStr) {
        return isDateRight(dateStr, DATE_YYYYMMDD);
    }

    /**
     * 传入的日期是否是合法日期
     *
     * @param dateStr     String | 日期的值
     * @param format_type String | 日期的格式
     * @return boolean | true:合法;false:不合法
     */
    public static boolean isDateRight(String dateStr, String format_type) {
        boolean result = false;
        if (!TextUtils.isEmpty(dateStr)) {
            if (TextUtils.isEmpty(format_type)) {
                format_type = DATE_YYYYMMDD;
            }

            DateFormat df = new SimpleDateFormat(format_type, DateUtil.LOCALE);
            try {
                Date date = df.parse(dateStr);
                result = date != null;
            } catch (ParseException e) {
                LogUtil.logError("", e);
                result = false;
            }
        }
        return result;
    }

    /**
     * 当前日期是否为周末，日期必须为{@link #DATE_YYYYMMDD}
     *
     * @param dateStr String|{@link #DATE_YYYYMMDD}格式
     * @return boolean|true:是周末;false:不是周末
     */
    public static boolean isDateWeekend(String dateStr) {
        boolean result = false;
        if (!TextUtils.isEmpty(dateStr) && dateStr.length() == 8) {
            result = isDateWeekend(dateStr, DATE_YYYYMMDD);
        }
        return result;
    }
    /**
     * 当前日期是否为周末
     *
     * @param dateStr     String|
     * @param format_type String|日期格式
     * @return boolean|true:是周末;false:不是周末
     */
    public static boolean isDateWeekend(String dateStr, String format_type) {
        int day=getWeekDays(dateStr,format_type);
        return day == 6 || day == 7;
    }

    /**
     * 当前日期是星期几，如果是星期一，就返回1，星期日就返回7，以此类推。
     * 如果日期格式不合法，则返回-1
     * @param dateStr String
     * @param format_type String
     * @return int | 如果是星期一，就返回1，星期日就返回7，以此类推。
     */
    public static int getWeekDays(String dateStr, String format_type) {
        boolean result = false;
        DateFormat df = new SimpleDateFormat(format_type, Locale.SIMPLIFIED_CHINESE);
        int day = -1;
        try {
            Date date = df.parse(dateStr);
            Calendar calendar = new GregorianCalendar();
            if (date != null) {
                calendar.setTimeInMillis(date.getTime());
                day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                if (day == 0) {
                    day = 7;
                }
            }
        } catch (ParseException e) {
            LogUtil.logError("", e);
            result = false;
        }
        return day;
    }

    public static String formatBirthday(final String birthday) {
        if (!TextUtils.isEmpty(birthday)) {
            Calendar calendar = Calendar.getInstance();
            Integer currentYear = calendar.get(Calendar.YEAR);
            Date birthdayDate = DateUtil.string2Date(birthday);
            calendar.setTime(birthdayDate);
            return String.valueOf(currentYear - calendar.get(Calendar.YEAR));
        }
        return "";
    }

    public static Calendar getLocalCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 判断是否为闰年
     */
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }


}
