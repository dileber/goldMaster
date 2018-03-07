package com.dileber.gold.alipaygold;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;
import com.drcosu.ndileber.mvp.presenter.NormalRxPresenter;

/**
 * Created by hyy on 2018/3/7.
 */
public class MainPresenter extends NormalRxPresenter<MainContract.View> implements MainContract.Presenter {


    public MainPresenter(@NonNull MainContract.View view) {
        super(view);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
