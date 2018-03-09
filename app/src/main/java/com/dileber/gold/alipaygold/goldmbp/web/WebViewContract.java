package com.dileber.gold.alipaygold.goldmbp.web;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by dileber
 */

public class WebViewContract {

    interface Presenter extends BasePresenter {

    }

    public interface View<T extends Presenter> extends BaseView<T> {

    }

}
