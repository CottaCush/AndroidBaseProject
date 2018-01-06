package com.example.testing.myapplication.utils;

import android.content.Context;

import com.cottacush.android.libraries.utils.PrefsUtils;

import retrofit2.Retrofit;

/**
 * Convenience class to instantiate single instance of repositories to be used
 * throughout the app and to make it easy to swap concrete implementations
 * of repositories in case a local storage will be used
 */

public final class Provider {

    private static PrefsUtils prefsUtils;
    private static Retrofit retrofitInstance;
    private Provider() {}

    public static PrefsUtils providePrefManager(Context context) {
        if (prefsUtils == null) {
            prefsUtils = new PrefsUtils(context.getApplicationContext());
        }
        return prefsUtils;
    }

    public static Retrofit provideRetrofitInstance(PrefsUtils prefsUtils) {
        if (retrofitInstance == null) {
            retrofitInstance = new RetrofitClient().build(prefsUtils);
        }
        return retrofitInstance;
    }

    public static <T> T provideRetrofitService(final Retrofit retrofit, Class<T> clazz) {
        return retrofit.create(clazz);
    }

    public static Retrofit provideRetrofitInstanceWithoutHeadersForOAUTH(PrefsUtils prefsUtils) {
        return new RetrofitClient().build(prefsUtils);
    }

}
