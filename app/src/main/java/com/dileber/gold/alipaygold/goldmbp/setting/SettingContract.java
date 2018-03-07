package com.dileber.gold.alipaygold.goldmbp.setting;

import android.support.annotation.DrawableRes;

import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

/**
 * Created by dileber
 */

public class SettingContract {

    interface Presenter extends BasePresenter {
        void saveImage(@DrawableRes int id);
    }

    public interface View<T extends Presenter> extends BaseView<T> {
    }

}
