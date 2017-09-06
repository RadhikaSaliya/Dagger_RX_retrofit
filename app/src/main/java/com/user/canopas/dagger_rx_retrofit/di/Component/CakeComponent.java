package com.user.canopas.dagger_rx_retrofit.di.Component;

import com.user.canopas.dagger_rx_retrofit.Api.CakeApiService;
import com.user.canopas.dagger_rx_retrofit.Modules.home.MainActivity;
import com.user.canopas.dagger_rx_retrofit.di.Module.CakeModule;
import com.user.canopas.dagger_rx_retrofit.di.Scope.PerActivity;

import dagger.Component;
import dagger.Provides;
import retrofit2.Retrofit;

@PerActivity
@Component(modules = CakeModule.class,dependencies = ApplicationComponent.class)
public interface   CakeComponent {

    void inject(MainActivity mainActivity);
}
