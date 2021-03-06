package com.example.mvpdb.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mvpdb.R;
import com.example.mvpdb.databinding.LayoutBaseBinding;
import com.example.mvpdb.util.ToastUtil;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/7/27 4:13 PM
 */
public abstract class BaseActivity<B extends ViewDataBinding, P extends BasePresenter>
        extends AppCompatActivity implements IBaseView {

    protected B binding;
    protected P presenter;
    private LayoutBaseBinding baseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this, R.layout.layout_base);
        if (setupLayoutId() != 0) binding = setDataBindingView(setupLayoutId());
        presenter = setupPresenter();
        initView(baseBinding.title);
    }

    protected abstract int setupLayoutId();

    protected abstract P setupPresenter();

    protected abstract void initView(TextView titleView);

    public <T extends ViewDataBinding> T setDataBindingView(@LayoutRes int layoutId) {
        return DataBindingUtil.inflate(LayoutInflater.from(this),
                layoutId, baseBinding.baseContentLayout, true);
    }

    public String getTag() {
        return getClass().getSimpleName();
    }

    public boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public void showToast(String message) {
        ToastUtil.show(message);
    }

    public void showToast(@StringRes int resId) {
        ToastUtil.show(resId);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
