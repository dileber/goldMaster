package com.dileber.gold.alipaygold.goldmbp.home;

import android.support.annotation.NonNull;

import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.tools.log.ULog;
import com.drcosu.ndileber.tools.rx.RxNetworkResponseObserver;

import java.util.Date;

/**
 * Created by hyy on 2018/2/1.
 */
public class HomePresenter extends DileberRxPresenter<HomeContract.View,MainRepository> implements HomeContract.Presenter {

    public HomePresenter(@NonNull HomeContract.View view, @NonNull MainRepository mDataSource) {
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
                ULog.o(fundNetValueResponse);
                mView.showChat(fundNetValueResponse);
            }

        }));
    }
}
