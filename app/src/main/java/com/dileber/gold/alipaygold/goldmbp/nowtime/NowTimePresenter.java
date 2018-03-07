package com.dileber.gold.alipaygold.goldmbp.nowtime;

import android.support.annotation.NonNull;

import com.dileber.gold.alipaygold.data.model.CommodityNo;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.params.GetFuturesQuoteParams;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.tools.log.ULog;
import com.drcosu.ndileber.tools.rx.RxNetworkResponseObserver;

import java.util.Date;

/**
 * Created by dileber
 */

public class NowTimePresenter extends DileberRxPresenter<NowTimeContract.View, MainRepository> implements NowTimeContract.Presenter {

    public NowTimePresenter(@NonNull NowTimeContract.View view, @NonNull MainRepository mDataSource) {
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
    public void getFuturesQuote(final CommodityNo commodityNo) {

        add(mDataSource.getFuturesQuote(new GetFuturesQuoteParams.Builder().setData(commodityNo.getS(),"minute").build()).subscribe(new RxNetworkResponseObserver<GetFuturesQuoteResponse>() {
            @Override
            public void onResponseFail(Exception e) {

            }

            @Override
            public void onBeforeResponseOperation() {
            }

            @Override
            public void onResponse(GetFuturesQuoteResponse fundNetValueResponse) {
                mView.showFuturesQuote(fundNetValueResponse,commodityNo);
            }

        }));
    }
}
