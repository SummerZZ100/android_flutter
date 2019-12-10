package cn.com.jsj.lib_base.tools.sp_tools;

/**
 * 登录接口返回的登录信息
 *
 * @author : wangjing
 * @date : 2019/1/9
 */
public class SaLoginInfoSpUtils {

    public static SaJsjEmployeeSpUtils getLoginInfoSp() {
        String fileName = "JSJ_AIRPORT_EMPLOYEE_LOGIN_INFO";
        return SaJsjEmployeeSpUtils.getInstance(fileName);
    }
}
