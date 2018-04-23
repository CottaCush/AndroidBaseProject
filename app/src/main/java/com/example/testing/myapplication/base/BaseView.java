package com.example.testing.myapplication.base;

import android.support.annotation.StringRes;
import android.view.View;


/**
 * Created by Jubril on 9/19/17.
 */

public interface BaseView<T extends BasePresenter> {

    void showLoading();

    void showLoading(@StringRes int progressMessage);

    void showLoading(String progressMessage);

    void dismissLoading();

    void showError(@StringRes int resId);

    void showSnackBarMessage(View view, String message);

    void showError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void onUnknownError(String errorMessage);

    void onTimeout();

    void onNetworkError();


}
