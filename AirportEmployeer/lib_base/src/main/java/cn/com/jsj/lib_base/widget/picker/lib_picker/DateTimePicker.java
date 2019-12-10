package cn.com.jsj.lib_base.widget.picker.lib_picker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import cn.com.jsj.lib_base.widget.picker.lib_picker.util.DateUtils;
import cn.com.jsj.lib_base.widget.picker.lib_picker.widget.WheelView;

/**
 * 日期选择器
 *
 * @author 董宏宇[QQ :1358506549]
 * @version 2016 /04/12
 */
public class DateTimePicker extends WheelPicker {

    /**
     * 是否可选当前时间||指定时间之前的时间
     */
    private boolean mCanChoiceBefore = true;


    /**
     * 年月日
     */
    public static final int YEAR_MONTH_DAY = 0;
    /**
     * 年月
     */
    public static final int YEAR_MONTH = 1;
    /**
     * 月日
     */
    public static final int MONTH_DAY = 2;

    /**
     * 年月日时
     */
    public static final int YEAR_MONTH_DAY_HOUR = 3;

    /**
     * 年月日时分
     */
    public static final int YEAR_MONTH_DAY_HOUR_MINUTE = 4;

    /**
     * 24小时
     */
    public static final int HOUR_OF_DAY = 0;
    /**
     * 12小时
     */
    public static final int HOUR = 1;

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

    //日期展示模式,年月日时,年月日,年月,月日
    private int mode = YEAR_MONTH_DAY;

    //时间展示模式,12小时制||24小时制
    private int time_mode = HOUR_OF_DAY;

    //获取当前的年份
    private Date nowDate = new Date();

    private Calendar calendar = Calendar.getInstance();

    //当前时间
    private int nowYear, nowMonth, nowDay, nowHour, nowMinute;

    //小时的开始时间
    private int startHour = 0, endHour = 24;


