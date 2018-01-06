package com.example.testing.myapplication.base;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cottacush.android.libraries.utils.MessageUtils;
import com.cottacush.android.libraries.utils.NetworkUtils;
import com.example.testing.myapplication.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements BaseView {


    protected OnBackPressedHandler backPressedHandler;
    private Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof BaseFragment.OnBackPressedHandler)) {
            throw new ClassCastException("Hosting activity must implement " +
                    "OnBackPressedHandler so fragments can be notified when back button is pressed");
        } else {
            backPressedHandler = (OnBackPressedHandler) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        backPressedHandler.setSelectedFragment(this);
    }

    public void replaceFragment(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.view_container, fragment, TAG)
                .addToBackStack(TAG)
                .commit();
    }

    protected void replaceFragmentWithoutHistory(Fragment fragment) {
        String TAG = fragment.getClass().getSimpleName();
        getActivity().getSupportFragmentManager().beginTransaction().
                add(R.id.view_container, fragment, TAG).commit();
    }

    protected void setUpUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BaseActivity baseActivity = (BaseActivity) getActivity();
        ActionBar actionBar = baseActivity.getSupportActionBar();
        if (actionBar != null) {
            baseActivity.setDrawerIconToHome();
        }
        hideKeyboard();
        if (unbinder != null) unbinder.unbind();
    }

    protected void showDatePickerDialog(DatePickerFragment.DatePickerCallback pickerCallback,
                                        boolean isfuture, Integer currentAge) {
        DialogFragment newFragment = new DatePickerFragment(pickerCallback, isfuture, currentAge);
        newFragment.show(getActivity().getSupportFragmentManager(), "pickerCallback");
    }

    public void setToolbarTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setElevation(0);
    }

    public void setToolbarTitle(@StringRes int title) {
        setToolbarTitle(getString(title));
    }

    public void setNameAndColorOfToolbar(String title, ColorDrawable colorDrawable) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().
                setBackgroundDrawable(colorDrawable);
    }

    @Override
    public void showLoading() {
        hideKeyboard();
        ((BaseActivity) getActivity()).showLoading();
    }

    @Override
    public void showLoading(int resId) {
        hideKeyboard();
        ((BaseActivity) getActivity()).showLoading(resId);
    }

    @Override
    public void showLoading(String message) {
        hideKeyboard();
        ((BaseActivity) getActivity()).showLoading(message);
    }

    @Override
    public void dismissLoading() {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        if (baseActivity != null) {
            baseActivity.dismissLoading();
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
        hideKeyboard();
        ((BaseActivity) getActivity()).showError(message);
    }

    protected void showAlertDialog(String title, String message, DialogInterface.OnClickListener
            clickListener) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_with_headers, null);
        builder.setPositiveButton(getString(R.string.close), clickListener);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        TextView bodyTextView = (TextView) view.findViewById(R.id.body);
        titleTextView.setText(title);
        bodyTextView.setText(message);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    protected void showAlertDialogWithTwoActions(String title, String body,
                                                 DialogInterface.OnClickListener positiveClickListener,
                                                 DialogInterface.OnClickListener negativeClickListener,
                                                 String positiveBtnText, String negativeBtnText) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_with_header_body_and_two_action_buttons, null);
        builder.setPositiveButton(positiveBtnText, positiveClickListener);
        builder.setNegativeButton(negativeBtnText, negativeClickListener);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        TextView titleTextView = (TextView) view.findViewById(R.id.title);
        TextView bodyTextView = (TextView) view.findViewById(R.id.body);
        titleTextView.setText(title);
        bodyTextView.setText(body);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void showToast(int resId) {
        ((BaseActivity) getActivity()).showToast(resId);
    }

    @Override
    public void showToast(CharSequence message) {
        ((BaseActivity) getActivity()).showToast(message);
    }

    @Override
    public void showSuccessMessage(@StringRes int resId, DialogInterface.OnClickListener
            positiveClickListener) {
        showSuccessMessage(getString(resId), positiveClickListener);
    }

    @Override
    public void showSuccessMessage(String message, DialogInterface.OnClickListener
            positiveClickListener) {
        ((BaseActivity) getActivity()).showSuccessMessage(message, positiveClickListener);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getActivity());
    }

    @Override
    public void hideKeyboard() {
        ((BaseActivity) getActivity()).hideKeyboard();
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

    /**
     * Convenience callback method implemented in attached activity to
     * provide the  unavailable onBackPress behaviour in fragments
     *
     * @return true if the action was consumed in the fragment
     */
    public boolean onBackPressed() {
        return false;
    }

    public interface OnBackPressedHandler {
        void setSelectedFragment(BaseFragment fragment);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private DatePickerCallback pickerCallback;
        private boolean isfuture;
        private Integer age;

        public DatePickerFragment() {
        }

        @SuppressLint("ValidFragment")
        public DatePickerFragment(DatePickerCallback callback, boolean isfuture, Integer age) {
            super();
            this.isfuture = isfuture;
            this.pickerCallback = callback;
            this.age = age;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(getActivity(), this, year, month, day);
            if (isfuture) {
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            } else {
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            }
            if (age != null) {
                final Calendar max = Calendar.getInstance();
                max.set(Calendar.YEAR, year - age);

                final Calendar min = Calendar.getInstance();
                min.set(Calendar.YEAR, year - age - 1);
                min.set(Calendar.DAY_OF_MONTH, min.get(Calendar.DAY_OF_MONTH) + 1);

                if (max.getActualMaximum(Calendar.DAY_OF_MONTH) == max.get(Calendar.DAY_OF_MONTH)) {
                    min.set(Calendar.MONTH, min.get(Calendar.MONTH) + 1);
                    min.set(Calendar.DAY_OF_MONTH, 1);
                }

                datePickerDialog.getDatePicker().setMinDate(min.getTimeInMillis());
                datePickerDialog.getDatePicker().setMaxDate(max.getTimeInMillis());
            }

            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            final Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            Date date = calendar.getTime();
            pickerCallback.OnDatePicked(date);
        }

        public interface DatePickerCallback {
            void OnDatePicked(Date date);
        }
    }
}
