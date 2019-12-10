package cn.com.jsj.lib_base.tools;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import cn.com.jsj.lib_base.R;


/**
 * 日期格式化工具类
 * <p>
 * SaDateUtils.java
 *
 * @Date 2015年6月25日下午11:37:36
 * @Author Donghongyu 1358506549@qq.com
 * @Version v1.0.0
 */
@SuppressLint("SimpleDateFormat")
public class SaDateUtils {

    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String dateFormatMDHMS = "MM-dd HH:mm:ss";

    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMSNORMAL = "yyyyMMddHHmmss";

    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD = "yyyy-MM-dd";
    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateYMD = "yyyyMMdd";

    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD_BIAS = "yyyy/MM/dd";
    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMDHM_BIAS = "yyyy/MM/dd HH:mm";
    public static final String dateFormatYMDHMS_BIAS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatMD_BIAS = "MM/dd";
    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatYM = "yyyy-MM";
    /**
     * 时间日期格式化到月.
     */
    public static final String dateFormatM = "MM";
    /**
     * 时间日期格式化到月.
     */
    public static final String dateFormatY = "yyyy";
    /**
     * 时间日期格式化到日.
     */
    public static final String dateFormatD = "dd";

    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";


    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatMDHM = "MM-dd HH:mm";

    /**
     * 时间日期格式化到月日.
     */
    public static final String dateFormatMD = "MM-dd";

    /**
     * 时分秒.
     */
    public static final String dateFormatHMS = "HH:mm:ss";

    /**
     * 时分.
     */
    public static final String dateFormatHM = "HH:mm";
    public static final String dateFormatMS = "mm:ss";

    /**
     * 上午.
     */
    public static final String AM = "AM";

    /**
     * 下午.
     */
    public static final String PM = "PM";

    public static final String dateFormatMMDD = "MM月dd日";

    public static final String dateFormatYYMMDD = "yyyy年MM月dd日";

    public static final String dateFormatYYMMDDHHMM = "yyyy年MM月dd日 HH:mm";

    public static final String dateFormatYYMM = "yyyy年MM月";
    public static final String DATE_FORMAT_HHMM = "HHmm";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final String dateFormatMMDDMMSS = "MM月dd日 HH:mm";
    public static final String TOADY = "今天";
    public static final String TOMORROW = "明天";
    public static final String THE_AFTER_TOMORROW = "后天";
    public static final String dateFormatYMD_NORAML = "yyyy.MM.dd";
    public static final String dateFormatYMD_NORAML_SIMPLE = "yyyy.M.d";
    public static final String MM_DD_HH_MM = "MM.dd HH:mm";
    public static final String HH = "HH";
    public static final String MM = "mm";

