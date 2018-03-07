package com.dileber.gold.alipaygold.data.source.local;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.dileber.gold.alipaygold.data.model.params.FundHisDetailParams;
import com.dileber.gold.alipaygold.data.model.params.FundNetValueParams;
import com.dileber.gold.alipaygold.data.model.params.GetFuturesQuoteParams;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;
import com.dileber.gold.alipaygold.data.source.MainDataSource;
import com.drcosu.ndileber.app.SApplication;
import com.drcosu.ndileber.mvp.data.source.local.BaseLocalDataSource;
import com.drcosu.ndileber.tools.file.FileUtil;
import com.drcosu.ndileber.tools.log.ULog;

import java.io.File;

import retrofit2.Callback;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 自动生成.
 */

public class MainLocalDataSource extends BaseLocalDataSource implements MainDataSource {

    private static volatile MainLocalDataSource instance;

    private MainLocalDataSource() {

    }

    public static MainLocalDataSource getInstance() {
        if (instance == null) {
            synchronized (MainLocalDataSource.class) {
                if (instance == null) {
                    instance = new MainLocalDataSource();
                }
            }
        }
        return instance;
    }

    public Observable<FundNetValueResponse> fundNetValue(FundNetValueParams params){
        Observable<FundNetValueResponse> fundNetValue = Observable.create(new Observable.OnSubscribe<FundNetValueResponse>() {
            @Override
            public void call(Subscriber<? super FundNetValueResponse> subscriber) {
//                if(fundNetValueResponse!=null){
//                    subscriber.onNext(fundNetValueResponse);
//                }
                subscriber.onCompleted();
            }
        });

        return fundNetValue;
    }

    @Override
    public Observable<FundHisDetailResponse> fundHisDetail(FundHisDetailParams params) {
        Observable<FundHisDetailResponse> fundHisDetail = Observable.create(new Observable.OnSubscribe<FundHisDetailResponse>() {
            @Override
            public void call(Subscriber<? super FundHisDetailResponse> subscriber) {
//                if(fundNetValueResponse!=null){
//                    subscriber.onNext(fundNetValueResponse);
//                }
                subscriber.onCompleted();
            }
        });

        return fundHisDetail;
    }

    //    FundNetValueResponse fundNetValueResponse = null;
    @Override
    public void saveFundNetValue(FundNetValueResponse fundNetValueResponse) {
        ULog.o(fundNetValueResponse);
//        this.fundNetValueResponse = fundNetValueResponse;
    }

    @Override
    public Observable<Boolean> saveImage(final int id) {
        Observable<Boolean> objectObservable = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Bitmap bitmap = BitmapFactory.decodeResource(SApplication.getAppContext().getResources(), id);
                String imageUri = MediaStore.Images.Media.insertImage(hContentProvider.getmContentResolver(),bitmap,"title", "description");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(new File(imageUri));
                    scanIntent.setData(contentUri);
                    SApplication.getAppContext().sendBroadcast(scanIntent);
                } else {
                    //4.4开始不允许发送"Intent.ACTION_MEDIA_MOUNTED"广播, 否则会出现: Permission Denial: not allowed to send broadcast android.intent.action.MEDIA_MOUNTED from pid=15410, uid=10135
                    Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
                    SApplication.getAppContext().sendBroadcast(intent);
                }
                bitmap.recycle();
                subscriber.onNext(true);
            }
        });
        return objectObservable;
    }

    @Override
    public Observable<GetFuturesQuoteResponse> getFuturesQuote(GetFuturesQuoteParams params) {
        Observable<GetFuturesQuoteResponse> getFuturesQuote = Observable.create(new Observable.OnSubscribe<GetFuturesQuoteResponse>() {
            @Override
            public void call(Subscriber<? super GetFuturesQuoteResponse> subscriber) {
                subscriber.onCompleted();
            }
        });

        return getFuturesQuote;
    }

    @Override
    public Observable<TouTiaoResponse> toutiao() {
        Observable<TouTiaoResponse> getFuturesQuote = Observable.create(new Observable.OnSubscribe<TouTiaoResponse>() {
            @Override
            public void call(Subscriber<? super TouTiaoResponse> subscriber) {
                subscriber.onCompleted();
            }
        });

        return getFuturesQuote;
    }

}
