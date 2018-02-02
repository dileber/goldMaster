package com.dileber.gold.alipaygold.goldmbp.text2;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;

public class MonoListFragment extends UBaseFragment<Main3Presenter> implements Main3Contract.View<Main3Presenter> {
    @Override
    public int layoutViewId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected boolean retain() {
        return false;
    }

    @Override
    protected Main3Presenter createPresenterInstance() {
        return new Main3Presenter(this, MainRepository.getInstance());
    }
}
