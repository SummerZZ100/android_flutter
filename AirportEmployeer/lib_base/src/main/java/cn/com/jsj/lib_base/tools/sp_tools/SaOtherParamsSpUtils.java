package cn.com.jsj.lib_base.tools.sp_tools;

/**
 * 其他信息的SP存储工具类
 *
 * @author : wangjing
 * @date : 2019/1/9
 */
public class SaOtherParamsSpUtils {

    public static SaJsjEmployeeSpUtils getOtherInfoSp() {
        String fileName = "JSJ_AIRPORT_EMPLOYEE_OTHER_PARAMS";
        return SaJsjEmployeeSpUtils.getInstance(fileName);
    }
}
