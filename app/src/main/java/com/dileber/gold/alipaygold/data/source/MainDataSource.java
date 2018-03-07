package com.dileber.gold.alipaygold.data.source;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;

import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.params.GetFuturesQuoteParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;
import com.drcosu.ndileber.mvp.data.BaseDataSource;

import retrofit2.Callback;
import retrofit2.http.Body;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 自动生成.
 */

public interface MainDataSource extends BaseDataSource {

    Observable<FundNetValueResponse> fundNetValue(FundNetValueParams params);

    Observable<FundHisDetailResponse> fundHisDetail(FundHisDetailParams params);

    void saveFundNetValue(FundNetValueResponse fundNetValueResponse);

    Observable<Boolean> saveImage(@DrawableRes int id);

    Observable<GetFuturesQuoteResponse> getFuturesQuote(GetFuturesQuoteParams params);

    Observable<TouTiaoResponse> toutiao();

}
