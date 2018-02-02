package com.dileber.gold.alipaygold.goldmbp.text1;


import android.content.Context;
import android.os.Bundle;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.ubase.UBaseActivity;
import com.drcosu.ndileber.utils.LauncherManager;
import com.drcosu.ndileber.utils.UToolBar;

/**
 * 自动生成：by dileber.
 */

public class Main2Activity extends UBaseActivity<Main2Presenter> implements Main2Contract.View<Main2Presenter> {

    public static void start(Context context) {
        LauncherManager.launcher.launch(context, Main2Activity.class);
    }

    @Override
    public int layoutViewId() {
        return R.layout.activity_main2;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("标题");
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar, uToolBar);
    }

    @Override
    protected Main2Presenter createPresenterInstance() {
        return new Main2Presenter(this, MainRepository.getInstance());
    }

    @Override
    public void showChat(FundNetValueResponse fundNetValueResponse) {
        
    }
}

