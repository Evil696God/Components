

package com.kuke.common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间转换工具类
 *
 * @date: 2018-12-12
 * @version: 1.0
 * @author: wangchenxing
 */
public class TimeUtils {

    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_DATE_TIME_SIMPLE = "yyyy-M-d H:mm";
    public final static String FORMAT_DATE_TIME_YEAR_MON_DAY = "yyyy-MM-dd";
    public final static String FORMAT_DATE_TIME_DAY_MON_YEAR = "dd/MM/yyyy";
    public final static String FORMAT_DATE_TIME_H_M = "HH:mm";
    public final static String FORMAT_SYSDATE_TIME = "yyyy-MM-dd'T'HH:mm:SSS";
    public static final String[] monthArr = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul"
            , "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static SimpleDateFormat HHmm;

    /**
     * @param sysTime 后台数据返回时间
     * @return dd/MM/yyyy
     */
    public static String systemTimeFormatDMY(String sysTime) {
        return systemTimeFormat(sysTime, FORMAT_DATE_TIME_DAY_MON_YEAR);
    }

    /**
     * @param sysTime 后台数据返回时间
     * @return yyyy-MM-dd
     */
    public static String systemTimeFormatYMD(String sysTime) {
        return systemTimeFormat(sysTime, FORMAT_DATE_TIME_YEAR_MON_DAY);
    }

    /**
     * @param sysTime 后台数据返回时间
     * @return HH:mm
     */
    public static String systemTimeFormatHHmm(String sysTime) {
        return systemTimeFormat(sysTime, FORMAT_DATE_TIME_H_M);
    }

    public static String systemTimeFormat(
            String sysTime,
            String format
    ) {
        if (TextUtils.isEmpty(sysTime)) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_SYSDATE_TIME);
        Date date = null;

