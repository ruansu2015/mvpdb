package com.example.mvpdb.base;

import com.example.mvpdb.util.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ruansu
 * on 2020/7/27 4:10 PM
 */
public class BasePresenter<V extends IBaseView> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    private void hideLoading() {
        if (view != null) view.hideLoading();
    }

    private void showToast(String message) {
        ToastUtil.show(message);
    }

    public abstract class ResultCallback<T> implements Observer<BaseModel<T>> {

        protected abstract void onResult(T t);

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(BaseModel<T> baseModel) {
            if (baseModel != null && baseModel.isOk()) {
                onResult(baseModel.getResult());
                hideLoading();
            } else
                onError(new Throwable(baseModel == null ? "Model is empty" : baseModel.getMessage()));
        }

        @Override
        public void onError(Throwable e) {
            hideLoading();
            ToastUtil.show(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

}
