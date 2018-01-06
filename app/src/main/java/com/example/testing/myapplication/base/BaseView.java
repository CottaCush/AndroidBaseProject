package com.example.testing.myapplication.base;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;

public interface BaseView {

    void showLoading();

    void showLoading(@StringRes int resId);

    void showLoading(String message);

    void dismissLoading();

    void showError(@StringRes int resId);

    void showError(CharSequence message);

    void showToast(@StringRes int resId);

    void showToast(CharSequence message);

    void showSuccessMessage(@StringRes int resId, DialogInterface.OnClickListener positiveClickListener);

    void showSuccessMessage(String message, DialogInterface.OnClickListener positiveClickListener);

    void showSnackBarMessage(View view, String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void onUnknownError(String errorMessage);

    void onTimeout();

    void onNetworkError();
}
