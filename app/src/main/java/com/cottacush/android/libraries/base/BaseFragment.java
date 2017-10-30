package com.cottacush.android.libraries.base;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


import com.cottacush.android.libraries.R;
import com.cottacush.android.libraries.utils.DateUtils;
import com.cottacush.android.libraries.utils.MessageUtils;
import com.cottacush.android.libraries.utils.NetworkUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView<T>{

    private Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    protected void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.view_container, fragment).addToBackStack(null).commit();
    }

    protected void setUpUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BaseActivity baseActivity = (BaseActivity)getActivity();
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null) {
            baseActivity.setDrawerIconToHome();
        }
        if (unbinder != null) unbinder.unbind();
    }

    protected void showDatePickerDialog(EditText editText) {
        DialogFragment newFragment = new DatePickerFragment(editText);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public void setToolbarTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
    }

    public void setNameAndColorOfToolbar(String title, ColorDrawable colorDrawable) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().
                setBackgroundDrawable(colorDrawable);
    }

    @Override
    public void showLoading() {
        ((BaseActivity)getActivity()).showLoading();
    }
    @Override
    public void dismissLoading() {
        ((BaseActivity)getActivity()).dismissLoading();
    }

    @Override
    public void showError(@StringRes int resId) {
        showError(getString(resId));
    }

    @Override
    public void showSnackBarMessage(View view, String message) {
        MessageUtils.showMessage(view,message);
    }

    @Override
    public void showError(String message) {
        ((BaseActivity)getActivity()).showError(message);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getActivity());
    }

    @Override
    public void hideKeyboard() {
        ((BaseActivity)getActivity()).hideKeyboard();
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

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private EditText editText;

        public DatePickerFragment() {
        }

        @SuppressLint("ValidFragment")
        public DatePickerFragment(EditText editText) {
            super();
            this.editText = editText;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            Date date = calendar.getTime();

            SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.FULL_DATE, Locale.UK);

            editText.setTag(date);
            editText.setText(formatter.format(date));
        }
    }
}
