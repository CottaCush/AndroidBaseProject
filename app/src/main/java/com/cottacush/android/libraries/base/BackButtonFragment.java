package com.cottacush.android.libraries.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;


/**
 * Created by codedentwickler on 10/6/17.
 */

public abstract class BackButtonFragment<T extends BasePresenter> extends BaseFragment<T> {

    BaseActivity baseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseActivity = ((BaseActivity) getActivity());
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null) {
            baseActivity.setDrawerIconToBack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            baseActivity.onBackPressed();
            return true;
        }
        return false;
    }
}
