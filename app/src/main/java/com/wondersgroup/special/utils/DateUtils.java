package com.wondersgroup.special.utils;


import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chan on 11/24/16.
 */

public class DateUtils {

    /**
     * 分割字符串
     *
     * @param date
     * @return
     */
    public static String[] getStrSplit(String date, String split) {
        return date.split(split);
    }

    /**
     * 返回今天日期
     *
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        String today = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        return today;
    }

    /**
     * 返回前一天日期
     *
     * @return
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        String yesterday = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
        return yesterday;
    }

    /**
     * 返回当前年份的第一天
     *
     * @return
     */
    public static String getFirstDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        String firstDay = calendar.get(Calendar.YEAR) + "-01-01";
        return firstDay;
    }

    /**
     * 获取6年前年份
     *
     * @return
     */
    public static String getSixAgeYear() {
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(Calendar.YEAR) - 6) + "";
    }

    public static String getThisYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "";
    }

    @SuppressLint("SimpleDateFormat")
    public static String StringToDate(String dateSrc, String formatSrc, String formatDes) {
        SimpleDateFormat format = new SimpleDateFormat(formatSrc);
        Date date = null;
        try {
            date = format.parse(dateSrc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatDes);
        return s.format(date);
    }

    public static String formatDate(String dateSrc) {
        String des = "";
        if (!TextUtils.isEmpty(dateSrc)) {
            des = DateUtils.StringToDate(dateSrc,
                    "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
        }
        return des;
    }
}
