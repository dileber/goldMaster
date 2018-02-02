package com.dileber.gold.alipaygold.goldmbp.history;

import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.drcosu.ndileber.dileberui.emptypage.EmptyPageLayout;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.mvp.view.BaseView;

import java.util.List;

/**
 * Created by dileber
 */

public class HistoryContract {

    interface Presenter extends BasePresenter {
        void refreshLast();
        void loadMore();
    }

    public interface View<T extends Presenter> extends BaseView<T> {
        void showList(FundHisDetailResponse fundHisDetailResponse, boolean isRefreshLast);
        void displayEmptyPage(EmptyPageLayout.Builder.Empty empty);
        void refreshCompleted();
        void loadMoreCompleted();
    }

}
