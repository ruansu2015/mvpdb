package com.example.mvpdb.base;

import androidx.annotation.StringRes;

/**
 * Created by Ruansu
 * on 2020/7/27 4:06 PM
 */
public interface IBaseView {

    void showToast(String message);

    void showToast(@StringRes int resId);

    void showLoading();

    void hideLoading();

}
