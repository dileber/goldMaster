package com.dileber.gold.alipaygold.goldmbp.home;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by hyy on 2018/2/1.
 */
public class HomeContract {

    interface Presenter extends BasePresenter {
        void fundNetValue();

    }

    public interface View<T extends Presenter> extends BaseView<T> {
        void showChat(FundNetValueResponse fundNetValueResponse);

    }

}
