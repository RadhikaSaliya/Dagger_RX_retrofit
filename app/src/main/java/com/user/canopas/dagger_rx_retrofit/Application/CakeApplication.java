package com.user.canopas.dagger_rx_retrofit.Application;


import android.app.Application;

import com.user.canopas.dagger_rx_retrofit.di.Component.ApplicationComponent;
import com.user.canopas.dagger_rx_retrofit.di.Component.DaggerApplicationComponent;
import com.user.canopas.dagger_rx_retrofit.di.Module.ApplicationModule;

public class CakeApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        InitApplicationComponent();
    }

    private void InitApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this, "https://gist.githubusercontent.com"))
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
