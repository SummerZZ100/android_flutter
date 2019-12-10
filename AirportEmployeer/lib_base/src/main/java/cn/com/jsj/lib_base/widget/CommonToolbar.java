package cn.com.jsj.lib_base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import cn.com.jsj.lib_base.R;


/**
 * 自定义的Toolbar
 *
 * @author : wangjing
 * @date : 2018/12/18
 */
public class CommonToolbar extends Toolbar {

    public OnLeftIconClickListener mOnLeftIconClickListener;
//    private TextView mRightTextView;

    public interface OnLeftIconClickListener {
        void onLeftIconClick();
    }

    OnLeftCloseTextClickListener mOnLeftCloseTextClickListener;

    public interface OnLeftCloseTextClickListener {
        void onLeftCloseTextClick();
    }

//    OnRightViewClickListener mOnRightViewClickListener;
//
//    public interface OnRightViewClickListener {
//        void onRightViewClick();
//    }


    private RelativeLayout mRlToolBarRootView;
    private FrameLayout mFlToolBarLeftIcon;
    private ImageView mToolBarLeftIcon;
    private TextView mBarLeftCloseText;
    public TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private int mToolbarLeftIconImageAttr;
    private String mToolbarLeftCloseTextStrAttr;
    private float mToolbarLeftCloseTextSizeAttr;
    private int mToolbarLeftCloseTextColorAttr;
    private boolean mToolbarLeftCloseTextShowTagAttr;
    private String mToolbarTitleTextStrAttr;
    private float mToolbarTitleTextSizeAttr;
    private int mToolbarTitleTextColorAttr;
    private String mToolbarSubTitleTextStrAttr;
    private float mToolbarSubTitleTextSizeAttr;
    private int mToolbarSubTitleTextColorAttr;
    private boolean mToolbarSubTitleTextShowTagAttr;
    private boolean mToolbarOnlyShowTitleTag;


    private float mDefaultRightTextSize = sp2px(getContext(), 15);
    private int mDefaultRightTextColor = getContext().getResources().getColor(R.color.common_app_gray_333333);
    private int mLeftIcon;
    private float mCloseTextSize;
    private int mCloseTextColor;
    private boolean mCloseTextShowTag;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private int mSubTitleTextColor;
    private float mSubTitleTextSize;

    public CommonToolbar(Context context) {
        super(context);
    }

