package com.example.mvpdb;

import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.blankj.utilcode.util.LogUtils;
import com.example.mvpdb.base.BaseActivity;
import com.example.mvpdb.databinding.ActivityMainBinding;
import com.example.mvpdb.ui.ChannelBean;
import com.example.mvpdb.ui.Login;
import com.example.mvpdb.ui.LoginIView;
import com.example.mvpdb.ui.LoginPresenter;

import java.util.List;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginIView {

    @Override
    protected void initView(TextView textView) {
        ActivityMainBinding binding = (ActivityMainBinding) setDataBindingView(R.layout.activity_main);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getChannelList();
    }

    @Override
    protected LoginPresenter setupPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void login(String text) {

    }

    @Override
    public void logout(Login login) {

    }

    @Override
    public void getChannelList(List<ChannelBean> channelBeans) {
        LogUtils.d(channelBeans);
    }
}