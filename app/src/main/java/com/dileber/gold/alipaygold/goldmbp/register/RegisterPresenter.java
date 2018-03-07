package com.dileber.gold.alipaygold.goldmbp.register;

import android.support.annotation.NonNull;

import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;

/**
 * Created by dileber
 */

public class RegisterPresenter extends DileberRxPresenter<RegisterContract.View, MainRepository> implements RegisterContract.Presenter {

    public RegisterPresenter(@NonNull RegisterContract.View view, @NonNull MainRepository mDataSource) {
        super(view, mDataSource);
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
