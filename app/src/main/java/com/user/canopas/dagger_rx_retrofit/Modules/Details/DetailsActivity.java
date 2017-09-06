package com.user.canopas.dagger_rx_retrofit.Modules.Details;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.user.canopas.dagger_rx_retrofit.BaseActivity;
import com.user.canopas.dagger_rx_retrofit.R;
import com.user.canopas.dagger_rx_retrofit.helper.ImageHandler;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.Cake;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity {

    public static String CAKE="cake";
    @Bind(R.id.cakeImage) protected ImageView mCakeImage;
    @Bind(R.id.cakeTitle) protected TextView mCakeTitle;
    @Bind(R.id.cakeDescription) protected TextView mCakeDescription;
    @Override
    protected int getContentView() {
        return R.layout.detail_activity;

    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCakeImage.setTransitionName("cakeImageAnimation");
        }



        Cake cake = (Cake) intent.getSerializableExtra(CAKE);
        setTitle("Cake Detail");

        mCakeTitle.setText(cake.getTitle());
        mCakeDescription.setText(cake.getDetailDescription());

        Glide.with(this).load(cake.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(mCakeImage));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
