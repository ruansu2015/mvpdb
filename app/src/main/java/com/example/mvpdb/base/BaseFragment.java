package com.example.mvpdb.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.mvpdb.R;
import com.example.mvpdb.databinding.LayoutBaseBinding;
import com.example.mvpdb.util.ToastUtil;

import java.util.List;

/**
 * Created by Ruansu
 * on 2020/7/28 12:03 PM
 */
public abstract class BaseFragment<B extends ViewDataBinding, P extends BasePresenter>
        extends Fragment implements IBaseView {

    protected B binding;
    protected P presenter;
    private LayoutBaseBinding baseBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseBinding = DataBindingUtil.inflate(inflater, R.layout.layout_base, container, true);
        if (setupLayoutId() != 0) binding = setDataBindingView(setupLayoutId());
        presenter = setupPresenter();
        return baseBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(baseBinding.title);
    }

    protected P setupPresenter() {
        return null;
    }

    protected abstract int setupLayoutId();

    public <T extends ViewDataBinding> T setDataBindingView(@LayoutRes int layoutId) {
        return DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                layoutId, baseBinding.baseContentLayout, true);
    }

    protected abstract void initView(TextView textView);

    public boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    @Override
    public void showToast(String message) {
        ToastUtil.show(message);
    }

    @Override
    public void showToast(int resId) {
        ToastUtil.show(resId);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
