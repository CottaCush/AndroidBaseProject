package com.example.testing.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

public abstract class BackButtonFragment extends BaseFragment {

    BaseActivity baseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onBackPressed() {
        return false;
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

    protected void setIconBackToHome() {
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null) {
            baseActivity.setDrawerIconToHome();
        }
    }

}
