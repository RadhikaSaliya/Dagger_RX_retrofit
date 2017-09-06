package com.user.canopas.dagger_rx_retrofit.di.Component;


import android.content.Context;

import com.user.canopas.dagger_rx_retrofit.di.Module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = ApplicationModule.class)
@Singleton
public  interface ApplicationComponent {
    Retrofit expossRetrofit();
    Context exposeContext();

}
