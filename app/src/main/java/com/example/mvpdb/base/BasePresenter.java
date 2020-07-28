package com.example.mvpdb.base;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ruansu
 * on 2020/7/27 4:10 PM
 */
public class BasePresenter<V extends IBaseView> {

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
    }

    public abstract class ResponseCallback<T> implements Callback<BaseModel<Object>> {

        protected abstract void onResult(T t);

        private void showToast(String message) {
            if (view != null) view.showToast(message);
        }

        @Override
        public void onResponse(@NotNull Call<BaseModel<Object>> call, Response<BaseModel<Object>> response) {
            if (response.isSuccessful()) {
                BaseModel<Object> model = response.body();
                if (model != null && model.isOk()) {
                    onResult((T) model.getResult());
                    return;
                }
            }
            showToast(response.message());
        }

        @Override
        public void onFailure(@NotNull Call<BaseModel<Object>> call, Throwable t) {
            showToast(t.getMessage());
        }
    }

}