    /**
     * 安卓开发应避免使用枚举类（enum），因为相比于静态常量enum会花费两倍以上的内存。
     *
     * @link http ://developer.android.com/training/articles/memory.html#Overhead
     */
    @IntDef(flag = false, value = {YEAR_MONTH_DAY, YEAR_MONTH, MONTH_DAY, YEAR_MONTH_DAY_HOUR, YEAR_MONTH_DAY_HOUR_MINUTE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    /**
     * 安卓开发应避免使用枚举类（enum），因为相比于静态常量enum会花费两倍以上的内存。
     *
     * @link http ://developer.android.com/training/articles/memory.html#Overhead
     */
    @IntDef(flag = false, value = {HOUR_OF_DAY, HOUR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimeMode {
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     */
    public DateTimePicker(Activity activity) {
        this(activity, YEAR_MONTH_DAY_HOUR_MINUTE, HOUR_OF_DAY);
    }

    /**
     * Instantiates a new Date picker. 默认24小时制
     *
     * @param activity the activity
     * @param mode     the mode
     */
    public DateTimePicker(Activity activity, @Mode int mode) {
        this(activity, mode, HOUR_OF_DAY);
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     * @param mode     the mode
     * @see #YEAR_MONTH_DAY #YEAR_MONTH_DAY #YEAR_MONTH_DAY
     * @see #YEAR_MONTH #YEAR_MONTH #YEAR_MONTH
     * @see #MONTH_DAY #MONTH_DAY #MONTH_DAY
     * @see #HOUR_OF_DAY #HOUR_OF_DAY #HOUR_OF_DAY
     */
    public DateTimePicker(Activity activity, @Mode int mode, @TimeMode int atime_mode) {
        super(activity);
        this.mode = mode;
        this.time_mode = atime_mode;

        calendar.setTime(nowDate);

        //当前的年份
        nowYear = calendar.get(Calendar.YEAR);
        //当前的月份
        nowMonth = calendar.get(Calendar.MONTH) + 1;
        //当前的天
        nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        //当前的分
        nowMinute = calendar.get(Calendar.MINUTE);


        //设置默认选中的当前时间
        selectedYear = String.valueOf(calendar.get(Calendar.YEAR));
        selectedMonth = DateUtils.fillZero(calendar.get(Calendar.MONTH) + 1);
        selectedDay = DateUtils.fillZero(calendar.get(Calendar.DAY_OF_MONTH));
        if (time_mode == HOUR) {
            //当前的时
            nowHour = calendar.get(Calendar.HOUR);
            selectedHour = DateUtils.fillZero(Calendar.getInstance().get(Calendar.HOUR));
        } else {
            //当前的时
            nowHour = calendar.get(Calendar.HOUR_OF_DAY);
            selectedHour = DateUtils.fillZero(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        }
        //当前分
        selectedMinute = DateUtils.fillZero(Calendar.getInstance().get(Calendar.MINUTE));


        for (int i = nowYear; i <= nowYear + 50; i++) {
            years.add(String.valueOf(i));
        }
        for (int i = 1; i <= 12; i++) {
            months.add(DateUtils.fillZero(i));
        }
        for (int i = 1; i <= 31; i++) {
            days.add(DateUtils.fillZero(i));
        }

        //初始化:时
        // 12小时制
        if (time_mode == HOUR) {
            startHour = 1;
            endHour = 12;
            for (int i = startHour; i <= endHour; i++) {
                hours.add(DateUtils.fillZero(i));
            }
        }
        //24小时制
        else {
            startHour = 0;
            endHour = 24;
            for (int i = startHour; i < endHour; i++) {
                hours.add(DateUtils.fillZero(i));
            }
        }

        //初始化分钟的数据
        minutes = new ArrayList<String>();
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

    private int findItemIndex(ArrayList<String> items, int item) {
        //折半查找有序元素的索引
        int index = Collections.binarySearch(items, item, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String lhsStr = lhs.toString();
                String rhsStr = rhs.toString();
                lhsStr = lhsStr.startsWith("0") ? lhsStr.substring(1) : lhsStr;
                rhsStr = rhsStr.startsWith("0") ? rhsStr.substring(1) : rhsStr;
                return Integer.parseInt(lhsStr) - Integer.parseInt(rhsStr);
            }
        });
        if (index < 0) {
            index = 0;
        }
        return index;
    }

    /**
     * Sets selected item.
     *
     * @param year   the year
     * @param month  the month
     * @param day    the day
     * @param hour   the hour
     * @param minute the minute
     */
    public void setSelectedItem(String year, String month, String day, String hour, String minute) {
        selectedYear = year;
        selectedMonth = month;
        selectedDay = day;
        selectedHour = hour;
        selectedMinute = minute;
    }

    /**
     * Sets selected item.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     * @param hour  the hour
     */
    public void setSelectedItem(String year, String month, String day, String hour) {
        selectedYear = year;
        selectedMonth = month;
        selectedDay = day;
        selectedHour = hour;
    }

    /**
     * Sets selected item.
     *
     * @param year  the year
     * @param month the month
     * @param day   the day
     */
    public void setSelectedItem(String year, String month, String day) {
        selectedYear = year;
        selectedMonth = month;
        selectedDay = day;
    }

    /**
     * Sets selected item.
     *
     * @param yearOrMonth the year or month
     * @param monthOrDay  the month or day
     */
    public void setSelectedItem(String yearOrMonth, String monthOrDay) {
        if (mode == MONTH_DAY) {
            selectedMonth = yearOrMonth;
            selectedDay = monthOrDay;
        } else {
            selectedYear = yearOrMonth;
            selectedMonth = monthOrDay;
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

    public boolean getCanChoiceBefore() {
        return mCanChoiceBefore;
    }

    public void setCanChoiceBefore(boolean canChoiceBefore) {
        mCanChoiceBefore = canChoiceBefore;
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

        //获取可以进行滚动的控件
        //年
        WheelView yearView = new WheelView(activity);
        yearView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        yearView.setTextSize(textSize);
        yearView.setTextColor(textColorNormal, textColorFocus);
        yearView.setLineVisible(lineVisible);
        yearView.setLineColor(lineColor);
        yearView.setOffset(offset);
        layout.addView(yearView);
        TextView yearTextView = new TextView(activity);
        yearTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        yearTextView.setTextSize(textSize);
        yearTextView.setTextColor(textColorFocus);
        //设置年label
        if (!TextUtils.isEmpty(yearLabel)) {
            yearTextView.setText(yearLabel);
        }
        layout.addView(yearTextView);

        //月
        final WheelView monthView = new WheelView(activity);
        monthView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        monthView.setTextSize(textSize);
        monthView.setTextColor(textColorNormal, textColorFocus);
        monthView.setLineVisible(lineVisible);
        monthView.setLineColor(lineColor);
        monthView.setOffset(offset);
        layout.addView(monthView);
        TextView monthTextView = new TextView(activity);
        monthTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        monthTextView.setTextSize(textSize);
        monthTextView.setTextColor(textColorFocus);
        //设置月label
        if (!TextUtils.isEmpty(monthLabel)) {
            monthTextView.setText(monthLabel);
        }
        layout.addView(monthTextView);

        //日
        final WheelView dayView = new WheelView(activity);
        dayView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        dayView.setTextSize(textSize);
        dayView.setTextColor(textColorNormal, textColorFocus);
        dayView.setLineVisible(lineVisible);
        dayView.setLineColor(lineColor);
        dayView.setOffset(offset);
        layout.addView(dayView);
        TextView dayTextView = new TextView(activity);
        dayTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        dayTextView.setTextSize(textSize);
        dayTextView.setTextColor(textColorFocus);
        //设置日label
        if (!TextUtils.isEmpty(dayLabel)) {
            dayTextView.setText(dayLabel);
        }
        layout.addView(dayTextView);

        //时
        final WheelView hourView = new WheelView(activity);
        hourView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        hourView.setTextSize(textSize);
        hourView.setTextColor(textColorNormal, textColorFocus);
        hourView.setLineVisible(lineVisible);
        hourView.setLineColor(lineColor);
        hourView.setOffset(offset);
        layout.addView(hourView);
        TextView hourTextView = new TextView(activity);
        hourTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        hourTextView.setTextSize(textSize);
        hourTextView.setTextColor(textColorFocus);
        //设置时label
        if (!TextUtils.isEmpty(hourLabel)) {
            hourTextView.setText(hourLabel);
        }
        layout.addView(hourTextView);

        //分
        final WheelView minuteView = new WheelView(activity);
        minuteView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        minuteView.setTextSize(textSize);
        minuteView.setTextColor(textColorNormal, textColorFocus);
        minuteView.setLineVisible(lineVisible);
        minuteView.setLineColor(lineColor);
        minuteView.setOffset(offset);
        layout.addView(minuteView);
        TextView minuteTextView = new TextView(activity);
        minuteTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        minuteTextView.setTextSize(textSize);
        minuteTextView.setTextColor(textColorFocus);
        if (!TextUtils.isEmpty(minuteLabel)) {
            minuteTextView.setText(minuteLabel);
        }
        layout.addView(minuteTextView);

        minuteView.setItems(minutes);


        //根据模式初始化视图
        //年月日时
        if (mode == YEAR_MONTH_DAY_HOUR) {
            //隐藏分
            minuteView.setVisibility(View.GONE);
            minuteTextView.setVisibility(View.GONE);
        }
        //年月日
        else if (mode == YEAR_MONTH_DAY) {
            //隐藏时
            hourView.setVisibility(View.GONE);
            hourTextView.setVisibility(View.GONE);
            //隐藏分
            minuteView.setVisibility(View.GONE);
            minuteTextView.setVisibility(View.GONE);
        }
        //年月
        else if (mode == YEAR_MONTH) {
            //隐藏日
            dayView.setVisibility(View.GONE);
            dayTextView.setVisibility(View.GONE);
            //隐藏时
            hourView.setVisibility(View.GONE);
            hourTextView.setVisibility(View.GONE);
            //隐藏分
            minuteView.setVisibility(View.GONE);
            minuteTextView.setVisibility(View.GONE);
        }
        //月日
        else if (mode == MONTH_DAY) {
            //隐藏年
            yearView.setVisibility(View.GONE);
            yearTextView.setVisibility(View.GONE);
            //隐藏时
            hourView.setVisibility(View.GONE);
            hourTextView.setVisibility(View.GONE);
            //隐藏分
            minuteView.setVisibility(View.GONE);
            minuteTextView.setVisibility(View.GONE);
        }


        //判断时间选择器的模式
        //模式非月日,需要初始化年的控件
        if (TextUtils.isEmpty(selectedYear)) {
            yearView.setItems(years);
        } else {
            yearView.setItems(years, selectedYear);
        }

        //设置年的滚轮选择器的监听
        yearView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                //设置年分选中的数据
                selectedYear = item;

                //根据年份初始化月份的选择列表，如果mCanChoiceBefore为false那么当前时间前的时间月份就不进行初始化
                if (!mCanChoiceBefore) {
                    //清空原来的数据集合
                    months.clear();

                    //选中的年份
                    int selectYear = stringToYearMonthDay(item);

                    //两个数据不相等的时候初始化月份为1-12月
                    if (selectYear != nowYear) {
                        for (int i = 1; i <= 12; i++) {
                            months.add(DateUtils.fillZero(i));
                        }
                    }
                    //选中的年和当前年份相等，初始化当前时间的月份到12月
                    else {
                        for (int i = nowMonth; i <= 12; i++) {
                            months.add(DateUtils.fillZero(i));
                        }
                    }

                    if (stringToYearMonthDay(selectedMonth) < nowMonth) {
                        selectedMonth = String.valueOf(nowMonth);
                        monthView.setItems(months);
                    } else {
                        //年变动时，保持之前选择的月不动
                        monthView.setItems(months, selectedMonth);
                    }
                } else {
                    monthView.setItems(months, selectedMonth);
                }

                //初始化天
                initDays(dayView);

            }
        });

        //设置月滚轮选择器滚动监听
        monthView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedMonth = item;
                if (mode != YEAR_MONTH) {
                    initDays(dayView);
                }
            }
        });


        //当模式非年月的时候需要初始化日期的控件
        if (mode != YEAR_MONTH) {
            dayView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                    selectedDay = item;
                    //初始化时
                    initHours(hourView);
                }
            });
        }


