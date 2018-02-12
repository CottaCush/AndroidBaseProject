package com.cottacush.android.baseProject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.cottacush.android.libraries.base.BaseActivity;


/**
 * Created by codedentwickler on 10/6/17.
 */

public abstract class BackButtonFragment<T extends BasePresenter> extends BaseFragment<T> {
    com.cottacush.android.libraries.base.BaseActivity baseActivity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseActivity = ((BaseActivity) getActivity());
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null) {
            baseActivity.setDrawerIconToBack();
        }
    }
}
