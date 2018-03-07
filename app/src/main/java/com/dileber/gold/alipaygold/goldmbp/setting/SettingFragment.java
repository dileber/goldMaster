package com.dileber.gold.alipaygold.goldmbp.setting;


import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;
import com.drcosu.ndileber.tools.permissions.PermissionsManager;
import com.drcosu.ndileber.tools.permissions.PermissionsResultAction;


/**
 * 自动生成：by dileber.
 */


public class SettingFragment extends UBaseFragment<SettingPresenter> implements SettingContract.View<SettingPresenter> {

    public SettingFragment() {
    }


    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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
    protected SettingPresenter createPresenterInstance() {
        return new SettingPresenter(this, MainRepository.getInstance());
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        findView(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.saveImage(R.mipmap.img_money);
            }
        });
    }

    private void grantLoactionPermissons() {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(
                this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        savePhoto();
                    }

                    @Override
                    public void onDenied(String permission) {
                        toast("没有读写SD卡权限，需要用户在设置中添加权限", Toast.LENGTH_SHORT);
                    }
                });
    }

    private void savePhoto(){
        boolean hasPermission = PermissionsManager.getInstance().hasAllPermissions(
                getActivityContext(),  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        if (hasPermission == false) {//没有授权成功
            grantLoactionPermissons();//去授权
            return;
        }
    }

    @Override
    public int layoutViewId() {
        return R.layout.fragment_setting;
    }
}