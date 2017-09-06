package com.user.canopas.dagger_rx_retrofit.di.Module;


import com.user.canopas.dagger_rx_retrofit.Api.CakeApiService;
import com.user.canopas.dagger_rx_retrofit.di.Scope.PerActivity;
import com.user.canopas.dagger_rx_retrofit.mvp.View.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class CakeModule {

    private MainView mView;

    public CakeModule(MainView view) {
        mView = view;
    }

    @PerActivity
    @Provides
    CakeApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(CakeApiService.class);
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }

}
