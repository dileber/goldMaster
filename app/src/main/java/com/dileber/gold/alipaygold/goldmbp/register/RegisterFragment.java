package com.dileber.gold.alipaygold.goldmbp.register;


import android.content.Context;
import android.os.Bundle;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;


/**
 * 自动生成：by dileber.
 */


public class RegisterFragment extends UBaseFragment<RegisterPresenter> implements RegisterContract.View<RegisterPresenter> {

    public RegisterFragment() {
    }


    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
    protected RegisterPresenter createPresenterInstance() {
        return new RegisterPresenter(this, MainRepository.getInstance());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    public int layoutViewId() {
        return R.layout.fragment_register;
    }
}