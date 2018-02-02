package com.dileber.gold.alipaygold.goldmbp.user;

import android.support.annotation.NonNull;

import com.drcosu.ndileber.mvp.presenter.NormalRxPresenter;

/**
 * Created by dileber
 */

public class UserPresenter extends NormalRxPresenter<UserContract.View> implements UserContract.Presenter {

    public UserPresenter(@NonNull UserContract.View view) {
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
