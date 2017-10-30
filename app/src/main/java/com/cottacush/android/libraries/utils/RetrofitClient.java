package com.cottacush.android.libraries.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Adegoke Obasa <goke@cottacush.com>
 */

public class RetrofitClient {
    public static final String OAUTH_SERVICE = "oauth_service";

    public Retrofit build() {
        //TODO Parametrize the base URL
        return new Retrofit.Builder()
                .baseUrl("")
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();

                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("access_token", "")
                                .build();

                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();
    }
}
