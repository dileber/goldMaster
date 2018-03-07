package com.dileber.gold.alipaygold;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dileber.gold.alipaygold.goldmbp.history.HistoryFragment;
import com.dileber.gold.alipaygold.goldmbp.home.HomeFragment;
import com.dileber.gold.alipaygold.goldmbp.nowtime.NowTimeFragment;
import com.dileber.gold.alipaygold.goldmbp.setting.SettingFragment;
import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.view.BView;
import com.drcosu.ndileber.tools.DialogLinstener;
import com.drcosu.ndileber.tools.UDialog;
import com.drcosu.ndileber.tools.UUi;
import com.drcosu.ndileber.tools.permissions.PermissionsManager;
import com.drcosu.ndileber.tools.permissions.PermissionsResultAction;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.LauncherManager;
import com.drcosu.ndileber.utils.UToolBar;

public class AppActivity extends BaseActivity implements BView,BottomNavigationView.OnNavigationItemSelectedListener {

    public static void start(Context context) {
        LauncherManager.launcher.launch(context, AppActivity.class);
    }

    @Override
    protected void startView(Bundle savedInstanceState) {

    }
    BottomNavigationView navigation;

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("黄金分析师");
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar, uToolBar);
        navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        ActivityUtils.replaceFragment(this.getSupportFragmentManager(),R.id.container, HomeFragment.newInstance());
        requestPermissions();

    }

    @Override
    public int layoutViewId() {
        return R.layout.activity_app;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                ActivityUtils.replaceFragment(this.getSupportFragmentManager(),R.id.container, HomeFragment.newInstance());
                return true;
            case R.id.navigation_dashboard:
                ActivityUtils.replaceFragment(this.getSupportFragmentManager(),R.id.container,HistoryFragment.newInstance());
                return true;
            case R.id.navigation_now:
                ActivityUtils.replaceFragment(this.getSupportFragmentManager(),R.id.container, NowTimeFragment.newInstance());
                return true;
//            case R.id.navigation_notifications:
//                ActivityUtils.replaceFragment(this.getSupportFragmentManager(),R.id.container, SettingFragment.newInstance());
//                return true;
        }
        return false;
    }

    @TargetApi(23)
    private void requestPermissions() {
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                //toast("权限OK",Toast.LENGTH_SHORT);
            }

            @Override
            public void onDenied(String permission) {
                //toast(permission+"没有权限，需要用户在设置中添加权限",Toast.LENGTH_SHORT);
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    @Override
    public void toast(String msg, int duration) {
        UUi.toast(this,msg,duration);
    }

    @Override
    public void showAlert(Integer type, String message) {

    }
    Dialog dialog;

    @Override
    public void loading() {
        if(dialog==null){
            dialog = UDialog.loading();
        }
        dialog.show();
    }

    @Override
    public void loadDialogDismiss() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public void dialogOk(String content, DialogLinstener dialogLinstener) {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialog!=null){
            dialog.dismiss();
        }

    }
}
