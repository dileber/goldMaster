package com.dileber.gold.alipaygold;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by dileber
 */

public class MainContract {

    interface Presenter extends BasePresenter {
    }

    public interface View<T extends Presenter> extends BaseView<T> {

    }

}
