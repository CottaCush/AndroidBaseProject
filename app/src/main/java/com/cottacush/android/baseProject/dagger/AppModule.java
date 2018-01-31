package com.cottacush.android.baseProject.dagger;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by becca on 1/30/18.
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    EditText provideEditText() {
        return new EditText(mContext);
    }

    @Provides
    @Singleton
    Fragment provideFragment() {
        return new Fragment();
    }

    @Provides
    @Singleton
    ProgressDialog provideDialog() {
        return new ProgressDialog(mContext);
    }

    @Provides
    @Singleton
    View provideView() {
        return new View(mContext);
    }
}
