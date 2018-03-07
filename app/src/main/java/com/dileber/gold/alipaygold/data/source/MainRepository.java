package com.dileber.gold.alipaygold.data.source;


import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.params.GetFuturesQuoteParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;
import com.dileber.gold.alipaygold.data.source.local.MainLocalDataSource;
import com.dileber.gold.alipaygold.data.source.remote.MainRemoteDataSource;
import com.drcosu.ndileber.mvp.data.BaseRepository;
import com.drcosu.ndileber.tools.log.ULog;
import com.drcosu.ndileber.tools.rx.RxTransformerHelper;

import retrofit2.Callback;
import rx.Observable;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 自动生成.
 */

public class MainRepository extends BaseRepository<MainLocalDataSource, MainRemoteDataSource> implements MainDataSource {

    public static volatile MainRepository instance;


    protected MainRepository(MainLocalDataSource localDataSource, MainRemoteDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

    public static MainRepository getInstance() {
        if (instance == null) {
            synchronized (MainRepository.class) {
                if (instance == null) {
                    instance = new MainRepository(MainLocalDataSource.getInstance(), MainRemoteDataSource.getInstance());
                }
            }
        }
        return instance;
    }


    @Override
    public Observable<FundNetValueResponse> fundNetValue(FundNetValueParams params) {
        Observable<FundNetValueResponse> observable = Observable.
                concat(localDataSource.fundNetValue(params),
                        remoteDataSource.fundNetValue(params).
                                doOnNext(new Action1<FundNetValueResponse>() {
                            @Override
                            public void call(FundNetValueResponse fundNetValueResponse) {
//                                if(fundNetValueResponse!=null){
//                                    saveFundNetValue(fundNetValueResponse);
//                                }
                                /**
                                 * cache
                                 */
                            }
                        })).compose(RxTransformerHelper.<FundNetValueResponse>ioToUI()).first();
        return observable;
    }

    @Override
    public Observable<FundHisDetailResponse> fundHisDetail(FundHisDetailParams params) {
        Observable<FundHisDetailResponse> observable = Observable.
                concat(localDataSource.fundHisDetail(params),
                        remoteDataSource.fundHisDetail(params).
                                doOnNext(new Action1<FundHisDetailResponse>() {
                                    @Override
                                    public void call(FundHisDetailResponse fundHisDetailResponse) {
//                                if(fundNetValueResponse!=null){
//                                    saveFundNetValue(fundNetValueResponse);
//                                }
                                        /**
                                         * cache
                                         */
                                    }
                                })).compose(RxTransformerHelper.<FundHisDetailResponse>ioToUI()).first();
        return observable;
    }

    @Override
    public void saveFundNetValue(FundNetValueResponse fundNetValueResponse) {
        localDataSource.saveFundNetValue(fundNetValueResponse);
    }

    @Override
    public Observable<Boolean> saveImage(int id) {
        return localDataSource.saveImage(id).compose(RxTransformerHelper.<Boolean>ioToUI());
    }

    @Override
    public Observable<GetFuturesQuoteResponse> getFuturesQuote(GetFuturesQuoteParams params) {
        Observable<GetFuturesQuoteResponse> observable = Observable.
                concat(localDataSource.getFuturesQuote(params),
                        remoteDataSource.getFuturesQuote(params).
                                doOnNext(new Action1<GetFuturesQuoteResponse>() {
                                    @Override
                                    public void call(GetFuturesQuoteResponse fundNetValueResponse) {
//                                if(fundNetValueResponse!=null){
//                                    saveFundNetValue(fundNetValueResponse);
//                                }
                                        /**
                                         * cache
                                         */
                                    }
                                })).compose(RxTransformerHelper.<GetFuturesQuoteResponse>ioToUI()).first();
        return observable;
    }

    @Override
    public Observable<TouTiaoResponse> toutiao() {
        Observable<TouTiaoResponse> observable = Observable.
                concat(localDataSource.toutiao(),
                        remoteDataSource.toutiao().
                                doOnNext(new Action1<TouTiaoResponse>() {
                                    @Override
                                    public void call(TouTiaoResponse fundNetValueResponse) {
//                                if(fundNetValueResponse!=null){
//                                    saveFundNetValue(fundNetValueResponse);
//                                }
                                        /**
                                         * cache
                                         */
                                    }
                                })).compose(RxTransformerHelper.<TouTiaoResponse>ioToUI()).first();
        return observable;
    }
}
