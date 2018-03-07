package com.dileber.gold.alipaygold.goldmbp.login;

import android.support.annotation.NonNull;

import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;

/**
 * Created by dileber
 */

public class LoginPresenter extends DileberRxPresenter<LoginContract.View, MainRepository> implements LoginContract.Presenter {

    public LoginPresenter(@NonNull LoginContract.View view, @NonNull MainRepository mDataSource) {
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
