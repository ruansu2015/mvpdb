package com.example.mvpdb;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.BuildConfig;
import com.blankj.utilcode.util.LogUtils;

/**
 * Created by Ruansu
 * on 2020/7/27 3:58 PM
 */
public class MdApplication extends Application {

    private static MdApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initLog();
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

    private void initLog() {
        LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
    }

}
