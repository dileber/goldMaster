package com.dileber.gold.alipaygold.goldmbp.text2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.drcosu.ndileber.mvp.data.model.SModel;
import com.drcosu.ndileber.mvp.presenter.BasePresenter;
import com.drcosu.ndileber.view.recycle.DileberAdapter;
import com.drcosu.ndileber.view.recycle.DileberHolder;

/**
 * Created by shidawei on 2017/6/5.
 */

public class MonoAdapter extends DileberAdapter<MonoAdapter.MonoHolder,SModel>{


    public MonoAdapter(Context context) {
        super(context);
    }

    public MonoAdapter(Context context, BasePresenter mBasePresenter) {
        super(context, mBasePresenter);
    }

    @Override
    protected void addViewHolderData(MonoHolder holder, int position) {

    }

    @Override
    public DileberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(0, parent, false);
        return new MonoHolder(view);
    }

    class MonoHolder extends DileberHolder<SModel>{
        public MonoHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void load(Context context) {

        }
    }
}
