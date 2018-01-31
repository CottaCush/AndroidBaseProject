package com.cottacush.android.baseProject.dagger;

import com.cottacush.android.baseProject.MainActivity;
import com.cottacush.android.baseProject.base.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by becca on 1/30/18.
 */

@Singleton
@Component (modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
