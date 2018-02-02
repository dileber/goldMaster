package com.dileber.gold.alipaygold.goldmbp.history;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.dileberui.emptypage.EmptyPageLayout;
import com.drcosu.ndileber.mvp.presenter.DileberRxPresenter;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.tools.log.ULog;
import com.drcosu.ndileber.tools.rx.RxNetworkResponseObserver;

import java.util.Date;

/**
 * Created by dileber
 */

public class HistoryPresenter extends DileberRxPresenter<HistoryContract.View, MainRepository> implements HistoryContract.Presenter {

    public HistoryPresenter(@NonNull HistoryContract.View view, @NonNull MainRepository mDataSource) {
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

    protected boolean isRefreshLast = true;
    protected int currentPage = 1;
    protected int pageSize = 10;

    private void request(int currentPage) {
        this.currentPage = currentPage;
        String endDate = UTime.getBeijingNowTime(UTime.Pattern.ymd);
        String startDate = UTime.getDateStr(UTime.Pattern.ymd,UTime.addMonth(new Date(),-6));

        add(mDataSource.fundHisDetail(new FundHisDetailParams.Builder().setData("000930",startDate,endDate).pageInfo(currentPage,pageSize).build()).subscribe(new RxNetworkResponseObserver<FundHisDetailResponse>() {
            @Override
            public void onResponseFail(Exception e) {
                if(isRefreshLast){
                    mView.displayEmptyPage(EmptyPageLayout.networkError);
                }else{
                    mView.toast(EmptyPageLayout.networkError.getMsg(), Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onBeforeResponseOperation() {
                mView.loadMoreCompleted();
                mView.refreshCompleted();
            }

            @Override
            public void onResponse(FundHisDetailResponse fundHisDetailResponse) {
                if(fundHisDetailResponse.data.resultList.size()==0){
                    if (isRefreshLast) {
                        mView.displayEmptyPage(EmptyPageLayout.emptyData);
                    }
                }else{
                    mView.showList(fundHisDetailResponse,isRefreshLast);
                }

            }
        }));
    }

    public void refreshLast() {
        isRefreshLast = true;
        request(1);
    }

    public void loadMore() {
        isRefreshLast = false;
        currentPage++;
        request(currentPage);
    }
}
