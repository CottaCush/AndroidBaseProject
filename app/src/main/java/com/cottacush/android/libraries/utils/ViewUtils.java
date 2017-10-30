package com.cottacush.android.libraries.utils;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by codedentwickler on 10/5/17.
 */

public final class ViewUtils {

    private ViewUtils() {
    }

    public static void show(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hide(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    public static void invis(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void show(@NonNull View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void hide(@NonNull View view) {
        view.setVisibility(View.GONE);
    }

    public static void invis(@NonNull View view) {
        view.setVisibility(View.INVISIBLE);
    }
}
