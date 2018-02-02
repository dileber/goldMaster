package com.dileber.gold.alipaygold.goldmbp.user;


import android.content.Context;
import android.os.Bundle;

import com.dileber.gold.alipaygold.R;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;


/**
 * 自动生成：by dileber.
 */


public class UserFragment extends UBaseFragment<UserPresenter> implements UserContract.View<UserPresenter> {

    public UserFragment() {
    }


    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
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
    protected UserPresenter createPresenterInstance() {
        return new UserPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    public int layoutViewId() {
        return R.layout.fragment_user;
    }
}