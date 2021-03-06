package com.dileber.gold.alipaygold.goldmbp.history;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
    TextView gonggao;
    View titleList;
    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerView = findView(R.id.list);
        refresh = findView(R.id.refresh);
        emptyPage = findView(R.id.emptyPageLayout);
        gonggao = findView(R.id.gonggao);
        titleList = findView(R.id.titleList);
        TextView timeT = titleList.findViewById(R.id.timeT);
        timeT.setText("时间");
        TextView valueT = titleList.findViewById(R.id.valueT);
        valueT.setText("日涨跌幅");
        TextView valueT2 = titleList.findViewById(R.id.valueT2);
        valueT2.setText("7日收益");
        TextView valueT3 = titleList.findViewById(R.id.valueT3);
        valueT3.setText("30日收益");
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
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshLast();
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
            if(fundHisDetailResponse.data.resultList.size()>0){
                FundHisDetailResponse.Data.ResultList resultList= fundHisDetailResponse.data.resultList.get(0);
                StringBuffer sb = new StringBuffer("公告：");
                if(Math.abs(Double.parseDouble(resultList.rate))>=0.5){
                    sb.append(resultList.date+"日涨跌幅比较大，投资要谨慎，");
                }else{
                    sb.append(resultList.date+"日涨跌幅比较小，注意投资，");
                }



                if(Double.parseDouble(resultList.monthYield)<=0){
                    sb.append("今天比较适合长期投资，");
                }else{
                    if(Double.parseDouble(resultList.monthYield)>=2){
                        sb.append("今天不适合长期投资，");
                    }else{
                        sb.append("今天长期投资情况一般，");
                    }
                }
                if(Double.parseDouble(resultList.weekYield)<=-1.5){
                    sb.append("今天比较适合短期投资。");
                }else{
                    sb.append("今天短期投资情况一般。");
                }
                gonggao.setText(sb.toString());
            }
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