    /**
     * 获取当前时间戳
     *
     * @return 时间戳
     */
    public static long getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
//        Logger.d("获取当前时间戳-->" + timeInMillis);
        return timeInMillis;
    }

    /**
     * 获取时间戳
     *
     * @return 时间戳
     */
    public static long getTimeByDate(String date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(SaDateUtils.getDateByFormat(date, format));
        long timeInMillis = calendar.getTimeInMillis();
//        Logger.d("获取当前时间戳-->" + timeInMillis);
        return timeInMillis / 1000;
    }


    /**
     * 格式化12小时制<br>
     * 格式：yyyy-MM-dd hh-MM-ss
     *
     * @param time 时间
     * @return 格式化后的时间文本
     */
    public static String format12Time(long time) {
        String format12Time = format(time, "yyyy-MM-dd hh:MM:ss");
//        Logger.d("格式化12小时制-yyyy-MM-dd hh-MM-ss->" + format12Time);
        return format12Time;
    }

    /**
     * 格式化24小时制<br>
     * 格式：yyyy-MM-dd HH-MM-ss
     *
     * @param time 时间
     * @return
     */
    public static String format24Time(long time) {
        String format24Time = format(time, "yyyy-MM-dd HH:MM:ss");
//        Logger.d("格式化24小时制-yyyy-MM-dd hh-MM-ss->" + format24Time);
        return format24Time;
    }

    /**
     * 格式化时间,自定义标签
     *
     * @param time    时间
     * @param pattern 格式化时间用的标签
     * @return
     */
    public static String format(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String formatDate = sdf.format(new Date(time));
//        Logger.d("格式化时间,自定义标签-->" + formatDate);
        return formatDate;
    }

    /**
     * 获取当前天
     *
     * @return 天
     */
    @SuppressWarnings("static-access")
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
//        Logger.d("获取当前天-单位为天->" + currentDay);
        return currentDay;
    }


    /**
     * 获取当前时间戳
     *
     * @return 天
     */
    public static long getCurrentDateLong() {
        Date date = new Date();
        return date.getTime();
    }


    /**
     * 获取当前月
     *
     * @return 月
     */
    @SuppressWarnings("static-access")
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
//        Logger.d("获取当前天-单位为月->" + currentMonth);
        return currentMonth + 1;
    }

    /**
     * 获取当前年
     *
     * @return 年
     */
    @SuppressWarnings("static-access")
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
//        Logger.d("获取当前天-单位为年->" + currentYear);
        return currentYear;
    }

    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    @SuppressLint("SimpleDateFormat")
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
//            Logger.d("String类型的日期时间转化为Date类型->" + date);
        } catch (ParseException e) {
            e.printStackTrace();
//            Logger.e("String类型的日期时间转化为Date类型异常-->" + e.getMessage());
        }
        return date;
    }

    /**
     * 描述：获取偏移之后的Date.
     *
     * @param date          日期时间
     * @param calendarField Calendar属性，对应offset的值，
     *                      如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return Date 偏移之后的日期时间
     */
    public static Date getDateByOffset(Date date, int calendarField, int offset) {
        Calendar c = new GregorianCalendar();
        try {
            c.setTime(date);
            c.add(calendarField, offset);
//            Logger.d("获取偏移之后的Date->" + c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取偏移之后的Date异常-->" + e.getMessage());
        }
        return c.getTime();
    }

    /**
     * 描述：获取指定日期时间的字符串(可偏移).
     *
     * @param strDate       String形式的日期时间
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss" 要返回的格式
     * @param calendarField Calendar属性，对应offset的值，要偏移的字段,就是年,月,日,时,分,秒
     *                      如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format,
                                           int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
//            Logger.d("获取指定日期时间的字符串(可偏移)-->" + mDateTime);
            return mDateTime;

        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取指定日期时间的字符串(可偏移)-->" + e.getMessage());
        }
        return format;
    }

    /**
     * 描述：Date类型转化为String类型(可偏移).
     *
     * @param date          the date
     * @param format        the format
     * @param calendarField the calendar field
     * @param offset        the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format,
                                           int calendarField, int offset) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
//            Logger.d("Date类型转化为String类型(可偏移)-->" + strDate);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("Date类型转化为String类型(可偏移)异常-->" + e.getMessage());
        }
        return strDate;
    }

    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format 想要的日期格式
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
//            Logger.d("Date类型转化为String类型.-->" + strDate);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("Date类型转化为String类型.异常-->" + e.getMessage());
        }
        return strDate;
    }

    /**
     * @param format 时间格式
     * @return 当前时间的字符串
     * @description 获取当前的时间, 并根据传入的数据格式转化
     */
    public static String getStringByFormat(String format) {
        String nowDate = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            Date date = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            nowDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取指定日期时间的字符串,用于导出想要的格式.异常-->" + e.getMessage());
        }
        return nowDate;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
                    dateFormatYMDHMS);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
//            Logger.d("获取指定日期时间的字符串,用于导出想要的格式-->" + mDateTime);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取指定日期时间的字符串,用于导出想要的格式.异常-->" + e.getMessage());
        }
        return mDateTime;
    }

    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @param format       格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
