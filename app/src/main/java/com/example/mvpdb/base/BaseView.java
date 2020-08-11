package com.example.mvpdb.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Ruansu
 * on 2020/8/5 3:59 PM
 */
public abstract class BaseView<B extends ViewDataBinding> extends LinearLayout {

    protected B binding;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setViewFromAttr(attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setViewFromAttr(attrs);
    }

    private void init() {
        if (setupLayoutId() == 0) return;
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                setupLayoutId(), this, true);
        initView();
    }

    protected abstract int setupLayoutId();

    protected void initView() {
    }

    protected int[] setupStyleable() {
        return null;
    }

    private void setViewFromAttr(AttributeSet attrs) {
        if (setupStyleable() == null) return;
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, setupStyleable());
        setViewFromTypedArray(typedArray);
        typedArray.recycle();
    }

    protected void setViewFromTypedArray(TypedArray typedArray) {
    }

}
