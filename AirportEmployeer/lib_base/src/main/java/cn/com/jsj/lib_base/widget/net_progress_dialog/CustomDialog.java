/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.jsj.lib_base.widget.net_progress_dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import cn.com.jsj.lib_base.R;


public class CustomDialog extends Dialog {


    public OnHidListener onHidListener;

    private CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private static class CustomDialogHolder {
        static CustomDialog getHolderInstance(Context context) {
            final CustomDialog dialog = instance(context);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    //取消对话框的时候调一下隐藏的监听方法
                    if (dialog.onHidListener != null) {
                        dialog.onHidListener.onHid();
                        dialog.onHidListener.onCancel();
                    }
                }
            });
            return dialog;
        }
    }

    public static CustomDialog getInstance(Context context) {
        return CustomDialogHolder.getHolderInstance(context);
    }

    private static CustomDialog instance(Context activity) {
        CustomDialog mCustomDialog = new CustomDialog(activity, R.style.JSJ_dialog_transparent_theme);
        mCustomDialog.setContentView(R.layout.dialog_load_progress);
        ImageView imageView = mCustomDialog.findViewById(R.id.loadingImageView);
        try {
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView
                    .getBackground();
            animationDrawable.start();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return mCustomDialog;
    }

    public void setOnHidListener(OnHidListener onHidListener) {
        this.onHidListener = onHidListener;
    }

    public interface OnHidListener {
        void onHid();

        void onCancel();
    }

}
