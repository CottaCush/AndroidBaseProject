package com.cottacush.android.baseProject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
;
import com.cottacush.android.libraries.utils.HttpResponseUtils;
import com.cottacush.android.libraries.utils.MessageUtils;
import com.cottacush.android.libraries.utils.NetworkUtils;
import com.cottacush.android.libraries.utils.ViewUtils;

import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
        implements BaseView<T> {
    private Unbinder unbinder;
    private ProgressDialog dialog;
    private int timeOutErrResourceId;
    private int networkErrResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setDrawerIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    public AlertDialog showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    protected void handleRemoteCallFailure(Throwable t, ProgressDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
        showErrorDialog(HttpResponseUtils.ERROR_MESSAGE);
        t.printStackTrace();
    }

    public AlertDialog showAlertDialog(String title,
                                       String message,
                                       String positiveText,
                                       DialogInterface.OnClickListener positiveClickListener,
                                       String negativeText,
                                       DialogInterface.OnClickListener negativeClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveText, positiveClickListener)
                .setNegativeButton(negativeText, negativeClickListener);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public AlertDialog showSuccessDialog(String message, DialogInterface.OnClickListener
            positiveClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Ok", positiveClickListener);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void showError(@StringRes int resId) {
        showError(getString(resId));
    }

    @Override
    public void showSnackBarMessage(View view, String message) {
        MessageUtils.showMessage(view, message);
    }

    @Override
    public void showError(String message) {
        showErrorDialog(message);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onUnknownError(String errorMessage) {
        showError(errorMessage);
    }

    @Override
    public void onTimeout() {
        showError(timeOutErrResourceId);
    }

    @Override
    public void onNetworkError() {
        showError(networkErrResourceId);
    }

    protected void setUpUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    public void setDrawerIconToBack() {

    }

    public void setDrawerIconToHome() {

    }
        /*
    The below  methods can be called from any Activity
    that implements BaseView and extends from this Activity.
    The required arguments should be bound to the said Activity,
    and passed as arguments.
     */

    public void configureProgressViews(int progressMessage, TextView progressTextView,
                                       ConstraintLayout progressBarRoot) {
        configureProgressViews(getString(progressMessage), progressTextView, progressBarRoot);
    }

    public void configureProgressViews(String progressMessage, TextView progressTextView,
                                       ConstraintLayout progressBarRoot) {
        progressTextView.setText(progressMessage);
        if (progressBarRoot.getVisibility() != View.VISIBLE) {
            ViewUtils.show(progressBarRoot);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    public void disconfigureProgressViews(ConstraintLayout progressBarRoot) {
        ViewUtils.hide(progressBarRoot);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
