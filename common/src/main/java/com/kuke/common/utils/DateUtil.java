package com.kuke.common.utils;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint({"SimpleDateFormat", "WrongConstant"})
public final class DateUtil {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_MONTH = "yyyy-MM";
    public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";
    public static final String DEFAULT_FORMAT_TIME_HH_MM = "HH:mm";
    public static final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> defaultDateMonthFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM");
        }
    };
    public static final ThreadLocal<SimpleDateFormat> defaultTimeFormat = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    private DateUtil() {
        throw new RuntimeException("￣ 3￣");
    }

    public static String getDateTimeFromHhMm(String timeHhMmSs) {
        String[] startTimes = timeHhMmSs.split(":");
        String startTime = "";
        if (startTimes != null && startTimes.length == 3) {
            startTime = startTimes[0] + ":" + startTimes[1];
        } else {
            startTime = timeHhMmSs;
        }

        return startTime;
    }

    public static String getDateTimeFromHhMmSs(String timeHhMm) {
        String[] startTimes = timeHhMm.split(":");
        String startTime = "";
        if (startTimes != null && startTimes.length == 2) {
            startTime = startTimes[0] + ":" + startTimes[1] + ":00";
        } else {
            startTime = timeHhMm;
        }

        return startTime;
    }

    public static String getDateTimeFromMillis(long timeInMillis) {
        return getDateTimeFormat(new Date(timeInMillis));
    }

    public static String getDateFromMillis(long timeInMillis) {
        return getDateFormat(new Date(timeInMillis));
    }

    public static String getDateTimeFormat(Date date) {
        return dateSimpleFormat(date, (SimpleDateFormat) defaultDateTimeFormat.get());
    }

    public static String getDateFormat(int year, int month, int day) {
        return getDateFormat(getDate(year, month, day));
    }

    public static String getDateFormat(Date date) {
        return dateSimpleFormat(date, (SimpleDateFormat) defaultDateFormat.get());
    }

    public static String getDateMonthFormat(Date date) {
        return dateSimpleFormat(date, (SimpleDateFormat) defaultDateMonthFormat.get());
    }

    public static String getTimeFormat(Date date) {
        return dateSimpleFormat(date, (SimpleDateFormat) defaultTimeFormat.get());
    }

    public static String dateFormat(String sdate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        java.sql.Date date = java.sql.Date.valueOf(sdate);
        return dateSimpleFormat(date, formatter);
    }

    public static String dateFormat(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return dateSimpleFormat(date, formatter);
    }

    public static String dateSimpleFormat(Date date, SimpleDateFormat format) {
        if (format == null) {
            format = (SimpleDateFormat) defaultDateTimeFormat.get();
        }

        return date == null ? "" : format.format(date);
    }

    public static Date getDateByDateTimeFormat(String strDate) {
        return getDateByFormat(strDate, (SimpleDateFormat) defaultDateTimeFormat.get());
    }

    public static Date getDateByDateFormat(String strDate) {
        return getDateByFormat(strDate, (SimpleDateFormat) defaultDateFormat.get());
    }

    public static Date getDateByFormat(String strDate, String format) {
        return getDateByFormat(strDate, new SimpleDateFormat(format));
    }

    private static Date getDateByFormat(String strDate, SimpleDateFormat format) {
        if (format == null) {
            format = (SimpleDateFormat) defaultDateTimeFormat.get();
        }

        try {
            return format.parse(strDate);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static Date getDate(int year, int month, int day) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(year, month - 1, day);
        return mCalendar.getTime();
    }

    public static long getIntervalDays(String strat, String end) {
        return (java.sql.Date.valueOf(end).getTime() - java.sql.Date.valueOf(strat).getTime()) / 86400000L;
    }

    public static int getCurrentYear() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(1);
    }

    public static String getCurrentMonth() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.MONTH, 0);
        return getDateMonthFormat(mCalendar.getTime());
    }

    public static String getOtherMonth(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.MONTH, diff);
        return getDateMonthFormat(mCalendar.getTime());
    }

    /**
     * 获取 几个月前的 当月一号 的时间戳
     *
     * @param diff 几个月前
     */
    public static long getDataCleaTime(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.MONTH, -diff);
        String time = getDateMonthFormat(mCalendar.getTime());
        String formatTime = time + "-01 00:00:00";
        return getTimeMillisFromString(formatTime, DEFAULT_DATE_TIME_FORMAT);
    }


    public static int getDayOfMonth() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.get(5);
    }

    public static String getToday() {
        Calendar mCalendar = Calendar.getInstance();
        return getDateFormat(mCalendar.getTime());
    }

    public static String getYesterday() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(5, -1);
        return getDateFormat(mCalendar.getTime());
    }

    public static String getBeforeYesterday() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(5, -2);
        return getDateFormat(mCalendar.getTime());
    }

    public static String getOtherDay(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(5, diff);
        return getDateFormat(mCalendar.getTime());
    }

    public static String getCalcDateFormat(String sDate, int amount) {
        Date date = getCalcDate(getDateByDateFormat(sDate), amount);
        return getDateFormat(date);
    }

    public static Date getCalcDate(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, amount);
        return cal.getTime();
    }

    public static Date getCalcTime(Date date, int hOffset, int mOffset, int sOffset) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(11, hOffset);
        cal.add(12, mOffset);
        cal.add(13, sOffset);
        return cal.getTime();
    }

    public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }

    public static int[] getYearMonthAndDayFromString(String sDate) {
        return getYearMonthAndDayFromDate(getDateByDateFormat(sDate));
    }

    public static int[] getYearMonthAndDayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] arr = new int[]{calendar.get(1), calendar.get(2), calendar.get(5)};
        return arr;
    }

    public static long getTimeMillisFromString(String date, String format) {
        Date tempDate = getDateByFormat(date, format);
        if (tempDate == null) {
            return 0;
        } else {
            return getDateByFormat(date, format).getTime();
        }
    }

    public static long getTimeMillisFromDay(long day) {
        return day * 24L * 60L * 60L * 1000L;
    }

    /**
     * 获取 数据库查询比较 less 当前日期
     * + 1 天
     * DateUtil.dateFormat(curDate, DateUtil.DEFAULT_FORMAT_DATE)
     */
    public static String getLessNowDate(long curDate) {
        long lessMillis = curDate + (24 * 60 * 60 * 1000);
        return dateFormat(new Date(lessMillis), DEFAULT_FORMAT_DATE);
    }

    /**
     * 获取 数据库查询比较 greater 当前日期
     * - 1 天
     */
    public static String getGreaterNowDate(long curDate) {
        long lessMillis = curDate - (24 * 60 * 60 * 1000);
        return dateFormat(new Date(lessMillis), DEFAULT_FORMAT_DATE);
    }

    /**
     * 获取 数据库查询比较 less 当前时间
     * + 1 分钟
     * DateUtil.dateFormat(curDate, DateUtil.DEFAULT_FORMAT_TIME)
     *
     * @param curDate
     */
    public static String getLessNowTime(long curDate) {
        long lessMillis = curDate + (60 * 1000);
        return dateFormat(new Date(lessMillis), DEFAULT_FORMAT_TIME);
    }

    /**
     * 获取 数据库查询比较 greater 当前时间
     * - 1 分钟
     */
    public static String getGreaterNowTime(long curDate) {
        long lessMillis = curDate - (60 * 1000);
        return dateFormat(new Date(lessMillis), DEFAULT_FORMAT_TIME);
    }
}
