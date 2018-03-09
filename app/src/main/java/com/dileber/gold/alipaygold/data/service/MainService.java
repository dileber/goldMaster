package com.dileber.gold.alipaygold.data.service;

import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.params.GetFuturesQuoteParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by 自动生成.
 */

public interface MainService {

    @GET(ApiMethods.fundNetValue)
    Observable<FundNetValueResponse> fundNetValue(@Query("netValueType") String netValueType,@Query("fundCode") String fundCode,@Query("startDate") String startDate,@Query("endDate") String endDate);

    @GET(ApiMethods.fundHisDetail)
    Observable<FundHisDetailResponse> fundHisDetail(@Query("fundCode") String fundCode,@Query("startDate") String startDate,@Query("endDate") String endDate,@Query("pageNo")String pageNo,@Query("pageSize")String pageSize);


    @POST(ApiMethods.fundNetValue)
    Observable<FundNetValueResponse> fundNetValue(@Body FundNetValueParams params);

    @POST(ApiMethods.fundHisDetail)
    Observable<FundHisDetailResponse> fundHisDetail(@Body FundHisDetailParams params);

    @POST(ApiMethods.getFuturesQuote)
    Observable<GetFuturesQuoteResponse> getFuturesQuote(@Body GetFuturesQuoteParams params);

    @GET(ApiMethods.getFuturesQuote)
    Observable<GetFuturesQuoteResponse> getFuturesQuote(@Query("commodityNo") String commodityNo,@Query("type") String type);

    @GET(ApiMethods.toutiao)
    Observable<TouTiaoResponse> toutiao(@Query("platform") String platform, @Query("limit") String limit, @Query("cursor") String cursor, @Query("channel") String channel);

}
