package com.example.mvpdb.ui;

import com.example.mvpdb.base.IBaseView;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/7/27 6:17 PM
 */
public interface LoginIView extends IBaseView {

    void login(String text);

    void logout(Login login);

    void getChannelList(List<ChannelBean> channelBeans);
}
