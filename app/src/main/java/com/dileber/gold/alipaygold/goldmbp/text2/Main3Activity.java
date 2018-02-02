package com.dileber.gold.alipaygold.goldmbp.text2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.dileberui.emptypage.EmptyPageLayout;
import com.drcosu.ndileber.mvp.ubase.UBaseActivity;
import com.drcosu.ndileber.utils.LauncherManager;
import com.drcosu.ndileber.utils.UToolBar;

/**
 * 自动生成：by dileber.
 */

public class Main3Activity extends UBaseActivity<Main3Presenter> implements Main3Contract.View<Main3Presenter> {

    public static void start(Context context) {
        LauncherManager.launcher.launch(context, Main3Activity.class);
    }

    @Override
    public int layoutViewId() {
        return R.layout.activity_main2;
    }

    EmptyPageLayout emptyPageLayout;
    RecyclerView list;
    SwipeRefreshLayout refresh;

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("标题");
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar, uToolBar);
        emptyPageLayout = findView(R.id.emptyPageLayout);
        list = findView(R.id.list);
        refresh = findView(R.id.refresh);

    }

    @Override
    protected Main3Presenter createPresenterInstance() {
        return new Main3Presenter(this, MainRepository.getInstance());
    }
}

