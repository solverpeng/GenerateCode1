package com.solverpeng.common.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author solverpeng
 */
public abstract class DateUtil extends org.apache.commons.lang.time.DateUtils {

    /**
     * 时间转成字符串 默认转换格式yyyy-MM-dd
     *
     * @param date 需要转换的时间对象
     * @return 转换后的时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, ConstUtil.DATE_PATTERN_YYYY_MM_DD);
    }

    /**
     * 根据指定格式，时间转成字符串
     *
     * @param date    需要转换的时间对象
     * @param pattern 格式化格式
     * @return 转换后的时间字符串
     */
    public static String date2String(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 字符串转成时间 默认转换格式yyyy-MM-dd
     *
     * @param dateString 需要转换的时间字符串
     * @return 转换后的时间
     */
    public static Date string2Date(String dateString) {
        return string2Date(dateString, ConstUtil.DATE_PATTERN_YYYY_MM_DD);
    }

    /**
     * 根据指定格式，字符串转成时间
     *
     * @param dateString 时间字符串
     * @param pattern    格式化格式
     * @return 转换后的时间对象
     */
    public static Date string2Date(String dateString, String pattern) {
        if (dateString == null || pattern == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate(ConstUtil.DATE_PATTERN_YYYY_MM_DD);
    }

    /**
     * 获取当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 获取日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, ConstUtil.DATE_PATTERN_YYYY_MM_DD);
        }
        return formatDate;
    }

    /**
     * 获取当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), ConstUtil.DATE_PATTERN_HH_MM_SS);
    }

    /**
     * 获取当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), ConstUtil.DATE_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), ConstUtil.DATE_PATTERN_YEAR);
    }

    /**
     * 获取当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), ConstUtil.DATE_PATTERN_MONTH);
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), ConstUtil.DATE_PATTERN_DAY);
    }

    /**
     * 获取当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), ConstUtil.DATE_PATTERN_WEEK);
    }

    /**
     * 获取传入时间和现在间隔的天数
     *
     * @param date 传入的时间对象
     * @return 间隔天数
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param earlyDate 较早的时间
     * @param laterDate 较后的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static long daysBetween(Date earlyDate, Date laterDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(ConstUtil.DATE_PATTERN_YYYY_MM_DD);
        earlyDate = sdf.parse(sdf.format(earlyDate));
        laterDate = sdf.parse(sdf.format(laterDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(earlyDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(laterDate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
        return betweenDays;
    }

    /**
     * 比较日期
     *
     * @param dateString1 时间字符串
     * @param dateString2 时间字符串2
     * @param pattern     时间格式
     * @return 1 时间1 > 时间2；0 时间1 = 时间2；-1 时间1 < 时间2；9 未知错误
     * @throws ParseException
     */

    public static int compareDate(String dateString1, String dateString2, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date dt1 = dateFormat.parse(dateString1);
            Date dt2 = dateFormat.parse(dateString2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 9;
    }

    /**
     * 取日期数组中的较小日期，如果有较小的日期相等则返回任何一个
     *
     * @param dateStringArr 日期数组，格式为 yyyy-MM-dd
     * @return 较小日期
     */
    public static String getMinDate(String[] dateStringArr) {
        String minDate = "2099-12-31";//默认最小日期
        if (null == dateStringArr || dateStringArr.length == 0) {
            return null;
        }
        for (String date : dateStringArr) {
            int index = compareDate(minDate, date, ConstUtil.DATE_PATTERN_YYYY_MM_DD);
            if (index == 1) {
                minDate = date;
            }
        }
        return minDate;
    }

    /**
     * 取日期数组中的最大日期，如果有两个较大的日期相等则返回任何一个
     *
     * @param dateStringArr 日期数组，格式为 yyyy-MM-dd
     * @return 最大日期
     */
    public static String getMaxDate(String[] dateStringArr) {
        String maxDate = "1970-01-01";//默认最大日期
        if (null == dateStringArr || dateStringArr.length == 0) {
            return null;
        }
        for (String date : dateStringArr) {
            int index = compareDate(maxDate, date, ConstUtil.DATE_PATTERN_YYYY_MM_DD);
            if (index == -1) {
                maxDate = date;
            }
        }
        return maxDate;
    }

    public static void main(String[] args) throws ParseException {
        String dateString = date2String(new Date());
        System.out.println(dateString);

        String dateString2 = date2String(new Date(), ConstUtil.DATE_PATTERN_YYYY_MM_DD_HH_MM_SS);
        System.out.println(dateString2);

        String date = getDate();
        System.out.println(date);

        String day = getDay();
        System.out.println(day);

        String month = getMonth();
        System.out.println(month);

        String time = getTime();
        System.out.println(time);

        String week = getWeek();
        System.out.println(week);

        int date1 = compareDate("2016-12-22", "2017-12-03", ConstUtil.DATE_PATTERN_YYYY_MM_DD);
        System.out.println(date1);

        Date date2 = string2Date("2017-01-23");
        long days = pastDays(date2);
        System.out.println(days);

        Date date3 = string2Date("2017-03-12");

        long daysBetween = daysBetween(date2, date3);
        System.out.println(daysBetween);

        long time1 = date3.getTime();
        System.out.println(time1);
    }

}
