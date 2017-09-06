package com.user.canopas.dagger_rx_retrofit.mvp.Presenter;


import com.user.canopas.dagger_rx_retrofit.Api.CakeApiService;
import com.user.canopas.dagger_rx_retrofit.mapper.CakeMapper;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.Cake;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.CakesResponse;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.Storage;
import com.user.canopas.dagger_rx_retrofit.mvp.View.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

public class CakePresenter extends BasePresenter<MainView> implements Observer<CakesResponse> {
    @Inject
    protected CakeApiService mApiService;
    @Inject protected CakeMapper mCakeMapper;
    @Inject protected Storage mStorage;
    @Inject
    public CakePresenter() {
    }

    public void getCakes() {
        getView().onShowDialog("Loading cakes....");
        Observable<CakesResponse> cakesResponseObservable = mApiService.getCakes();
        subscribe(cakesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Cakes loading complete!");

    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading cakes " + e.getMessage());

    }

    @Override
    public void onNext(CakesResponse cakesResponse) {
        List<Cake> cakes = mCakeMapper.mapCakes(mStorage, cakesResponse);
        getView().onClearItems();
        getView().onCakeLoaded(cakes);
    }
    public void getCakesFromDatabase() {
        List<Cake> cakes = mStorage.getSavedCakes();
        getView().onClearItems();
        getView().onCakeLoaded(cakes);
    }
}
