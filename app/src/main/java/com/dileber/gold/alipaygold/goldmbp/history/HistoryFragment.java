package com.dileber.gold.alipaygold.goldmbp.history;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.adapter.HistoryAdapter;
import com.dileber.gold.alipaygold.data.model.MFund;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.dileberui.emptypage.EmptyPageLayout;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;
import com.drcosu.ndileber.view.recycle.FooterHolder;
import com.drcosu.ndileber.view.recycle.LoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.drcosu.ndileber.dileberui.emptypage.EmptyPageLayout.networkError;


/**
 * 自动生成：by dileber.
 */


public class HistoryFragment extends UBaseFragment<HistoryPresenter> implements HistoryContract.View<HistoryPresenter> {

    public HistoryFragment() {
    }


    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean retain() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    protected HistoryPresenter createPresenterInstance() {
        return new HistoryPresenter(this, MainRepository.getInstance());
    }
    RecyclerView recyclerView;
    EmptyPageLayout emptyPage;
    HistoryAdapter historyAdapter;
    SwipeRefreshLayout refresh;
    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerView = findView(R.id.list);
        refresh = findView(R.id.refresh);
        emptyPage = findView(R.id.emptyPageLayout);
        historyAdapter = new HistoryAdapter(getActivityContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(historyAdapter);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if (refresh != null) {
                    refresh.setRefreshing(true);
                    mPresenter.refreshLast();
                }
            }
        });

        recyclerView.addOnScrollListener(new LoadMoreListener() {
            @Override
            public void onLoadNextPage(View view) {
                if(!refresh.isRefreshing()) {
                    historyAdapter.setFootState(FooterHolder.FooterState.Loading);
                    mPresenter.loadMore();
                }

            }
        });

        emptyPage.setOnRefreshDelegate(new EmptyPageLayout.OnRefreshDelegate() {
            @Override
            public void onRefresh() {
                mPresenter.refreshLast();
            }
        });
    }


    @Override
    public int layoutViewId() {
        return R.layout.fragment_history;
    }

    @Override
    public void showList(FundHisDetailResponse fundHisDetailResponse, boolean isRefreshLast) {
        displayHasData();

        if (isRefreshLast) {
            historyAdapter.refresh(fundHisDetailResponse.data.resultList);
        } else {
            historyAdapter.addData(fundHisDetailResponse.data.resultList);
        }
    }
    private void displayHasData() {
        emptyPage.setVisibility(View.GONE);
        refresh.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayEmptyPage(EmptyPageLayout.Builder.Empty empty) {
        emptyPage.setVisibility(View.VISIBLE);
        emptyPage.setEmptyType(empty);
        historyAdapter.clean();
    }

    @Override
    public void refreshCompleted() {
        refresh.setRefreshing(false);
    }

    @Override
    public void loadMoreCompleted() {
        historyAdapter.setFootState(FooterHolder.FooterState.Normal);
    }
}