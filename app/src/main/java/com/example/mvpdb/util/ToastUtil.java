package com.example.mvpdb.util;

import android.view.Gravity;

import androidx.annotation.StringRes;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

public class ToastUtil {

    public static void show(String msg) {
        show(msg, false);
    }

    public static void show(@StringRes int resId) {
        show(StringUtils.getString(resId));
    }

    public static void showLong(String msg) {
        show(msg, true);
    }

    public static void showLong(@StringRes int resId) {
        showLong(StringUtils.getString(resId));
    }

    private static void show(String msg, boolean isLong) {
        if (StringUtils.isTrimEmpty(msg)) return;
        ToastUtils.setMsgTextSize(14);
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.setBgColor(ColorUtils.getColor(android.R.color.darker_gray));
        ToastUtils.setMsgColor(ColorUtils.getColor(android.R.color.white));
        if (isLong)
            ToastUtils.showLong(msg);
        else
            ToastUtils.showShort(msg);
    }

}
