package cn.com.jsj.lib_base.tools.sp_tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 存取SharedPreferences工具类，存储用户登录账户信息，用户信息，查询记录等
 * Created by donghongyu on 2016/5/30.
 */
public class SaBaseSpUtils {



    /**
     * 获取String 方法
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @return sp文件存储的数据
     */
    public static String getString(String fileName,Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }


    /**
     * 获取Int 方法
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @return sp文件存储的数据
     */
    public static int getInt(String fileName,Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * 获取boolean 方法
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @return sp文件存储的数据
     */
    public static boolean getBoolean(String fileName,Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 获取Float 方法
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @return sp文件存储的数据
     */
    public static float getFloat(String fileName,Context context, String key, float defValue) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }


    /**
     * 获取long 方法
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @return sp文件存储的数据
     */
    public static long getLong(String fileName,Context context, String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     */
    public static void remove(String fileName,Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param context 操作的上下文
     */
    public static void clear(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @return true-要查询的key已存在，false-不存在
     */
    public static boolean contains(String fileName,Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context 操作的上下文
     * @return 返回sp文件所有的键值对应数据
     */
    public static Map<String, ?> getAll(String fileName,Context context) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getAll();
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context 操作的上下文
     * @param key     要获取的key
     * @param obj     任意数据类型
     */
    public static void put(String fileName,Context context, String key, Object obj) {

        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, obj.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context    操作的上下文
     * @param key        要获取的key
     * @param defaultObj 任意数据类型
     * @return 返回查询到的sp中存在的key对应的数据
     */
    public static Object get(String fileName,Context context, String key, Object defaultObj) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        }

        return null;
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return Method方法
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor 编辑器
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }

    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     * @param object
     */
    public static void putObject(String fileName,Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(String fileName,Context context, String key, Class<T> clazz) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    //--------------------------- Parcelable 转换工具------------------------------

    /**
     * Parcel 可以转化成 byte[]
     *
     * @param parceable
     * @return
     */
    public static byte[] marshall(Parcelable parceable) {
        Parcel parcel = Parcel.obtain();
        parcel.setDataPosition(0);
        parceable.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        //Log.d("ParcelableTest", "bytes = " + String.valueOf(bytes) + "parcel" + parcel.toString());
        parcel.recycle();
        return bytes;
    }

    /**
     * byte[] 转换成Parcel
     *
     * @param bytes
     * @return
     */
    public static Parcel unmarshall(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        return parcel;
    }
    //--------------------------- Parcelable 转换工具------------------------------

}
