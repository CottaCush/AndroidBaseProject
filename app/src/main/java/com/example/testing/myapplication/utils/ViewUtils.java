package com.example.testing.myapplication.utils;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by codedentwickler on 10/5/17.
 */

public final class ViewUtils {

    private ViewUtils() {
    }

    /**
     * @param views
     */
    public static void show(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     * @param views
     */
    public static void hide(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * @param views
     */
    public static void invisible(@NonNull View... views) {
        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * @param view
     */
    public static void show(@NonNull View view) {
        view.setVisibility(View.VISIBLE);
    }

    /**
     * @param view
     */
    public static void hide(@NonNull View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * @param view
     */
    public static void invis(@NonNull View view) {
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * @param dp
     * @return
     */
    public static int convertDpToPixels(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    /**
     * @param gridView
     * @param arrayList
     */
    public static void adjustGridViewSizeBasedOnData(GridView gridView, ArrayList arrayList) {
        int size = arrayList.size();
        int rowHeight = (size % 2 == 0) ? size / 2 : (size + 1) / 2;
        int height = ViewUtils.convertDpToPixels(rowHeight * 36);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = height;
        gridView.setLayoutParams(layoutParams);
        gridView.requestLayout();
    }
}
