package com.example.mvpdb.ui;

import com.example.mvpdb.base.BasePresenter;
import com.example.mvpdb.http.HttpManager;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/7/27 6:16 PM
 */
public class LoginPresenter extends BasePresenter<LoginIView> {

    public LoginPresenter(LoginIView view) {
        super(view);
    }

    public void getChannelList() {
        HttpManager.getChannelList(new ResponseCallback<List<ChannelBean>>() {

            @Override
            protected void onResult(List<ChannelBean> channelBeans) {
                view.getChannelList(channelBeans);
            }
        });
    }
}
