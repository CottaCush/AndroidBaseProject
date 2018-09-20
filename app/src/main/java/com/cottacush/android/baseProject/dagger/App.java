package com.cottacush.android.baseProject.dagger;

import android.app.Application;

import com.cottacush.android.baseProject.dagger.modules.ApplicationModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //All Modules must be specified here.
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