    public CommonToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }


    public CommonToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        inflate(context, R.layout.common_tool_bar_layout, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonToolbar);
        mLeftIcon = R.mipmap.ic_back_black;
        mToolbarLeftIconImageAttr = typedArray.getResourceId(R.styleable.CommonToolbar_toolbarLeftIconImage, mLeftIcon);

        mToolbarLeftCloseTextStrAttr = typedArray.getString(R.styleable.CommonToolbar_toolbarLeftCloseTextStr);
        //默认关闭字体大小
        mCloseTextSize = sp2px(getContext(), 10);
        mToolbarLeftCloseTextSizeAttr = typedArray.getDimension(R.styleable.CommonToolbar_toolbarLeftCloseTextSize, mCloseTextSize);
        //默认关闭字体颜色
        mCloseTextColor = context.getResources().getColor(R.color.common_app_blue_009ff0);
        mToolbarLeftCloseTextColorAttr = typedArray.getColor(R.styleable.CommonToolbar_toolbarLeftCloseTextColor, mCloseTextColor);
        //默认关闭Text是否显示
        mCloseTextShowTag = false;
        mToolbarLeftCloseTextShowTagAttr = typedArray.getBoolean(R.styleable.CommonToolbar_toolbarLeftCloseTextShowTag, mCloseTextShowTag);

        //主标题 attr初始化
        mToolbarTitleTextStrAttr = typedArray.getString(R.styleable.CommonToolbar_toolbarTitleTextStr);

        mTitleTextSize = sp2px(getContext(), 18);
        mToolbarTitleTextSizeAttr = typedArray.getDimension(R.styleable.CommonToolbar_toolbarTitleTextSize, mTitleTextSize);
        mTitleTextColor = context.getResources().getColor(R.color.common_app_gray_333333);
        mToolbarTitleTextColorAttr = typedArray.getColor(R.styleable.CommonToolbar_toolbarTitleTextColor, mTitleTextColor);

        mToolbarSubTitleTextStrAttr = typedArray.getString(R.styleable.CommonToolbar_toolbarSubTitleTextStr);
        mSubTitleTextSize = sp2px(getContext(), 14);
        mToolbarSubTitleTextSizeAttr = typedArray.getDimension(R.styleable.CommonToolbar_toolbarSubTitleTextSize, mSubTitleTextSize);
        mSubTitleTextColor = context.getResources().getColor(R.color.common_app_gray_666666);
        mToolbarSubTitleTextColorAttr = typedArray.getColor(R.styleable.CommonToolbar_toolbarSubTitleTextColor, mSubTitleTextColor);
        mToolbarSubTitleTextShowTagAttr = typedArray.getBoolean(R.styleable.CommonToolbar_toolbarSubTitleTextShowTag, false);
        mToolbarOnlyShowTitleTag = typedArray.getBoolean(R.styleable.CommonToolbar_toolbarOnlyShowTitleTag, false);
        typedArray.recycle();

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRlToolBarRootView = findViewById(R.id.rl_tool_bar_root_view);
        mFlToolBarLeftIcon = findViewById(R.id.fl_tool_bar_left_icon);
        mToolBarLeftIcon = findViewById(R.id.iv_tool_bar_left_icon);
        mBarLeftCloseText = findViewById(R.id.tv_tool_bar_left_close_text);
        mToolbarTitle = findViewById(R.id.toolbar_title);
        mToolbarSubTitle = findViewById(R.id.toolbar_sub_title);
        initViewWithAttrs();
        initListener();
    }

    private void initListener() {
        mFlToolBarLeftIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLeftIconClickListener != null) {
                    mOnLeftIconClickListener.onLeftIconClick();
                }
            }
        });

        mBarLeftCloseText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLeftCloseTextClickListener != null) {
                    mOnLeftCloseTextClickListener.onLeftCloseTextClick();
                }
            }
        });

    }

    private void initViewWithAttrs() {
        mToolBarLeftIcon.setBackgroundResource(mToolbarLeftIconImageAttr);
        mBarLeftCloseText.setTextColor(mToolbarLeftCloseTextColorAttr);
        mBarLeftCloseText.setText(mToolbarLeftCloseTextStrAttr);
        mBarLeftCloseText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mToolbarLeftCloseTextSizeAttr);
        mToolbarTitle.setText(mToolbarTitleTextStrAttr);
        mToolbarTitle.setTextColor(mToolbarTitleTextColorAttr);
        mToolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mToolbarTitleTextSizeAttr);
        mToolbarSubTitle.setText(mToolbarSubTitleTextStrAttr);
        mToolbarSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mToolbarSubTitleTextSizeAttr);
        mToolbarSubTitle.setTextColor(mToolbarSubTitleTextColorAttr);

        if (mToolbarSubTitleTextShowTagAttr) {
            mToolbarSubTitle.setVisibility(VISIBLE);
        } else {
            mToolbarSubTitle.setVisibility(GONE);
        }
        if (mToolbarLeftCloseTextShowTagAttr) {
            mBarLeftCloseText.setVisibility(VISIBLE);
        } else {
            mBarLeftCloseText.setVisibility(GONE);
        }

        if (mToolbarOnlyShowTitleTag) {
            mToolBarLeftIcon.setVisibility(INVISIBLE);
            mBarLeftCloseText.setVisibility(INVISIBLE);
            mToolbarTitle.setVisibility(VISIBLE);
            mToolbarSubTitle.setVisibility(GONE);
        }
    }

    // -----------设置关闭图标-----------//

    public void setLeftIconBackgroundResource(@DrawableRes int resid) {
        mToolBarLeftIcon.setBackgroundResource(resid);
    }

    /**
     * 设置返回图标是否可见
     * @param show true 可见 false 不可见
     */
    public void setLeftIconVisibility(boolean show) {
        mToolBarLeftIcon.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }


    // -----------设置关闭文字相关-----------//

    public void setCloseTextString(String closeText) {
        mBarLeftCloseText.setText(closeText);
    }

    public void setCloseTextSize(float textSize) {
        mBarLeftCloseText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setCloseTextColor(int color) {
        mBarLeftCloseText.setTextColor(color);

    }

    public void setCloseTextIsShow(boolean isShow) {
        if (isShow) {
            mBarLeftCloseText.setVisibility(VISIBLE);
        } else {
            mBarLeftCloseText.setVisibility(INVISIBLE);
        }
    }


    // -----------设置主标题相关-----------//

    public void setMainTitleText(String closeText) {
        mToolbarTitle.setText(closeText);
    }

    public void setMainTitleTextColor(int color) {
        mToolbarTitle.setTextColor(color);
    }

    public void setMainTitleTextSize(float textSize) {
        mToolbarTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setMainTitleOnlyShow(boolean isOnlyShow) {
        if (isOnlyShow) {
            mToolBarLeftIcon.setVisibility(INVISIBLE);
            mBarLeftCloseText.setVisibility(INVISIBLE);
            mToolbarTitle.setVisibility(VISIBLE);
            mToolbarSubTitle.setVisibility(GONE);
        }
    }


    // -----------设置副标题相关-----------//

    public void setSubTitleText(String closeText) {
        mToolbarSubTitle.setText(closeText);
    }

    public void setSubTitleTextColor(int color) {
        mToolbarSubTitle.setTextColor(color);
    }

    public void setSubTextSize(float textSize) {
        mToolbarSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setSubTitIsShow(boolean isShow) {
        if (isShow) {
            mToolbarSubTitle.setVisibility(VISIBLE);
        } else {
            mToolbarSubTitle.setVisibility(GONE);
        }
    }


    /**
     * 设置关闭按钮监听
     *
     * @param onLeftIconClickListener
     */
    public void setOnLeftIconClickListener(OnLeftIconClickListener onLeftIconClickListener) {
        mOnLeftIconClickListener = onLeftIconClickListener;
    }
//
//    public void setOnRightViewClickListener(OnRightViewClickListener onRightViewClickListener) {
//        mOnRightViewClickListener = onRightViewClickListener;
//    }

    /**
     * 设置关闭文字按钮监听
     *
     * @param onLeftCloseTextClickListener
     */
    public void setOnLeftCloseTextClickListener(OnLeftCloseTextClickListener onLeftCloseTextClickListener) {
        mOnLeftCloseTextClickListener = onLeftCloseTextClickListener;
    }

    /**
     * 添加默认 15sp common_app_gray_333333 黑色的 右侧TextView
     *
     * @param str 文字
     */
    public TextView addRightTextView(String str) {
        return addRightTextView(str, mDefaultRightTextSize, mDefaultRightTextColor, 15f);
    }

    /**
     * 添加默认 15sp 指定颜色的 右侧TextView
     *
     * @param str       文字
     * @param textColor 颜色
     */
    public TextView addRightTextView(String str, int textColor) {
        return addRightTextView(str, mDefaultRightTextSize, textColor, 15f);
    }

    /**
     * 添加右侧TextView 指定字体颜色、大小
     *
     * @param str       文字
     * @param textSize  字体大小
     * @param textColor 颜色
     */
    public TextView addRightTextView(String str, float textSize, int textColor, float marginRight) {
        return addRightTextView(str, textSize, textColor, 0, marginRight);
    }

    /**
     * 添加右侧TextView 指定字体颜色、大小 及 左侧图标
     *
     * @param str       文字
     * @param textSize  字体大小
     * @param textColor 颜色
     * @param drawId    图标id
     */
    public TextView addRightTextView(String str, float textSize, int textColor, int drawId, float marginRight) {
        TextView mRightTextView = new TextView(getContext());
        mRightTextView.setText(str);
        mRightTextView.setTextColor(textColor);
        mRightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.rightMargin = dp2px(getContext(), marginRight);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightTextView.setLayoutParams(layoutParams);
        mRightTextView.setGravity(Gravity.CENTER_VERTICAL);
        if (drawId != 0) {
            Drawable drawable = getResources().getDrawable(drawId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mRightTextView.setCompoundDrawables(drawable, null, null, null);
            mRightTextView.setPadding(10, 0, 0, 0);
        }

//        mRightTextView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnRightViewClickListener != null) {
//                    mOnRightViewClickListener.onRightViewClick();
//                }
//            }
//        });

        mRlToolBarRootView.addView(mRightTextView);
        return mRightTextView;
    }

    public ImageView addRightImageIcon(int res) {
        return addRightImageIcon(res, 15f);
    }

    /**
     * 动态添加右侧图标
     *
     * @param res resID
     */
    public ImageView addRightImageIcon(int res, float marginRight) {
        ImageView mRightImageView = new ImageView(getContext());
        mRightImageView.setImageResource(res);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.rightMargin = dp2px(getContext(), marginRight);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        mRightImageView.setLayoutParams(layoutParams);
//        mRightImageView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnRightViewClickListener != null) {
//                    mOnRightViewClickListener.onRightViewClick();
//                }
//            }
//        });
        mRlToolBarRootView.addView(mRightImageView);
        return mRightImageView;
    }

    /**
     * 将Toolbar设置为自定义的View
     *
     * @param rootView
     */
    public void setCustomRootView(View rootView) {
        mRlToolBarRootView.removeAllViews();
        mRlToolBarRootView.addView(rootView);
    }

    /**
     * 设置右边动态添加的View是否显示
     *
     * @param isShow true ：VISIBLE  false ：INVISIBLE
     */
    private void setRightViewVisibility(boolean isShow) {
//        if (mRightTextView != null) {
//            mRightTextView.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
//        } else if (mRightImageView != null) {
//            mRightImageView.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
//        }
    }


    /**
     * SP  转 px
     */
    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }


    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        int result = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
        return result;
    }
}
