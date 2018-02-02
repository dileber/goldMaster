package com.dileber.gold.alipaygold.goldmbp.user;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by dileber
 */

public class UserContract {

    interface Presenter extends BasePresenter {

    }

    public interface View<T extends Presenter> extends BaseView<T> {

    }

}
