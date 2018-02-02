package com.dileber.gold.alipaygold;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dileber.gold.alipaygold.goldmbp.history.HistoryFragment;
import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.utils.ActivityUtils;

public class AppActivity extends BaseActivity {

    @Override
    protected void startView(Bundle savedInstanceState) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityUtils.getFragment(getSupportFragmentManager(),R.id.container, HistoryFragment.newInstance());

    }

    @Override
    public int layoutViewId() {
        return R.layout.activity_app;
    }
}
