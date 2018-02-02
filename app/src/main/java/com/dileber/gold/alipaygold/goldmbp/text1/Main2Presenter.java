package com.dileber.gold.alipaygold.goldmbp.text1;

import android.support.annotation.NonNull;

import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberPresenter;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;
import com.drcosu.ndileber.mvp.presenter.NormalPresenter;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.tools.log.ULog;
import com.drcosu.ndileber.tools.rx.RxNetworkResponseObserver;

import java.util.Date;

/**
 * Created by dileber
 */

public class Main2Presenter extends DileberRxPresenter<Main2Contract.View,MainRepository> implements Main2Contract.Presenter {

    public Main2Presenter(@NonNull Main2Contract.View view, @NonNull MainRepository mDataSource) {
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
    public void fundNetValue() {
        String endDate = UTime.getBeijingNowTime(UTime.Pattern.ymd);
        String startDate = UTime.getDateStr(UTime.Pattern.ymd,UTime.addMonth(new Date(),-6));
        add(mDataSource.fundNetValue(new FundNetValueParams.Builder().setData("NETVALUEPER","000929",startDate,endDate).build()).subscribe(new RxNetworkResponseObserver<FundNetValueResponse>() {

            @Override
            public void onResponseFail(Exception e) {

            }

            @Override
            public void onBeforeResponseOperation() {

            }

            @Override
            public void onResponse(FundNetValueResponse fundNetValueResponse) {
//                ULog.o(fundNetValueResponse);
                mView.showChat(fundNetValueResponse);
            }

        }));
    }
}
