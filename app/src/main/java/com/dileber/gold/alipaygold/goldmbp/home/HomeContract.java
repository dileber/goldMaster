package com.dileber.gold.alipaygold.goldmbp.home;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

import java.util.List;

/**
 * Created by hyy on 2018/2/1.
 */
public class HomeContract {

    interface Presenter extends BasePresenter {
        void fundNetValue();
        void toutiao();

    }

    public interface View<T extends Presenter> extends BaseView<T> {
        void showChat(FundNetValueResponse fundNetValueResponse);
        void showToutiao(List<TouTiaoResponse.Data.Items> items);

    }

}
