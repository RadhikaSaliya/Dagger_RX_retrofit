package com.user.canopas.dagger_rx_retrofit.mapper;


import com.user.canopas.dagger_rx_retrofit.mvp.Model.Cake;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.CakesResponse;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.CakesResponseCakes;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.Storage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CakeMapper
{
    @Inject
    public CakeMapper() {
    }
    public List<Cake> mapCakes(Storage storage, CakesResponse response) {
        List<Cake> cakeList = new ArrayList<>();

        if (response != null) {
            CakesResponseCakes[] responseCakes = response.getCakes();
            if (responseCakes != null) {
                for (CakesResponseCakes cake : responseCakes) {
                    Cake myCake = new Cake();
                    myCake.setId(cake.getId());
                    myCake.setTitle(cake.getTitle());
                    myCake.setDetailDescription(cake.getDetailDescription());
                    myCake.setPreviewDescription(cake.getPreviewDescription());
                    myCake.setImageUrl(cake.getImage());
                    storage.addCake(myCake);
                    cakeList.add(myCake);
                }
            }
        }
        return cakeList;
    }
}
