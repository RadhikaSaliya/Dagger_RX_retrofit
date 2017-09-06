package com.user.canopas.dagger_rx_retrofit.mvp.View;


import com.user.canopas.dagger_rx_retrofit.mvp.Model.Cake;

import java.util.List;

public interface MainView{
    void onCakeLoaded(List<Cake> cakes);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
