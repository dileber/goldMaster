package com.dileber.gold.alipaygold.data.service;

import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 自动生成.
 */

public interface MainService {

    @POST(ApiMethods.fundNetValue)
    Observable<FundNetValueResponse> fundNetValue(@Body FundNetValueParams params);

    @POST(ApiMethods.fundHisDetail)
    Observable<FundHisDetailResponse> fundHisDetail(@Body FundHisDetailParams params);

}
