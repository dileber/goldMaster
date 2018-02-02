package com.dileber.gold.alipaygold.data.source;

import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.drcosu.ndileber.mvp.data.BaseDataSource;

import retrofit2.Callback;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 自动生成.
 */

public interface MainDataSource extends BaseDataSource {

    Observable<FundNetValueResponse> fundNetValue(FundNetValueParams params);

    Observable<FundHisDetailResponse> fundHisDetail(FundHisDetailParams params);

    void saveFundNetValue(FundNetValueResponse fundNetValueResponse);

}