        //当模式为年月日时分
        if (mode == YEAR_MONTH_DAY_HOUR_MINUTE || mode == YEAR_MONTH_DAY_HOUR) {
            hourView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                    selectedHour = item;
                    if (mode == YEAR_MONTH_DAY_HOUR_MINUTE) {
                        //初始化分
                        initMinutes(minuteView);
                    }
                }
            });

            if (mode == YEAR_MONTH_DAY_HOUR_MINUTE) {
                minuteView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                        selectedMinute = item;
                    }
                });
            }
        }


        return layout;
    }

    /**
     * 初始化分
     *
     * @param minuteView 分的控件
     */
    private void initMinutes(WheelView minuteView) {
        minutes.clear();
        //不可选当前时间之前的数据
        if (!mCanChoiceBefore) {
            //初始化分
            if (mode == YEAR_MONTH_DAY_HOUR_MINUTE) {
                //初始化时,如果选中的时间为当前的年,月,日,那么时只能选当前时间之后的数据
                if (stringToYearMonthDay(selectedMonth) == nowMonth &&
                        stringToYearMonthDay(selectedYear) == nowYear &&
                        stringToYearMonthDay(selectedDay) == nowDay &&
                        stringToYearMonthDay(selectedHour) == nowHour) {
                    //初始化分钟的数据
                    minutes = new ArrayList<String>();
                    for (int i = nowMinute; i < 60; i++) {
                        minutes.add(DateUtils.fillZero(i));
                    }
                    minuteView.setItems(minutes);
                } else {
                    //初始化分钟的数据
                    minutes = new ArrayList<String>();
                    for (int i = 0; i < 60; i++) {
                        minutes.add(DateUtils.fillZero(i));
                    }
                    minuteView.setItems(minutes, selectedMinute);
                }
            }
        } else {
            //初始化分钟的数据
            minutes = new ArrayList<String>();
            for (int i = 0; i < 60; i++) {
                minutes.add(DateUtils.fillZero(i));
            }
            minuteView.setItems(minutes, selectedMinute);
        }

    }


    /**
     * 初始化时
     *
     * @param hourView 小时控件
     */
    private void initHours(WheelView hourView) {
        hours.clear();

        //不可选当前时间之前的数据
        if (!mCanChoiceBefore) {
            if (mode == YEAR_MONTH_DAY_HOUR || mode == YEAR_MONTH_DAY_HOUR_MINUTE) {
                //初始化时,如果选中的时间为当前的年,月,日,那么时只能选当前时间之后的数据
                if (stringToYearMonthDay(selectedMonth) == nowMonth &&
                        stringToYearMonthDay(selectedYear) == nowYear &&
                        stringToYearMonthDay(selectedDay) == nowDay) {
                    // 12小时制
                    if (time_mode == HOUR) {
                        for (int i = nowHour; i <= endHour; i++) {
                            hours.add(DateUtils.fillZero(i));
                        }
                    }
                    //24小时制
                    else {
                        for (int i = nowHour; i <= endHour; i++) {
                            if (i != 24) {
                                hours.add(String.valueOf(i));
                            }
                        }
                    }
                    hourView.setItems(hours);
                }
                //非当前时间
                else {
                    // 12小时制
                    if (time_mode == HOUR) {
                        for (int i = startHour; i <= endHour; i++) {
                            hours.add(DateUtils.fillZero(i));
                        }
                    }
                    //24小时制
                    else {
                        for (int i = startHour; i <= endHour; i++) {
                            if (i != 24) {
                                hours.add(String.valueOf(i));
                            }
                        }
                    }
                    hourView.setItems(hours, selectedHour);
                }
            }
        }
        //可选当前的时间之外的
        else {
            // 12小时制
            if (time_mode == HOUR) {
                for (int i = startHour; i <= endHour; i++) {
                    hours.add(DateUtils.fillZero(i));
                }
            }
            //24小时制
            else {
                for (int i = startHour; i <= endHour; i++) {
                    if (i != 24) {
                        hours.add(String.valueOf(i));
                    }
                }
            }
            hourView.setItems(hours, selectedHour);
        }

    }

    /**
     * 初始化天的滚轮控件
     *
     * @param dayView 日期选择控件
     */
    private void initDays(WheelView dayView) {
        days.clear();

        //不可选当前时间之前的数据
        if (!mCanChoiceBefore) {
            //需要根据年份及月份动态计算天数,但是当前时间之前的天为不可选
            int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(selectedYear), stringToYearMonthDay(selectedMonth));

            //如果选中时间为当前年的当前月的时间,那么天只能选中当前天之后
            if (stringToYearMonthDay(selectedMonth) == nowMonth && stringToYearMonthDay(selectedYear) == nowYear) {
                for (int i = nowDay; i <= maxDays; i++) {
                    days.add(DateUtils.fillZero(i));
                }
                dayView.setItems(days);

            }
            //选中的时间年,月不是当前的时间
            else {
                for (int i = 1; i <= maxDays; i++) {
                    days.add(DateUtils.fillZero(i));
                }

                if (Integer.parseInt(selectedDay) >= maxDays) {
                    //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
                    selectedDay = String.valueOf(maxDays);
                }
                dayView.setItems(days, selectedDay);
            }
        }
        //可选当前时间之前的数据
        else {
            //需要根据年份及月份动态计算天数
            int maxDays = DateUtils.calculateDaysInMonth(stringToYearMonthDay(selectedYear), stringToYearMonthDay(selectedMonth));
            for (int i = 1; i <= maxDays; i++) {
                days.add(DateUtils.fillZero(i));
            }
            if (Integer.parseInt(selectedDay) >= maxDays) {
                //年或月变动时，保持之前选择的日不动：如果之前选择的日是之前年月的最大日，则日自动为该年月的最大日
                selectedDay = String.valueOf(maxDays);
            }
            dayView.setItems(days, selectedDay);
        }

    }

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

    @Override
    protected void onSubmit() {
        if (onDatePickListener != null) {
            String year = getSelectedYear();
            String month = getSelectedMonth();
            String day = getSelectedDay();
            String hour = getSelectedHour();
            String minute = getSelectedMinute();

            switch (mode) {
                case YEAR_MONTH_DAY:
                    ((OnYearMonthDayPickListener) onDatePickListener).onDatePicked(year, month, day);
                    break;
                case YEAR_MONTH:
                    ((OnYearMonthPickListener) onDatePickListener).onDatePicked(year, month);
                    break;
                case MONTH_DAY:
                    ((OnMonthDayPickListener) onDatePickListener).onDatePicked(month, day);
                    break;
                default:
                    ((OnYearMonthDayTimePickListener) onDatePickListener).onDatePicked(year, month, day, hour, minute);
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
     * The interface On year month day time pick listener.
     */
    public interface OnYearMonthDayTimePickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param year  the year
         * @param month the month
         * @param day   the day
         */
        void onDatePicked(String year, String month, String day, String hour, String minute);

    }

    /**
     * The interface On year month day pick listener.
     */
    public interface OnYearMonthDayPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param year  the year
         * @param month the month
         * @param day   the day
         */
        void onDatePicked(String year, String month, String day);

    }

    /**
     * The interface On year month pick listener.
     */
    public interface OnYearMonthPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param year  the year
         * @param month the month
         */
        void onDatePicked(String year, String month);

    }

    /**
     * The interface On month day pick listener.
     */
    public interface OnMonthDayPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param month the month
         * @param day   the day
         */
        void onDatePicked(String month, String day);

    }


}
