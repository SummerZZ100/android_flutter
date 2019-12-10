package cn.com.jsj.lib_base.tools;

/**
 * todo 描述：模块名_具体页面描述
 *
 * @author : wangjing
 * @date : 2019/1/17
 */
public class SaCommonInfoCheckUtils {

    public static boolean checkIsPhoneNum(String num) {
        if (num.isEmpty()) {
            SaToastUtils.showToast("请输入手机号");
            return false;
        }
        if (num.length() != 11) {
            SaToastUtils.showToast("请输入正确的手机号");
            return false;
        }

        return true;
    }

}