//            Logger.d("获取milliseconds表示的日期时间的字符串-->" + thisDateTime);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取milliseconds表示的日期时间的字符串.异常-->" + e.getMessage());
        }
        return thisDateTime;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate   String形式的日期时间
     * @param strFormat 第一个日期的格式
     * @param format    输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String strFormat, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
                    strFormat);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }


    /**
     * 描述：获取表示当前日期时间的字符串.
     *
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
//        Logger.d("getCurrentDate:" + format);
        String curDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            curDateTime = mSimpleDateFormat.format(c.getTime());
//            Logger.d("获取表示当前日期时间的字符串-->" + curDateTime);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取表示当前日期时间的字符串..异常-->" + e.getMessage());
        }
        return curDateTime;

    }

    /**
     * 描述：获取表示当前日期时间的字符串(可偏移).
     *
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值，
     *                      如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getCurrentDateByOffset(String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            Calendar c = new GregorianCalendar();
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
//            Logger.d("获取表示当前日期时间的字符串(可偏移).-->" + mDateTime);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取表示当前日期时间的字符串(可偏移)..异常-->" + e.getMessage());
        }
        return mDateTime;

    }

    /**
     * 描述：计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getOffectDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        // 先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
//        Logger.d("计算两个日期所差的天数-->" + day);
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1 - h2 + day * 24;
//        Logger.d("计算两个日期所差的小时数-->" + h);
        return h;
    }

    public static int getOffectHour(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1.getTime());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2.getTime());
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1.getTime(), date2.getTime());
        h = h1 - h2 + day * 24;
//        Logger.d("计算两个日期所差的小时数-->" + h);
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1 - m2 + h * 60;
//        Logger.d("计算两个日期所差的分钟数.-->" + m);
        return m;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     * 日期为string
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的毫秒数
     */
    public static int getDateMinutes(String date1, String date2, String format) {
        //默认时间格式
        if (format == null) {
            format = dateFormatYMDHMS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date dtDate1 = sdf.parse(date1);
            Date dtDate2 = sdf.parse(date2);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTimeInMillis(dtDate1.getTime());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(dtDate2.getTime());
            int h1 = calendar1.get(Calendar.MINUTE);
            int h2 = calendar2.get(Calendar.MINUTE);
            int m = getOffectMinutes(dtDate1.getTime(), dtDate2.getTime());
            m = h1 - h2 + m * 60;
            return m;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /***
     * yyyy-MM-dd HH:mm:ss
     */
    private static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    /***
     * 两个日期相差多少秒
     *
     * @param dateStr1 :yyyy-MM-dd HH:mm:ss
     * @param dateStr2 :yyyy-MM-dd HH:mm:ss
     */
    public static int getTimeDelta(String dateStr1, String dateStr2) {
        Date date1 = parseDateByPattern(dateStr1, yyyyMMddHHmmss);
        Date date2 = parseDateByPattern(dateStr2, yyyyMMddHHmmss);
        return getTimeDelta(date1, date2);
    }

    public static Date parseDateByPattern(String dateStr, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 两个日期相差多少秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getTimeDelta(Date date1, Date date2) {
        long timeDelta = (date1.getTime() - date2.getTime()) / 1000;//单位是秒
        int secondsDelta = timeDelta > 0 ? (int) timeDelta : (int) Math.abs(timeDelta);
        return secondsDelta;
    }

    /**
     * 传入时间跟当前的时间,返回格式化后的时间附带星期，（昨天，明天，后天，其他的具体的星期）
     *
     * @param strTime 要转化的时间
     * @return 昨天，明天，后天
     */
    public static String getNowDateWeekStr(String strTime, String strFormat) {
        //默认的时间，传过来什么格式就是什么格式
        String formatLater;
        Date formatLaterDate = SaDateUtils.getDateByFormat(strTime, strFormat);
        //当前时间
        long nowDateTime = SaDateUtils.getDateByFormat(SaDateUtils.getCurrentDate(strFormat), strFormat).getTime();
        //要比较的时间
        long nowSelectDateTime = formatLaterDate.getTime();
        if (SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime) == 0) {
            formatLater = TOADY;
        } else if (SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) >= 1 &&
                SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) < 2) {
            formatLater = TOMORROW;
        } else if (SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) >= 2 &&
                SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) < 3) {
            formatLater = THE_AFTER_TOMORROW;
        } else {
            //时间的星期
            formatLater = SaDateUtils.getWeekNumber(strTime, strFormat);
        }
        return formatLater;
    }

    //只有今明两天
    public static String getNowDateWeekStrTodayAndTomorrow(String strTime, String strFormat) {
        //默认的时间，传过来什么格式就是什么格式
        String formatLater;
        Date formatLaterDate = SaDateUtils.getDateByFormat(strTime, strFormat);
        //当前时间
        long nowDateTime = SaDateUtils.getDateByFormat(SaDateUtils.getCurrentDate(strFormat), strFormat).getTime();
        //要比较的时间
        long nowSelectDateTime = formatLaterDate.getTime();
        if (SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime) == 0) {
            formatLater = TOADY;
        } else if (SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) >= 1 &&
                SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) < 2) {
            formatLater = TOMORROW;
        } else {
            //时间的星期
            formatLater = SaDateUtils.getWeekNumber(strTime, strFormat);
        }
        return formatLater;
    }

    //只有今明两天
    public static String getNowDateWeekStrTodayAndTomorrow(Date targetDate) {
        //默认的时间，传过来什么格式就是什么格式
        String formatLater = "";
        //当前时间
        long nowDateTime = SaDateUtils.getDateByFormat(SaDateUtils.getCurrentDate(dateYMD), dateYMD).getTime();
        //要比较的时间
        long nowSelectDateTime = targetDate.getTime();
        if (SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime) == 0) {
            formatLater = TOADY;
        } else if (SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) >= 1 &&
                SaDateUtils.getOffectDay(nowSelectDateTime, nowDateTime) < 2) {
            formatLater = TOMORROW;
        }
        return formatLater;
    }

    /**
     * 传入时间跟当前的时间,返回格式化后的时间附带星期，（昨天，明天，后天，其他的具体的星期）
     *
     * @param strTime 要转化的时间
     * @return 昨天，明天，后天
     */
    public static String getNowDateWeekStrHaveFormat(String strTime, String strFormat) {
        //默认的时间，传过来什么格式就是什么格式
        String formatLater;
        Date formatLaterDate = SaDateUtils.getDateByFormat(strTime, strFormat);
        //当前时间
        Date nowDate = SaDateUtils.getDateByFormat(SaDateUtils.getCurrentDate(strFormat), strFormat);
        long nowDateTime = nowDate.getTime();
        //要比较的时间
        long nowSelectDateTime = formatLaterDate.getTime();
        if (Math.abs(SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime)) == 0 && nowDate.equals(formatLaterDate)) {
            formatLater = TOADY;
        } else if (Math.abs(SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime)) >= 1 &&
                Math.abs(SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime)) < 2 && formatLaterDate.after(nowDate)) {
            formatLater = TOMORROW;
        } else if (Math.abs(SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime)) >= 2 &&
                Math.abs(SaDateUtils.getOffectDay(nowDateTime, nowSelectDateTime)) < 3 && formatLaterDate.after(nowDate)) {
            formatLater = THE_AFTER_TOMORROW;
        } else {
            //时间的星期
            formatLater = SaDateUtils.getWeekNumber(strTime, strFormat);
        }
        return "(" + formatLater + ")";
    }

    /**
     * 计算两个日期间相差天、时、分
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param format    时间格式
     * @return
     */
    public static String getOffectDiff(String startTime, String endTime, String format) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long diff;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;//计算差多少天
            long hour = diff % nd / nh;//计算差多少小时
            long min = diff % nd % nh / nm;//计算差多少分钟

            StringBuffer strDiff = new StringBuffer("");

            //对时间差进行拼接
            //如果天数不为0那么就进行追加
            if (day != 0) {
                strDiff.append(day + "天");
                //判断分钟是否为0
                if (min != 0) {
                    //如果不为0,判断小时是否为0，不为0则追加
                    if (hour != 0) {
                        strDiff.append(hour + "小时");
                    }
                }
            } else {
                //如果天数为0
                if (hour != 0) {
                    //那么就判断小时是否为0，如果不为0那么就追加
                    strDiff.append(hour + "小时");
                }
            }
            if (min != 0) {
                //如果小时为0，那么判断分钟数是否为0
                strDiff.append(min + "分钟");
            }

            //输出结果
            return strDiff.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "错误";
    }

    public static String getOffectDiff(String startTime, String endTime, String format, boolean simple) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long diff;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff / nd;//计算差多少天
            long hour = diff % nd / nh;//计算差多少小时
            long min = diff % nd % nh / nm;//计算差多少分钟

            StringBuffer strDiff = new StringBuffer("");

            //对时间差进行拼接
            //如果天数不为0那么就进行追加
            if (day != 0) {
                strDiff.append(day + "天");
                //判断分钟是否为0
                if (min != 0) {
                    //如果不为0,判断小时是否为0，不为0则追加
                    if (hour != 0) {
                        strDiff.append(hour + "小时");
                    }
                }
            } else {
                //如果天数为0
                if (hour != 0) {
                    //那么就判断小时是否为0，如果不为0那么就追加
                    strDiff.append(hour + "小时");
                }
            }
            if (min != 0) {
                //如果小时为0，那么判断分钟数是否为0
                strDiff.append(min + "分");
            }

            if (diff <= 0) {
                //输出结果
                return "";
            } else {
                //输出结果
                return strDiff.toString();
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 计算两个日期间相差时、分
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param format    时间格式
     * @return
     */
    public static String getOffectHM(String startTime, String endTime, String format) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long diff;
        try {
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long hour = diff / nh;//计算差多少小时
            long min = diff % nh / nm;//计算差多少分钟

            StringBuffer strDiff = new StringBuffer("");

            //对时间差进行拼接
            //如果天数不为0那么就进行追加
            //如果天数为0
            if (hour != 0) {
                //那么就判断小时是否为0，如果不为0那么就追加
                strDiff.append(hour + "h");
            }
            if (min != 0) {
                //如果小时为0，那么判断分钟数是否为0
                strDiff.append(min + "m");
            }
            //输出结果
            return strDiff.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "错误";
    }

    /***
     * 比较两个String日期大小
     *
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compareDate(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /***
     * 比较两个String日期大小
     *
     * @param DATE1
     * @param DATE2
     * @param format  传入的格式
     * @return 1: date1 > date2  -1 :date1<date2 0:相等
     */
    public static int compareDate(String DATE1, String DATE2, String format) {


        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                //System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                //System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 描述：获取本周一.日期
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        String firstDayOfWeek = getDayOfWeek(format, Calendar.MONDAY);
//        Logger.d("获取本周一-日期->" + firstDayOfWeek);
        return firstDayOfWeek;
    }

    /**
     * 描述：获取本周日.日期
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        String lastDayOfWeek = getDayOfWeek(format, Calendar.SUNDAY);
//        Logger.d("获取本周日-日期->" + lastDayOfWeek);
        return lastDayOfWeek;
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format        the format
     * @param calendarField the calendar field
     * @return String String类型日期时间
     */
    public static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
//                Logger.d("获取本周的某一天.-日期->" + strDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取本周的某一天..异常-->" + e.getMessage());
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
//            Logger.d("获取本月第一天..-日期->" + strDate);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取本月第一天..异常-->" + e.getMessage());
        }
        return strDate;

    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
