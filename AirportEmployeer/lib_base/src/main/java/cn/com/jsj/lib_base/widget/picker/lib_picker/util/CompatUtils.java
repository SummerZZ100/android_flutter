package cn.com.jsj.lib_base.widget.picker.lib_picker.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * 兼容旧版&新版
 *
 * @author 李玉江[QQ :1032694760]
 * @since 2015 /10/19 Created By Android Studio
 */
public class CompatUtils {

    /**
     * Sets background.
     *
     * @param view     the view
     * @param drawable the drawable
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 16) {
            //noinspection deprecation
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    /**
     * Gets drawable.
     *
     * @param context     the context
     * @param drawableRes the drawable res
     * @return the drawable
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(Context context, @DrawableRes int drawableRes) {
        if (Build.VERSION.SDK_INT < 21) {
            //noinspection deprecation
            return context.getResources().getDrawable(drawableRes);
        } else {
            return context.getDrawable(drawableRes);
        }
    }

    /**
     * Gets string.
     *
     * @param context   the context
     * @param stringRes the string res
     * @return the string
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static String getString(Context context, @StringRes int stringRes) {
        if (Build.VERSION.SDK_INT < 21) {
            //noinspection deprecation
            return context.getResources().getString(stringRes);
        } else {
            return context.getString(stringRes);
        }
    }


}
