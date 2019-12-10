package cn.com.jsj.lib_base.widget.picker.lib_picker;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import cn.com.jsj.lib_base.widget.picker.lib_picker.util.DateUtils;
import cn.com.jsj.lib_base.widget.picker.lib_picker.widget.WheelView;


/**
 * -------- 日期 ---------- 维护人 ------------ 变更内容 --------
 * 2016/8/10	18:44	    刘泽			    新增 类
 * 2016/8/10	18:44	    刘泽			    月日星期在一起的选择器,  分钟以10为一个单位,本类只实现一种展示模式,如需要其他类型参照DataTimePicker自行扩展
 */
public class MonthDayWeekPicker extends WheelPicker {


    /**
     * 月日时分
     */
    public static final int MONTH_DAY_HOUR_MINUTE = 4;
    //日期展示模式,年月日时,年月日,年月,月日
    private int mode = MONTH_DAY_HOUR_MINUTE;  //默认选择显示的类型,其他类型用时再扩展
    //年
    private ArrayList<String> years = new ArrayList<String>();
    //月
    private ArrayList<String> months = new ArrayList<String>();
    //日
    private ArrayList<String> days = new ArrayList<String>();
    //时
    private ArrayList<String> hours = new ArrayList<String>();
    //分
    private ArrayList<String> minutes = new ArrayList<String>();

    //选中的时间监听
    private OnDatePickListener onDatePickListener;

    //展示的label
    private String yearLabel = "年", monthLabel = "月", dayLabel = "日", hourLabel = "时", minuteLabel = "分";

    //选中的年,月,日,时
    private String selectedYear = "0", selectedMonth = "0", selectedDay = "0", selectedHour = "0", selectedMinute = "0";

    //获取当前的年份
    private Date nowDate = new Date();

    private Calendar calendar = Calendar.getInstance();

    //当前时间
    private int nowYear, nowMonth, nowDay, nowHour, nowMinute;

    //小时的开始时间
    private int startHour = 0, endHour = 24;
    private ArrayList<String> mCurrentMinutes = new ArrayList<String>();
    private ArrayList<String> mCurrentHours = new ArrayList<String>();

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     */
    public MonthDayWeekPicker(Activity activity) {
        this(activity, MONTH_DAY_HOUR_MINUTE);
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     * @param mode     the mode
     * @see #
     */
    public MonthDayWeekPicker(Activity activity, int mode) {
        super(activity);
        this.mode = mode;

        calendar.setTime(nowDate);
        calendar.add(Calendar.MINUTE, 60);

        //当前的年份
        nowYear = calendar.get(Calendar.YEAR);
        //当前的月份
        nowMonth = calendar.get(Calendar.MONTH) + 1;
        //当前的天
        nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        //当前的分
        nowMinute = calendar.get(Calendar.MINUTE);
        //当前的时
        nowHour = calendar.get(Calendar.HOUR_OF_DAY);

        //设置默认选中的当前时间
        selectedYear = String.valueOf(calendar.get(Calendar.YEAR));
        selectedMonth = DateUtils.fillZero(calendar.get(Calendar.MONTH) + 1);
        selectedDay = DateUtils.fillZero(calendar.get(Calendar.DAY_OF_MONTH));
        selectedHour = DateUtils.fillZero(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        //当前分
        selectedMinute = DateUtils.fillZero(Calendar.getInstance().get(Calendar.MINUTE));

        //年月日时分的集合赋值
        for (int i = nowYear; i <= nowYear + 1; i++) {
            years.add(String.valueOf(i));
        }
        for (int i = 1; i <= 12; i++) {
            months.add(DateUtils.fillZero(i));
        }
        for (int i = 1; i <= 31; i++) {
            days.add(DateUtils.fillZero(i));
        }
        for (int i = 0; i < 24; i++) {
            hours.add(DateUtils.fillZero(i));
        }
        for (int i = 0; i < 60; i++) {
            minutes.add(DateUtils.fillZero(i));
        }
    }

    /**
     * Sets label.
     *
     * @param yearLabel  the year label
     * @param monthLabel the month label
     * @param dayLabel   the day label
     */
    public void setLabel(String yearLabel, String monthLabel,
                         String dayLabel, String hourLabel, String minuteLabel) {
        this.yearLabel = yearLabel;
        this.monthLabel = monthLabel;
        this.dayLabel = dayLabel;
        this.hourLabel = hourLabel;
        this.minuteLabel = minuteLabel;
    }

    /**
     * Sets range.设置年范围
     *
     * @param startYear the start year
     * @param endYear   the end year
     */
    public void setRange(int startYear, int endYear) {
        years.clear();
        for (int i = startYear; i <= endYear; i++) {
            years.add(DateUtils.fillZero(i));
        }
    }

    /**
     * Sets range.设置月范围
     *
     * @param startMonth 开始月份
     * @param endMonth   结束月份
     */
    public void setMonthRange(int startMonth, int endMonth) {
        months.clear();
        for (int i = startMonth; i <= endMonth; i++) {
            months.add(DateUtils.fillZero(i));
        }
    }

    /**
     * Sets range.设置日范围
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     */
    public void setDayRange(int startDay, int endDay) {
        days.clear();
        for (int i = startDay; i <= endDay; i++) {
            days.add(DateUtils.fillZero(i));
        }
    }

    /**
     * Sets range.设置时范围
     *
     * @param startHour 开始时 0  || 0
     * @param endHour   结束时 24 || 12
     */
    public void setHourRange(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
        hours.clear();
        for (int i = startHour; i <= endHour; i++) {
            if (i != 24) {
                hours.add(DateUtils.fillZero(i));
            }
        }
    }


    /**
     * Sets on date pick listener.
     *
     * @param listener the listener
     */
    public void setOnDatePickListener(OnDatePickListener listener) {
        this.onDatePickListener = listener;
    }


    @Override
    @NonNull
    protected View makeCenterView() {


        //线性布局
        LinearLayout layout = new LinearLayout(activity);
        //水平
        layout.setOrientation(LinearLayout.HORIZONTAL);
        //居中
        layout.setGravity(Gravity.CENTER);

        //获取可以进行滚动的控
        //月日星期
        final WheelView monthDayWeekView = new WheelView(activity);
        monthDayWeekView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        monthDayWeekView.setTextSize(textSize);
        monthDayWeekView.setTextColor(Color.GRAY, Color.BLACK);
        monthDayWeekView.setLineFullView(true);
        monthDayWeekView.setLineVisible(true);
        monthDayWeekView.setLineColor(Color.GRAY);
        monthDayWeekView.setOffset(offset);
        layout.addView(monthDayWeekView);

        //时
        final WheelView hourView = new WheelView(activity);
        hourView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        hourView.setTextSize(textSize);
        hourView.setTextColor(Color.GRAY, Color.BLACK);
        hourView.setLineFullView(true);
        hourView.setLineVisible(true);
        hourView.setLineColor(Color.GRAY);
        hourView.setOffset(offset);
        layout.addView(hourView);


        //分
        final WheelView minuteView = new WheelView(activity);
        minuteView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        minuteView.setTextSize(textSize);
        minuteView.setTextColor(Color.GRAY, Color.BLACK);
        minuteView.setLineFullView(true);
        minuteView.setLineVisible(true);
        minuteView.setLineColor(Color.GRAY);
        minuteView.setOffset(offset);
        layout.addView(minuteView);


        minuteView.setItems(minutes);

        final int minute = ((Integer.valueOf(nowMinute)) / 10 * 10) + 10;
        //如果时间是60 小时加1
        if (minute == 60) {
            selectedHour += 1;
        }

        //初始化天
        initMDHs(monthDayWeekView);
        //初始化时
        initHours(hourView);
        //初始化分
        initMinutes(minuteView);

        //当模式非年月的时候需要初始化日期的控件

        monthDayWeekView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedDay = item;
                //初始化时
                initHours(hourView);
            }
        });