        try {
            date = formatter.parse(sysTime);
            if (date != null) {
                return new SimpleDateFormat(format).format(date);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sysTime;
    }


    /**
     * String类型转换为Date类型
     *
     * @param stringTime 要转换的String类型的时间
     * @return Date类型的时间
     */
    public static Date stringToDate(String stringTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TIME);
        Date date = null;

        try {
            date = formatter.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date stringToDate(
            String stringTime,
            String type
    ) {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Date date = null;

        try {
            date = formatter.parse(stringTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date类型转换为String类型
     *
     * @param data Date类型的时间
     * @return String
     */
    public static String dateToString(Date data) {
        return new SimpleDateFormat(FORMAT_DATE_TIME).format(data);
    }

    public static String dateToString(
            Date data,
            String type
    ) {
        return new SimpleDateFormat(type).format(data);
    }

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (format == null || "".equals(format.trim())) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * string类型转换为long类型
     *
     * @param strTime
     * @return
     */
    public static long stringToLong(String strTime) {
        // String类型转成date类型
        Date date = stringToDate(strTime);
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            long currentTime = date2Long(date);
            return currentTime;
        }
    }

    public static long stringToLong(
            String strTime,
            String type
    ) {
        Date date = stringToDate(
                strTime,
                type
        );
        // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            // date类型转成long类型
            long currentTime = date2Long(date);
            return currentTime;
        }
    }

    /**
     * long类型转换为String类型
     *
     * @param currentTime 要转换的long类型的时间
     * @return String
     */
    public static String longToString(long currentTime) {
        String strTime = "";
        // long类型转成Date类型
        Date date = longToDate(currentTime);
        // date类型转成String
        strTime = dateToString(date);
        return strTime;
    }

    public static String longToString(
            long currentTime,
            String type
    ) {
        String strTime = "";
        // long类型转成Date类型
        Date date = longToDate(
                currentTime,
                type
        );
        // date类型转成String
        strTime = dateToString(
                date,
                type
        );
        return strTime;
    }

    /**
     * long转换为Date类型
     *
     * @param currentTime
     * @return
     */
    public static Date longToDate(long currentTime) {
        // 根据long类型的毫秒数生命一个date类型的时间
        Date dateOld = new Date(currentTime);
        // 把date类型的时间转换为string
        String sDateTime = dateToString(dateOld);
        // 把String类型转换为Date类型
        Date date = stringToDate(sDateTime);
        return date;
    }

    public static Date longToDate(
            long currentTime,
            String type
    ) {
        // 根据long类型的毫秒数生命一个date类型的时间
        Date dateOld = new Date(currentTime);
        // 把date类型的时间转换为string
        String sDateTime = dateToString(
                dateOld,
                type
        );
        // 把String类型转换为Date类型
        Date date = stringToDate(
                sDateTime,
                type
        );
        return date;
    }

    /**
     * date类型转换为long类型
     *
     * @param date
     * @return
     */
    public static long date2Long(Date date) {
        return date.getTime();
    }

    public static GregorianCalendar get0Day() {
        return new GregorianCalendar(
                1,
                0,
                1
        );
    }

    public static Calendar getCurrentTime() {
        return Calendar.getInstance();
    }

    public static boolean isToday(Calendar calendar) {
        Calendar current = getCurrentTime();
        return current.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                && current.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static String date(Calendar calendar) {
        StringBuilder sb = new StringBuilder(calendar.getDisplayName(
                Calendar.MONTH,
                Calendar.SHORT,
                Locale.getDefault()
        ));
        if (Locale.getDefault().equals(Locale.CHINA)) {
            sb.append(calendar.get(Calendar.DAY_OF_MONTH)).append("日");
        } else {
            sb.append(" ").append(calendar.get(Calendar.DAY_OF_MONTH));
        }
        sb.append(" ").append(calendar.getDisplayName(
                Calendar.DAY_OF_WEEK,
                Calendar.SHORT,
                Locale.getDefault()
        ));
        return sb.toString();
    }

    public static synchronized String time(Calendar calendar) {
        if (HHmm == null) {
            HHmm = new SimpleDateFormat("HH:mm", Locale.getDefault());
        }
        return HHmm.format(calendar.getTime());
    }

    public static int compare(Calendar time1, Calendar time2) {
        long time1Millis = time1.getTimeInMillis();
        long time2Millis = time2.getTimeInMillis();
        if (time1Millis == time2Millis) {
            return 0;
        } else {
            return time1Millis > time2Millis ? 1 : -1;
        }
    }

    public static int daySwitchesBetween(Calendar time1, Calendar time2) {
        long time1Millis = time1.getTimeInMillis();
        long time2Millis = time2.getTimeInMillis();
        int fix = 0;
        if (tomorrowOClock(time1) - time1Millis < tomorrowOClock(time2) - time2Millis) {
            fix = 1;
        }

        return (int) ((time2Millis - time1Millis) / (24 * 60 * 60 * 1000)) + fix;
    }

    private static long todayOClock(Calendar time) {
        Calendar temp = (Calendar) time.clone();
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        temp.set(Calendar.MILLISECOND, 0);
        return temp.getTimeInMillis();
    }

    private static long tomorrowOClock(Calendar time) {
        Calendar temp = (Calendar) time.clone();
        temp.set(Calendar.HOUR_OF_DAY, 0);
        temp.set(Calendar.MINUTE, 0);
        temp.set(Calendar.SECOND, 0);
        temp.set(Calendar.MILLISECOND, 0);
        if (temp.getTimeInMillis() < time.getTimeInMillis()) {
            temp.add(Calendar.DAY_OF_YEAR, 1);
        }
        return temp.getTimeInMillis();
    }

    private static boolean isAtStartDay(Calendar startDate, Calendar selectedDate) {
        return selectedDate.get(Calendar.YEAR) == startDate.get(Calendar.YEAR)
                && selectedDate.get(Calendar.DAY_OF_YEAR) == startDate.get(Calendar.DAY_OF_YEAR);
    }

    public static int calculateStepOffset(Calendar startDate, Calendar selectedDate, int minutesInterval) {
        if (!isAtStartDay(startDate, selectedDate)) {
            return 0;
        }
        int stepOffset = 0;

        int hourValue = startDate.get(Calendar.HOUR_OF_DAY);
        int minuteValue = startDate.get(Calendar.MINUTE);

        stepOffset += (hourValue) * (60 / minutesInterval);
        boolean remain = minuteValue % minutesInterval > 0;
        minuteValue = (minuteValue / minutesInterval + (remain ? 1 : 0)) * minutesInterval;
        stepOffset += minuteValue / minutesInterval;

        return stepOffset;
    }

    public static int calculateStep(Calendar date, int minutesInterval) {
        int hours = date.get(Calendar.HOUR_OF_DAY);
        int minutes = date.get(Calendar.MINUTE);
        return (hours * 60 + minutes) / minutesInterval;
    }

    public static int calculateStep(Calendar startDate, Calendar toDate, int minutesInterval) {
        int hours;
        int minutes;
        if (isAtStartDay(startDate, toDate)) {
            hours = toDate.get(Calendar.HOUR_OF_DAY) - startDate.get(Calendar.HOUR_OF_DAY);
            if (hours == 0) {
                minutes = toDate.get(Calendar.MINUTE) - startDate.get(Calendar.MINUTE);
            } else {
                minutes = toDate.get(Calendar.MINUTE);
            }
        } else {
            hours = toDate.get(Calendar.HOUR_OF_DAY);
            minutes = toDate.get(Calendar.MINUTE);
        }
        return (hours * 60 + minutes) / minutesInterval;
    }

    /**
     * 由24小时变为12小时，并想显示am pm
     */
    public static String get12TimeForAM(long milles) {
        Date date = new Date();
        date.setTime(milles);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        String format = sdf.format(date);
        return format;
    }

    /**
     * 日期转为 日 英文月 年
     */
    public static String getEnglishDate(Date date) {
        LogManagementUtils.showLog("日期:" + date.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int moon = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day + " " + monthArr[moon - 1] + " " + year;
    }

    /**
     * 日/月/年
     */
    public static String getDate(Date date) {
        LogManagementUtils.showLog("日期:" + date.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int moon = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String endDay = day + "";
        String endMoon = moon + "";
        if (day < 10) {
            endDay = "0" + endDay;
        }

        if (moon < 10) {
            endMoon = "0" + endMoon;
        }

        return endDay + "/" + endMoon + "/" + year;
    }
}
