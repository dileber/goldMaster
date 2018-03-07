package com.dileber.gold.alipaygold.goldmbp.nowtime;

import com.dileber.gold.alipaygold.data.model.CommodityNo;
import com.dileber.gold.alipaygold.data.model.params.GetFuturesQuoteParams;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

import retrofit2.http.Body;

/**
 * Created by dileber
 */

public class NowTimeContract {

    interface Presenter extends BasePresenter {
        void getFuturesQuote(CommodityNo commodityNo);
    }

    public interface View<T extends Presenter> extends BaseView<T> {

        void showFuturesQuote(GetFuturesQuoteResponse fundNetValueResponse,CommodityNo commodityNo);
    }

}
