package com.dileber.gold.alipaygold.goldmbp.text1;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by dileber
 */

public class Main2Contract {

    interface Presenter extends BasePresenter {
        void fundNetValue();
    }

    public interface View<T extends Presenter> extends BaseView<T> {

        void showChat(FundNetValueResponse fundNetValueResponse);
    }

}
