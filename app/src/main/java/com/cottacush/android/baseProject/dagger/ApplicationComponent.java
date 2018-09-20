package com.cottacush.android.baseProject.dagger;


import com.cottacush.android.baseProject.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    // specify injection target(s) or expose downStream Components here.
}
