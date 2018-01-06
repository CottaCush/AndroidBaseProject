package com.example.testing.myapplication.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cottacush.android.libraries.utils.MessageUtils;
import com.cottacush.android.libraries.utils.NetworkUtils;
import com.cottacush.android.libraries.utils.PrefsUtils;
import com.example.testing.myapplication.R;
import com.example.testing.myapplication.data.model.Token;
import com.example.testing.myapplication.data.remote.OAUTHService;
import com.example.testing.myapplication.utils.Constants;
import com.example.testing.myapplication.utils.JsendResponse;
import com.example.testing.myapplication.utils.Provider;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity implements BaseView,
        BaseFragment.OnBackPressedHandler {

    protected PrefsUtils prefsUtils;
    private ProgressDialog dialog;
    protected BaseFragment attachedFragment;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefsUtils = Provider.providePrefManager(this);
    }

    public AlertDialog showErrorDialog(CharSequence message) {
        dismissLoading();
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

    @Override
    public void showToast(int resId) {
        dismissLoading();
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(CharSequence message) {
        dismissLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showLoading(null);
    }

    @Override
    public void showLoading(int resId) {
        showLoading(getString(resId));
    }

    @Override
    public void showLoading(String message) {
        if (message == null) {
            message = getString(R.string.please_wait);
        }
        if (dialog == null) {
            dialog = new ProgressDialog(this);
        }
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void dismissLoading() {
        dialog.dismiss();
    }


    protected void doOAUTH2Authentication() {
        if (!isNetworkConnected()) {
            onNetworkError();
            return;
        }
        showLoading(R.string.authenticating_user);
        OAUTHService oauthService = Provider.provideRetrofitInstanceWithoutHeadersForOAUTH(
                Provider.providePrefManager(this)).create(OAUTHService.class);
        oauthService.getAccessToken(Constants.getOauthCredentialsMap()).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsendResponse jsendResponse = new JsendResponse(response.body(),
                        response.errorBody());

                if (!jsendResponse.isSuccess()) {
                    showError(R.string.authenticating_failed);
                    return;
                }
                if (prefsUtils.doesContain(Constants.PREF_KEY_ACCESS_TOKEN)) {
                    prefsUtils.remove(Constants.PREF_KEY_ACCESS_TOKEN);
                    prefsUtils.remove(Constants.PREF_KEY_TOKEN_EXPIRES);
                }
                Token token = new Gson().fromJson(jsendResponse.getDataAsObject(), Token.class);
                prefsUtils.putString(Constants.PREF_KEY_ACCESS_TOKEN, token.getAccessToken());
                long expiresIn = (System.currentTimeMillis() / 1000) + token.getExpiresIn();
                prefsUtils.putLong(Constants.PREF_KEY_TOKEN_EXPIRES, expiresIn);
                dismissLoading();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dismissLoading();
                showErrorDialog(JsendResponse.ERROR_MESSAGE);
                t.printStackTrace();
            }
        });
    }

    public AlertDialog showSuccessDialog(String message, DialogInterface.OnClickListener
            positiveClickListener) {
        dismissLoading();
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
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onBackPressed() {
        if (attachedFragment == null || !attachedFragment.onBackPressed()) {
            // Attached fragment did not consume the back press event.
            super.onBackPressed();
            hideKeyboard();
        }
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
    public void showError(CharSequence message) {
        showErrorDialog(message);
    }

    @Override
    public void showSuccessMessage(@StringRes int resId, DialogInterface.OnClickListener
            positiveClickListener) {
        showSuccessDialog(getString(resId), positiveClickListener);
    }

    @Override
    public void showSuccessMessage(String message, DialogInterface.OnClickListener
            positiveClickListener) {
        showSuccessDialog(message, positiveClickListener);
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
        showError(R.string.time_out_err_msg);
    }

    @Override
    public void onNetworkError() {
        showError(R.string.network_err);
    }

    protected void setUpUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    public abstract void setDrawerIconToBack();

    public abstract void setDrawerIconToHome();

    @Override
    public void setSelectedFragment(BaseFragment fragment) {
        this.attachedFragment = fragment;
    }
}