//            Logger.d("获取本月最后一天...-日期->" + strDate);
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取本月最后一天...异常-->" + e.getMessage());
        }
        return strDate;
    }

    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
//            Logger.d("获取表示当前日期的0点时间毫秒数....-日期->" + date.getTime());
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取表示当前日期的0点时间毫秒数....异常-->" + e.getMessage());
        }
        return -1;
    }

    /**
     * 描述：获取表示当前日期24点时间毫秒数.
     *
     * @return the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
//            Logger.d("获取表示当前日期24点时间毫秒数...-日期->" + date.getTime());
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("获取表示当前日期24点时间毫秒数....异常-->" + e.getMessage());
        }
        return -1;
    }

    /**
     * 描述：判断是否是闰年()
     * <p>
     * (year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year 年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 描述：根据时间返回格式化后的时间的描述. 小于1小时显示多少分钟前 大于1小时显示今天＋实际日期，大于今天全部显示实际时间
     *
     * @param strDate   the str date
     * @param outFormat the out format
     * @return the string
     */
    public static String formatDateStr2Desc(String strDate, String outFormat) {
        DateFormat df = new SimpleDateFormat(dateFormatYMDHMS);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(df.parse(strDate));
            c1.setTime(new Date());
            int d = getOffectDay(c1.getTimeInMillis(), c2.getTimeInMillis());
            if (d == 0) {
                int h = getOffectHour(c1.getTimeInMillis(),
                        c2.getTimeInMillis());
                if (h > 0) {
                    return TOADY + getStringByFormat(strDate, dateFormatHM);
                    // return h + "小时前";
                } else if (h < 0) {
                    // return Math.abs(h) + "小时后";
                } else if (h == 0) {
                    int m = getOffectMinutes(c1.getTimeInMillis(),
                            c2.getTimeInMillis());
                    if (m > 0) {
                        return m + "分钟前";
                    } else if (m < 0) {
                        // return Math.abs(m) + "分钟后";
                    } else {
                        return "刚刚";
                    }
                }

            } else if (d > 0) {
                if (d == 1) {
                    // return "昨天"+getStringByFormat(strDate,outFormat);
                } else if (d == 2) {
                    // return "前天"+getStringByFormat(strDate,outFormat);
                }
            } else if (d < 0) {
                if (d == -1) {
                    // return TOMORROW+getStringByFormat(strDate,outFormat);
                } else if (d == -2) {
                    // return THE_AFTER_TOMORROW+getStringByFormat(strDate,outFormat);
                } else {
                    // return Math.abs(d) +
                    // "天后"+getStringByFormat(strDate,outFormat);
                }
            }
            String out = getStringByFormat(strDate, outFormat);
            if (!TextUtils.isEmpty(out)) {
                return out;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Logger.e("根据时间返回格式化后的时间的描述...异常-->" + e.getMessage());
        }

        return strDate;
    }

    /**
     * 描述：根据时间返回格式化后的时间的描述.昨天，今天，明天，后天，
     *
     * @param strDate   the str date
     * @param inFormat  the in format
     * @param outFormat the out format
     * @return the string
     */
    public static String formatDateDesc(String strDate, String inFormat, String outFormat) {
        String outStrDate = null;
        try {
            Date inDate = getDateByFormat(strDate, inFormat);

            outStrDate = getStringByFormat(inDate, outFormat);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outStrDate == null ? strDate : outStrDate;
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式yyyy
     * @return String 星期几
     */
    public static String getWeekNumber(String strDate, String inFormat) {
        String week = "周日";
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = "周日";
                break;
            case 1:
                week = "周一";
                break;
            case 2:
                week = "周二";
                break;
            case 3:
                week = "周三";
                break;
            case 4:
                week = "周四";
                break;
            case 5:
                week = "周五";
                break;
            case 6:
                week = "周六";
                break;
        }
        return week;
    }

    /**
     * 取指定日期为星期几. 不带周
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式yyyy
     * @return String 星期几
     */
    public static String getWeekNumberWithOutZhou(String strDate, String inFormat) {
        String week = "周日";
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = "日";
                break;
            case 1:
                week = "一";
                break;
            case 2:
                week = "二";
                break;
            case 3:
                week = "三";
                break;
            case 4:
                week = "四";
                break;
            case 5:
                week = "五";
                break;
            case 6:
                week = "六";
                break;
            default:
                break;
        }
        return week;
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式
     * @return String 星期几
     */
    public static int getWeekNumberInt(String strDate, String inFormat) {
        int week = 0;
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return 0;
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = 7;
                break;
            case 1:
                week = 1;
                break;
            case 2:
                week = 2;
                break;
            case 3:
                week = 3;
                break;
            case 4:
                week = 4;
                break;
            case 5:
                week = 5;
                break;
            case 6:
                week = 6;
                break;
        }
        return week;
    }

    /**
     * 根据给定的日期判断是否为上下午.
     *
     * @param strDate the str date
     * @param format  the format
     * @return the time quantum
     */
    @SuppressWarnings("deprecation")
    public static String getTimeQuantum(String strDate, String format) {
        Date mDate = getDateByFormat(strDate, format);
        int hour = mDate.getHours();
        if (hour >= 12) {
            return "PM";
        } else {
            return "AM";
        }
    }

    /**
     * 根据给定的毫秒数算得时间的描述.
     *
     * @param milliseconds the milliseconds
     * @return the time description
     */
    public static String getTimeDescription(long milliseconds) {
        long day=0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        String hourStr="";
        String minStr="";
        String secStr="";
        try {
            day = milliseconds / (24 * 60 * 60 * 1000);
            hour = (milliseconds / (60 * 60 * 1000) - day * 24);
            min = ((milliseconds / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (milliseconds/1000-day*24*60*60-hour*60*60-min*60);
            if(hour<10){
                hourStr="0"+hour;
            }else{
                hourStr=hour+"";
            }
            if(min<10){
                minStr="0"+min;
            }else{
                minStr=min+"";
            }
            if(sec<10){
                secStr="0"+sec;
            }else{
                secStr=sec+"";
            }
        }catch (Exception e){
            e.getMessage();
        }
       return hourStr+":"+minStr+":"+secStr;
    }


    /**
     * 判断date 是不是当前月
     *
     * @param date 目标日期
     * @return > 0 日期晚于当前, ==0 当前月 ,< 0 过去的月份
     */
    public static int compareMonth(Date date) {
        Date d = new Date();
        return (date.getYear() + 1900 - (d.getYear() + 1900)) * 12 + (date.getMonth() - d.getMonth());
    }

    /**
     * 返回两个年月相差多少个月。    >0 ，表示第二个参数比第一个参数大(晚)
     *
     * @param d    第一个日期
     * @param date 第二个日期
     * @return
     */
    public static int compareMonth(Date d, Date date) {
        return (date.getYear() + 1900 - (d.getYear() + 1900)) * 12 + (date.getMonth() - d.getMonth());
    }


    /**
     * 计算两个时间中的月份差
     *
     * @param date1 第一个日期
     * @param date2 第二个日期
     * @return int 月份差
     * @throws ParseException
     */
    public static int getMonthSpace(Date date1, Date date2)
            throws ParseException {

        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    /**
     * 日期格式话 输出hh时mm分
     *
     * @param inputdate 输入的时间
     * @param i         想要的格式下标
     * @return 日期格式话 输出hh时mm分
     */
    public static String getUpTime(String inputdate, int i) {
        String input = inputdate;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(input));
            if (i == 1) {
                String date = new SimpleDateFormat("MM月dd日HH:mm").format(cal
                        .getTime());
                return date;
            }
            if (i == 2) {
                String date = new SimpleDateFormat("HH时").format(cal.getTime());
                return date;
            }
            if (i == 3) {
                String date = new SimpleDateFormat("yyyy年MM月dd日").format(cal
                        .getTime());
                return date;
            }
            if (i == 4) {
                String date = new SimpleDateFormat("HH:mm").format(cal
                        .getTime());
                return date;
            }
            if (i == 5) {
                String date = new SimpleDateFormat("HH时mm分").format(cal
                        .getTime());
                return date;
            }
            if (i == 6) {
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                        .format(cal.getTime());
                return date;
            }
            if (i == 7) {
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .format(cal.getTime());
                return date;
            }
            if (i == 8) {
                String date = new SimpleDateFormat("MM月dd日").format(cal
                        .getTime());
                return date;
            }

        } catch (ParseException e) {
        }
        return input;
    }

    /**
     * 根据指定日期及日期格式获取对应的Calendar对象
     *
     * @param strDate 指定的日期
     * @param format  指定的日期格式
     * @return 生成指定日期的Calendar对象
     */
    public static Calendar getDateToCalendar(String strDate, String format) {
        strDate.replace("T", ",");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(strDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }


    /**
     * @param time yyyy-MM-dd
     * @return example:6月3日
     */
    public static String getTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        } else {
            int month;
            int day;
            try {
                String[] dates = time.split("-");
                month = Integer.parseInt(dates[1]);
                day = Integer.parseInt(dates[2]);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            return month + "月" + day + "日";
        }
    }

    //日程插入的设置，插入成功 true 失败 false
    /**
     * android 2.0之前的不考虑
     */
    public static String calanderEventURL = "content://com.android.calendar/events";
    public static String calanderURL = "content://com.android.calendar/calendars";
    public static String calanderRemiderURL = "";

    /**
     * @param context
     * @param starYear   日程开始的时间
     * @param starMonth
     * @param starDay
     * @param starHour
     * @param starMinute
     * @param endYear    日程结束的时间
     * @param endMonth
     * @param endDay
     * @param endHour
     * @param endMinute
     * @param title      事件标题
     * @param des        事件描述
     * @param location   事件发生位置
     * @return
     */
    public static boolean initCalendar(Context context, int starYear, int starMonth, int starDay, int starHour, int starMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute, String title, String des, String location) {
        //先定义一个URL，到时作为调用系统日历的uri的参数
        if (Build.VERSION.SDK_INT >= 8) {
            calanderRemiderURL = "content://com.android.calendar/reminders";
        } else {
            calanderRemiderURL = "content://calendar/reminders";
        }
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(starYear, starMonth - 1, starDay, starHour, starMinute);  //注意，月份的下标是从0开始的
        startMillis = beginTime.getTimeInMillis();  //插入日历时要取毫秒计时
        Calendar endTime = Calendar.getInstance();
        endTime.set(endYear, endMonth - 1, endDay, endHour, endMinute);
        endMillis = endTime.getTimeInMillis();
        String calId = "";
        try {
            Cursor userCursor = context.getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
            if (userCursor.getCount() > 0) {
                userCursor.moveToLast();  //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
                calId = userCursor.getString(userCursor.getColumnIndex("_id"));
            } else {
                // 没有账户，请先添加账户;

                if (initCalendars(context)) {
                    //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
                    userCursor.moveToLast();
                    calId = userCursor.getString(userCursor.getColumnIndex("_id"));
                } else {
//                    Logger.e("插入账户失败");
                    return false;
                }
            }
        } catch (Exception e) {
//            Logger.e("查询账户异常");
            return false;
        }
        ContentValues eValues = new ContentValues();  //插入事件
        ContentValues rValues = new ContentValues();  //插入提醒，与事件配合起来才有效
        TimeZone tz = TimeZone.getDefault();//获取默认时区
        //插入日程
        eValues.put(CalendarContract.Events.DTSTART, startMillis);
        eValues.put(CalendarContract.Events.DTEND, endMillis);
        eValues.put(CalendarContract.Events.TITLE, title);
        eValues.put(CalendarContract.Events.DESCRIPTION, des);
        eValues.put(CalendarContract.Events.CALENDAR_ID, calId);
        eValues.put(CalendarContract.Events.EVENT_LOCATION, location);
        eValues.put(CalendarContract.Events.EVENT_TIMEZONE, tz.getID());
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            Logger.e("权限异常，检查是否添加权限");
            return false;
        }
        //添加事件
        Uri newEvent = null;
        try {
            newEvent = context.getContentResolver().insert(Uri.parse(calanderEventURL), eValues);
        } catch (Exception e) {
//            Logger.e("事件添加出现异常，导致失败");
            return false;
        }
        //插完日程之后必须再插入以下代码段才能实现提醒功能
        // 得到当前表的_id
        String myEventsId = newEvent.getLastPathSegment();
        rValues.put("event_id", myEventsId);
        //提前0分钟提醒,有其他需求再进行变动
        rValues.put("minutes", 0);
        //如果需要有提醒,必须要有这一行
        rValues.put("method", 1);
        Uri newRemind = null;
        try {
            newRemind = context.getContentResolver().insert(Uri.parse(calanderRemiderURL), rValues);
        } catch (Exception e) {
//            Logger.e("事件添加提醒，出现异常，导致失败");
            return false;
        }
        if (newEvent == null || newRemind == null) {
//            Logger.e("事件添加失败");
            return false;
        }
        return true;
    }

    /**
     * 添加日程时，若没有账户的话，需要先添加账户
     *
     * @param context
     * @return 添加成功true
     */
    public static boolean initCalendars(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, "yy");
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, "JSJ@gmail.com");
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, "com.android.exchange");
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "JSJ");
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, -9206951);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, "JSJ@gmail.com");
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);
        Uri calendarUri = CalendarContract.Calendars.CONTENT_URI;
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "JSJ@gmail.com")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "com.android.exchange")
                .build();
        Uri uri = null;
        uri = context.getContentResolver().insert(calendarUri, value);
        if (uri == null) {
            return false;
        }
        return true;
    }

    /**
     * 分钟转成天 时 分
     *
     * @param totalMinutes 总共的分钟数
     * @return
     */
    public static String ChangeSecondsToTime(int totalMinutes) {
        int day;
        int hour;
        int minute;
        if (0 == totalMinutes) {
            return "";
        } else {
            try {
                //计算天 1天 = 24*60 1小时=60
                day = totalMinutes / (24 * 60);
                hour = (totalMinutes % (24 * 60)) / 60;
                minute = (totalMinutes % (24 * 60)) % 60;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            if (day == 0) {//不足一天
                //再去判断 足不足一小时
                if (hour == 0) {//不足一小时
                    //再去判断 足不足一分钟
                    if (minute == 0) {//不足一分钟
                        return "";
                    } else {
                        return minute + "m";
                    }
                } else {
                    return hour + "h" + minute + "m";
                }
            } else {
                return day + "d" + hour + "h" + minute + "m";
            }
        }
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else if (monthNow > monthBirth) {

            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * 判断日期是否是周末
     *
     * @return
     */
    public static boolean isWeekend(String strDate) {
        int weekNumber = getWeekNumberInt(strDate, "yyyy-MM-dd");
        //判断是否是周六日
        if (weekNumber == 6 || weekNumber == 7) {
            return true;
        }
        return false;
    }


    //**************************************下面的是常用的方法******************************************************//

    /**
     * 字符串转换为java.util.Date<br>
     * <p>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'
     * <br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'
     * <br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'
     * <br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00'
     * <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am'
     * <br>
     *
     * @param time String 字符串<br>
     * @return Date 日期<br>
     */
    public static Date stringToDate(String time) {
        Date ctime = new Date();
        try {
            SimpleDateFormat formatter;
            int tempPos = time.indexOf("AD");
            time = time.trim();
            formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
            if (tempPos > -1) {
                time = time.substring(0, tempPos) +
                        "公元" + time.substring(tempPos + "AD".length());//china
                formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
            }
            tempPos = time.indexOf("-");
            if (tempPos > -1 && (!time.contains(" "))) {
                formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
            } else if ((time.contains("/")) && (time.contains(" "))) {
                formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            } else if ((time.contains("-")) && (time.contains(" "))) {
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else if ((time.contains("/")) && (time.contains("am")) || (time.contains("pm"))) {
                formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
            } else if ((time.contains("-")) && (time.contains("am")) || (time.contains("pm"))) {
                formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
            }
            ParsePosition pos = new ParsePosition(0);
            ctime = formatter.parse(time, pos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ctime;
    }


    /**
     * 将差传入的时间进行格式化，倒计时会用
     *
     * @param mss interval in millisecond
     * @return formatted date string
     */
    public static String formatDuring(long mss, Context context) {
        String time = "";

        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;

        if (seconds != 0) {
            time = seconds + context.getString(R.string.second);
        }

        if (minutes != 0) {
            time = minutes + context.getString(R.string.minute) + time;
        }

        if (hours != 0) {
            time = hours + context.getString(R.string.hour) + time;
        }

        if (days != 0) {
            time = days + context.getString(R.string.day) + time;
        }

        if (TextUtils.isEmpty(time)) {
            time = "0" + context.getString(R.string.second);
        }

        return time;
    }

    /**
     * 根据传入的时间戳，按照要求的格式导出
     *
     * @param time
     * @param format yyyy-MM-dd
     * @return
     */
    public static String timeStamp2Date(long time, String format) {
        Long timestamp = time * 1000;
        String date = new SimpleDateFormat(format).format(new Date(timestamp));
        return date;
    }

    /**
     * String 转 Date
     *
     * @param str    要转化的字符串
     * @param format 要转化的字符串 的日期格式 必须要对应
     * @return
     */
    public static Date strToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date parse = null;
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 比较两个日期（毫秒格式）相差的天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getTwoDateOffsetDays(long startDate, long endDate) {
        //一天的毫秒数
        long nd = 1000 * 24 * 60 * 60;
        //相差天数 使用int就够了
        return (int) Math.abs((startDate - endDate) / nd);
    }


    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        //同一年
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {
            //不同年
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }
    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String starttime, String endtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        String hourStr="";
        String minStr="";
        String secStr="";
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
            if(hour<10){
                hourStr="0"+hour;
            }else{
                hourStr=hour+"";
            }
            if(min<10){
                minStr="0"+min;
            }else{
                minStr=min+"";
            }
            if(sec<10){
                secStr="0"+sec;
            }else{
                secStr=sec+"";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour + ":" + min + ":" + sec;
    }
    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
     * 根据差值返回多长之间前或多长时间后
     * */
    public static String getDistanceTime(long time1, long time2) {
        String strDate1 = timeStamp2Date(time1, SaDateUtils.dateFormatYMDHMS);
        String strDate2 = timeStamp2Date(time2, SaDateUtils.dateFormatYMDHMS);
        Date date1 = getDateByFormat(strDate1, SaDateUtils.dateFormatYMDHMS);
        Date date2 = getDateByFormat(strDate2, SaDateUtils.dateFormatYMDHMS);
        long diff;
        if (date1.getTime() < date2.getTime()) {
            diff = date2.getTime() - date1.getTime();
        } else {
            diff = date1.getTime() - date2.getTime();
        }
        long secs = diff / 1000;

        long sec = secs % 60;

        long minites = secs / 60;

        long minute = minites % 60;

        long hour = minites / 60;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("服务时长");
        if (hour > 0)
            stringBuilder.append(hour + "时");
        if (minute > 0)
            stringBuilder.append(minute + "分");
        if (sec > 0)
            stringBuilder.append(sec + "秒");
        return stringBuilder.toString();
    }

    /**
     * @param dateLong 毫秒数
     * @Description: long类型转换成日期
     */
    public static String longToDateStr(long dateLong, String format) {
        Date date = new Date(dateLong);
        SimpleDateFormat sd = new SimpleDateFormat(format);
        return sd.format(date);
    }

    /**
     * 计算两个日期相差的天、时、秒
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;


        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        if (day == 0 && hour == 0 && min == 0) {
            return "两个时间相等";
        } else if (day == 0 && hour == 0) {
            return min + "分钟";
        } else if (day == 0) {
            return hour + "小时" + min + "分钟";
        } else {
            return day + "天" + hour + "小时" + min + "分钟";

        }

    }

    /**
     * Date转换成指定的DateStr
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToDateStr(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * long 转date
     *
     * @param date
     * @return
     */
    public static Date longToDate(long date) {
        return new Date(date);
    }
}
