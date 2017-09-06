package com.user.canopas.dagger_rx_retrofit.Modules.home;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.user.canopas.dagger_rx_retrofit.BaseActivity;
import com.user.canopas.dagger_rx_retrofit.Modules.Details.DetailsActivity;
import com.user.canopas.dagger_rx_retrofit.R;
import com.user.canopas.dagger_rx_retrofit.di.Component.ApplicationComponent;
import com.user.canopas.dagger_rx_retrofit.di.Component.DaggerApplicationComponent;
import com.user.canopas.dagger_rx_retrofit.di.Component.DaggerCakeComponent;
import com.user.canopas.dagger_rx_retrofit.di.Module.ApplicationModule;
import com.user.canopas.dagger_rx_retrofit.di.Module.CakeModule;
import com.user.canopas.dagger_rx_retrofit.mvp.Model.Cake;
import com.user.canopas.dagger_rx_retrofit.mvp.Presenter.CakePresenter;
import com.user.canopas.dagger_rx_retrofit.mvp.View.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView{
    @Bind(R.id.cake_list) protected RecyclerView mCakeList;

    @Inject
    protected CakePresenter mPresenter;
    private CakeAdapter mCakeAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        ButterKnife.bind(this);
        initializeList();
        loadCakes();
    }

    private void loadCakes() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){

                mPresenter.getCakes();
            } else {
                mPresenter.getCakesFromDatabase();
            }

    }
    private void initializeList() {

        mCakeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCakeAdapter = new CakeAdapter(getLayoutInflater());
        mCakeAdapter.setCakeClickListener(mCakeClickListener);
        mCakeList.setAdapter(mCakeAdapter);
    }
    private CakeAdapter.OnCakeClickListener mCakeClickListener = new CakeAdapter.OnCakeClickListener() {
        @Override
        public void onClick(View v, Cake cake, int position) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.CAKE, cake);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "cakeImageAnimation");
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    };
    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void onCakeLoaded(List<Cake> cakes) {
        mCakeAdapter.addCakes(cakes);

    }
    @Override
    public void resolveDaggerDependency() {
      DaggerCakeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .cakeModule(new CakeModule(this))
                .build().inject(this);


    }
    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        mCakeAdapter.clearCakes();
    }
}