        //当模式为年月日时分

        hourView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                if (!item.equals("") && item != null) {
                    selectedHour = item;
                }
                //初始化分
                initMinutes(minuteView);
            }
        });


        minuteView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedMinute = item;
            }
        });


        return layout;
    }

    /**
     * 初始化分
     *
     * @param minuteView 分的控件
     */
    private void initMinutes(WheelView minuteView) {
        minutes.clear();
        mCurrentMinutes.clear();
        //初始化分钟的数据
        for (int i = 10; i <= 60; i += 10) {
            if (i == 60) {
                minutes.add(0, "00");
            } else {
                minutes.add(DateUtils.fillZero(i));
            }
        }
        //初始化时,如果选中的时间为当前的年,月,日,那么时只能选当前时间之后的数据
        if (selectedDay.equals("今天      ") &&
                stringToYearMonthDay(selectedHour) == nowHour) {
            //日
            int minute = ((Integer.valueOf(nowMinute)) / 10 * 10) + 10;
            int indexOf = minutes.indexOf(String.valueOf(minute).equals("60") ? "00" : String.valueOf(minute));
            for (int i = indexOf; i < minutes.size(); i++) {
                mCurrentMinutes.add(minutes.get(i));
            }
            minuteView.setItems(mCurrentMinutes, String.valueOf(minute));
        } else {
            mCurrentMinutes.addAll(minutes);
            if ((stringToYearMonthDay(selectedHour) == nowHour + 1 && nowMinute >= 50)) {
                selectedMinute = "00";
            }
            minuteView.setItems(mCurrentMinutes, selectedMinute);
        }
    }


    /**
     * 初始化时
     *
     * @param hourView 小时控件
     */
    private void initHours(WheelView hourView) {
        hours.clear();
        mCurrentHours.clear();
        //24小时制
        for (int i = startHour; i <= endHour; i++) {
            if (i != 24) {
                hours.add(DateUtils.fillZero(i));
            }
        }
        //如果是今天不显示之前日期
        if (selectedDay.equals("今天      ")) {
            //日
            int indexOf = hours.indexOf(DateUtils.fillZero(nowHour));
            for (int i = indexOf; i < hours.size(); i++) {
                if (i == indexOf) {
                    if (nowMinute < 50) {
                        mCurrentHours.add(hours.get(i));//去掉之前的日期的集合
                    }
                } else {
                    mCurrentHours.add(hours.get(i));//去掉之前的日期的集合
                }
            }
            hourView.setItems(mCurrentHours, mCurrentHours.get(0));
        } else {
            mCurrentHours.addAll(hours);
            hourView.setItems(mCurrentHours, selectedHour);
        }
    }


    /**
     * 初始化天的滚轮控件
     *
     * @param dayView 日期选择控件
     */
    private void initMDHs(WheelView dayView) {
        days.clear();
        for (String year : years) {
            for (String month : months) {
                //需要根据年份及月份动态计算天数,但是当前时间之前的天为不可选
                int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(year), stringToYearMonthDay(month));
                for (int i = 1; i <= maxDays; i++) {
                    String weekNumber = DateUtils.getWeekNumber(year + "-" + month + "-" + i, "yyyy-MM-dd");
                    days.add(month + monthLabel + DateUtils.fillZero(i) + dayLabel + "  " + weekNumber + "       ");
                }
            }
        }
        //日
        String weekNumber = DateUtils.getWeekNumber(DateUtils.fillZero(nowYear) + "-" + DateUtils.fillZero(nowMonth) + "-" + DateUtils.fillZero(nowDay), "yyyy-MM-dd");
        selectedDay = DateUtils.fillZero(nowMonth) + monthLabel + DateUtils.fillZero(nowDay) + dayLabel + "  " + weekNumber + "       ";
        ArrayList<String> currentMDHs = new ArrayList<String>();
        int indexOf = days.indexOf(selectedDay);
        days.lastIndexOf(selectedDay);
        for (int i = indexOf; i < indexOf + 30; i++) {//向后偏移30天
            if (i == indexOf) {
                if (!(nowHour == 23 && nowMinute >= 50))
                    currentMDHs.add("今天      ");
            } else {
                currentMDHs.add(days.get(i));
            }
        }
        dayView.setItems(currentMDHs, currentMDHs.get(0));
    }

    /**
     * String 转化int
     *
     * @param text
     * @return
     */
    private int stringToYearMonthDay(String text) {
        try {
            if (text.startsWith("0")) {
                //截取掉前缀0以便转换为整数
                text = text.substring(1);
            }
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 回调到外部获取选择的数据
     */
    @Override
    protected void onSubmit() {
        if (onDatePickListener != null) {
            String year = getSelectedYear();
            String monthDayWeek = getSelectedDay();
            String hour = getSelectedHour();
            String minute = getSelectedMinute();
            switch (mode) {
                case MONTH_DAY_HOUR_MINUTE:
                    if (monthDayWeek.equals("今天      ")) {
                        String weekNumber = DateUtils.getWeekNumber(nowYear + "-" + nowMonth + "-" + nowDay, "yyyy-MM-dd");
                        monthDayWeek = DateUtils.fillZero(nowMonth) + monthLabel + DateUtils.fillZero(nowDay) + dayLabel + "  " + weekNumber + "       ";
                    }
                    ((OnMonthDayHoursMinuPickListener) onDatePickListener).onDatePicked(year, monthDayWeek.substring(0, 2) + "-" + monthDayWeek.substring(3, 5), hour, minute);
                    break;

            }
        }
    }

    /**
     * Gets selected year.
     *
     * @return the selected year
     */
    public String getSelectedYear() {
        if (!((getSelectedDay().trim()).equals("今天"))) {
            String currentMonth = getSelectedDay().substring(0, 2);
            if (stringToYearMonthDay(currentMonth) < nowMonth) {
                int year = stringToYearMonthDay(selectedYear);
                year++;
                selectedYear = DateUtils.fillZero(year);
            }
        }
        return selectedYear;
    }

    /**
     * Gets selected month.
     *
     * @return the selected month
     */
    public String getSelectedMonth() {
        return selectedMonth;
    }

    /**
     * Gets selected day.
     *
     * @return the selected day
     */
    public String getSelectedDay() {
        return selectedDay;
    }

    /**
     * Gets selected hour.
     *
     * @return the selected hour
     */
    public String getSelectedHour() {
        return selectedHour;
    }

    /**
     * Gets selected minute.
     *
     * @return the selected minute
     */
    public String getSelectedMinute() {
        return selectedMinute;
    }

    /**
     * The interface On date pick listener.
     */
    protected interface OnDatePickListener {

    }


    /**
     * The interface On month day pick listener.
     */
    public interface OnMonthDayHoursMinuPickListener extends OnDatePickListener {

        /**
         * @param year
         * @param monthDayWeek
         * @param hour
         * @param minute
         */
        void onDatePicked(String year, String monthDayWeek, String hour, String minute);

    }

}
