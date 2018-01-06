package com.example.testing.myapplication.data.remote;


import com.example.testing.myapplication.R;
import com.example.testing.myapplication.base.BaseView;
import com.example.testing.myapplication.utils.JsendResponse;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Created by codedentwickler on 10/11/17.
 */

/**
 * Convenience Wrapper Class for Retrofit callback to handle common error
 * at once to avoid duplicate code
 *
 * @param <T>
 */

public abstract class CallbackWrapper<T> implements Callback<T> {

    private BaseView view;

    protected CallbackWrapper(BaseView baseView) {
        this.view = baseView;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.errorBody() != null) {
            JSONObject errorBody;
            try {
                errorBody = new JSONObject(response.errorBody().string());
                view.onUnknownError(errorBody.getString("message").replaceAll("_", " "));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        if (t instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) t).response().errorBody();
            view.onUnknownError(getErrorMessage(responseBody));
        } else if (t instanceof EOFException) {
            view.showError(R.string.api_response_error);
        } else if (t instanceof SocketTimeoutException || t instanceof UnknownHostException) {
            view.onTimeout();
        }else if (t instanceof MalformedJsonException) {
            // This will be handle by the caller in on Failure
        }
         else {
            view.onUnknownError(JsendResponse.ERROR_MESSAGE);
        }
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message").replaceAll("_", " ");
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
