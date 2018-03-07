package com.dileber.gold.alipaygold.goldmbp.setting;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;
import com.drcosu.ndileber.tools.rx.RxNetworkResponseObserver;

/**
 * Created by dileber
 */

public class SettingPresenter extends DileberRxPresenter<SettingContract.View, MainRepository> implements SettingContract.Presenter {

    public SettingPresenter(@NonNull SettingContract.View view, @NonNull MainRepository mDataSource) {
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

    @Override
    public void saveImage(int id) {
        mView.loading();
        add(mDataSource.saveImage(id).subscribe(new RxNetworkResponseObserver<Boolean>() {
            @Override
            public void onResponseFail(Exception e) {

            }

            @Override
            public void onBeforeResponseOperation() {
                mView.loadDialogDismiss();
            }

            @Override
            public void onResponse(Boolean aBoolean) {
                if(aBoolean){
                    mView.toast("保存成功", Toast.LENGTH_SHORT);
                }
            }
        }));

    }
}
