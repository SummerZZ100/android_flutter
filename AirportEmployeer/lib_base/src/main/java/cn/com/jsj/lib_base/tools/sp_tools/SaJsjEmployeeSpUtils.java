package cn.com.jsj.lib_base.tools.sp_tools;

import android.content.Context;

import java.util.Map;

import cn.com.jsj.lib_base.base.BaseKTApplication;

/**
 * 员工端用户信息存储sp存储工具类 方法二次处理
 *
 * @author : wangjing
 * @date : 2019/1/9
 */
public class SaJsjEmployeeSpUtils {


    public static SaJsjEmployeeSpUtils getInstance(String fileName) {
        return new SaJsjEmployeeSpUtils(fileName);
    }


    private String mFileName;


    private SaJsjEmployeeSpUtils(String fileName) {
        this.mFileName = fileName;
    }

    /**
     * put值
     *
     * @param key   key
     * @param value value
     */
    public void put(String key, Object value) {
        SaBaseSpUtils.put(mFileName, BaseKTApplication.Companion.getInstance(), key, value);
    }

    /**
     * 返回string 默认为"“
     *
     * @param key key
     * @return String
     */
    public String getString(String key) {
        return getString(key, "");
    }

    /**
     * 返回string 指定默认defValue
     *
     * @param key key
     * @return String
     */
    public String getString(String key, String defValue) {
        return SaBaseSpUtils.getString(mFileName, BaseKTApplication.Companion.getInstance(), key, defValue);
    }

    /**
     * int 默认为0
     *
     * @param key key
     * @return int
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }

    /**
     * int 默认为指定defValue
     *
     * @param key key
     * @return int
     */
    public int getInt(String key, int defValue) {
        return SaBaseSpUtils.getInt(mFileName, BaseKTApplication.Companion.getInstance(), key, defValue);
    }

    /**
     * 获取boolean 默认为false
     *
     * @param key key
     * @return boolean
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取boolean 默认为defValue
     *
     * @param key key
     * @return boolean
     */
    public boolean getBoolean(String key, boolean defValue) {
        return SaBaseSpUtils.getBoolean(mFileName, BaseKTApplication.Companion.getInstance(), key, defValue);
    }


    /**
     * 获取float 默认为0
     *
     * @param key key
     * @return float
     */
    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    /**
     * 获取float 默认为defValue
     *
     * @param key key
     * @return float
     */
    public float getFloat(String key, float defValue) {
        return SaBaseSpUtils.getFloat(mFileName, BaseKTApplication.Companion.getInstance(), key, defValue);
    }


    /**
     * 获取long 默认为0
     *
     * @param key key
     * @return long
     */
    public long getLong(String key) {
        return getLong(key, 0);
    }

    /**
     * 获取long  默认为defValue
     *
     * @param key key
     * @return long
     */
    public long getLong(String key, long defValue) {
        return SaBaseSpUtils.getLong(mFileName, BaseKTApplication.Companion.getInstance(), key, defValue);
    }


    public void remove(Context context, String key) {
        SaBaseSpUtils.remove(mFileName, context, key);
    }

    public void clear() {
        SaBaseSpUtils.clear(BaseKTApplication.Companion.getInstance(), mFileName);
    }
    public void clear(String fileName) {
        SaBaseSpUtils.clear(BaseKTApplication.Companion.getInstance(), fileName);
    }

    public void clear(Context context) {
        SaBaseSpUtils.clear(context, mFileName);
    }


    public boolean contains(Context context, String key) {
        return SaBaseSpUtils.contains(mFileName, context, key);
    }


    public Map<String, ?> getAll(Context context) {
        return SaBaseSpUtils.getAll(mFileName, context);
    }
    public void putObject(String key, Object object) {
        SaBaseSpUtils.putObject(mFileName, BaseKTApplication.Companion.getInstance(), key, object);
    }
    public void putObject(Context context, String key, Object object) {
        SaBaseSpUtils.putObject(mFileName, context, key, object);
    }

    public <T> T getObject(Context context, String key, Class<T> clazz) {
        return SaBaseSpUtils.getObject(mFileName, context, key, clazz);
    }


}
