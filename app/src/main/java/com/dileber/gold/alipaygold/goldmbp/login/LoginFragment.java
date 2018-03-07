package com.dileber.gold.alipaygold.goldmbp.login;


import android.content.Context;
import android.os.Bundle;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;


/**
 * 自动生成：by dileber.
 */


public class LoginFragment extends UBaseFragment<LoginPresenter> implements LoginContract.View<LoginPresenter> {

    public LoginFragment() {
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
    protected LoginPresenter createPresenterInstance() {
        return new LoginPresenter(this, MainRepository.getInstance());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    public int layoutViewId() {
        return R.layout.fragment_login;
    }
}