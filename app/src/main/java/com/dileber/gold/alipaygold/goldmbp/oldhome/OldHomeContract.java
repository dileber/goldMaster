package com.dileber.gold.alipaygold.goldmbp.oldhome;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.goldmbp.home.HomeContract;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by dileber
 */

public class OldHomeContract {

    interface Presenter extends BasePresenter {
        void fundNetValue();

    }

    public interface View<T extends OldHomeContract.Presenter> extends BaseView<T> {
        void showChat(FundNetValueResponse fundNetValueResponse);

    }

}
