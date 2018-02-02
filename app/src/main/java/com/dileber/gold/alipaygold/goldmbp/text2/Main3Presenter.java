package com.dileber.gold.alipaygold.goldmbp.text2;

import android.support.annotation.NonNull;

import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;

/**
 * Created by dileber
 */

public class Main3Presenter extends DileberPresenter<Main3Contract.View, MainRepository> implements Main3Contract.Presenter {

    public Main3Presenter(@NonNull Main3Contract.View view, @NonNull MainRepository mDataSource) {
        super(view, mDataSource);
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
