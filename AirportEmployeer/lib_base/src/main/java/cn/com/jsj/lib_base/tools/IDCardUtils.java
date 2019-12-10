package cn.com.jsj.lib_base.tools;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 省份证号处理工具类,
 * Created by MT on 2015/8/11.
 */

public class IDCardUtils {


    /**
     * 功能：将15位的身份证号码转换成18位的,并去除最后一位字母,实际返回17位的身份证号
     *
     * @param idNum
     * @return
     */
    private static String convertIdNumBy15bit(String idNum) {
        if (idNum.length() == 18) {
            idNum = idNum.substring(0, 17);
        } else if (idNum.length() == 15) {
            idNum = idNum.substring(0, 6) + "19" + idNum.substring(6, 15);
        }
        return idNum;
    }

    /**
     * 功能：获取省份证中的年份
     *
     * @param idNum 身份证号
     * @return 年份
     */
    public static String getBirthdayYear(String idNum) {
        idNum = convertIdNumBy15bit(idNum);
        if (TextUtils.isEmpty(idNum) || !isNumeric(idNum)) {
            return null;
        }
        // 获取出生年
        return idNum.substring(6, 10);// 年份
    }

    /**
     * 功能：获取省份证中的月份
     *
     * @param idNum 身份证号
     * @return 月份
     */
    public static String getBirthdayMonth(String idNum) {
        idNum = convertIdNumBy15bit(idNum);
        if (TextUtils.isEmpty(idNum) || !isNumeric(idNum)) {
            return null;
        }
        // 获取出生 月
        return idNum.substring(10, 12);// 月份
    }

    /**
     * 获取省份证中的日期
     *
     * @param idNum 身份证号
     * @return 日期
     */
    public static String getBirthdayDay(String idNum) {
        idNum = convertIdNumBy15bit(idNum);
        if (TextUtils.isEmpty(idNum) || !isNumeric(idNum)) {
            return null;
        }
        // 获取出生 日期
        return idNum.substring(12, 14);// 日期
    }

    /**
     * 功能：获取省份证中的性别
     *
     * @param idNum 身份证号,这18位数字分别是6位行政区划代码＋8位出生日期＋3位顺序码＋1位校验码,
     *              顺序码中最后一位用于区分性别，男的为奇数 女的为偶数
     * @return 1:女 2:男
     */
    public static int getIdNumSex(String idNum) {
        idNum = convertIdNumBy15bit(idNum);
        if (TextUtils.isEmpty(idNum) || !isNumeric(idNum)) {
            return 0;
        }
        if (idNum.length() != 17) {
            return 0;
        }
        String sexStr = idNum.substring(16);
        if (!isNumeric(sexStr)) {
            return 0;
        }
        //获取最后一位性别标识
        int sexInt = Integer.valueOf(sexStr);
        //判断奇偶
        if (sexInt % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param idNum
     * @return
     */
    private static boolean isNumeric(String idNum) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(idNum);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate 校验的日期
     * @return
     */
    public static boolean isDate(String strDate) {
        //定义正则
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        //进行校验
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取出生日期
     *
     * @param no
     * @return yyyy-mm-dd
     */
    public static String getBirthDayByFarmat(String no) {
        String date = "";
        //通过身份证号码获取生日
        try {
            String strYear = getBirthdayYear(no);
            String strMonth = getBirthdayMonth(no);
            String strDay = getBirthdayDay(no);
            date = SaDateUtils.getStringByFormat(strYear + "-" + strMonth + "-" + strDay, SaDateUtils.dateFormatYMD, SaDateUtils.dateFormatYMD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 功能：校验身份证号是否合法,如果合法那么errorInfo为""字符
     *
     * @param IDStr
     * @return
     */
    public static String IDCardValidate(String IDStr) {
        if (TextUtils.isEmpty(IDStr)) {
            return "身份证号码不能为空";
        }
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = getInstance("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equalsIgnoreCase(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        // =====================(end)=====================
        return "";
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
    public static SimpleDateFormat getInstance(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf;
    }
}
