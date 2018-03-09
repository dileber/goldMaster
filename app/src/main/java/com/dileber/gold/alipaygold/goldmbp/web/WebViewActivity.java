package com.dileber.gold.alipaygold.goldmbp.web;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dileber.gold.alipaygold.R;
import com.drcosu.ndileber.mvp.ubase.UBaseActivity;
import com.drcosu.ndileber.tools.log.ULog;
import com.drcosu.ndileber.utils.LauncherManager;
import com.drcosu.ndileber.utils.UToolBar;

/**
 * 自动生成：by dileber.
 */

public class WebViewActivity extends UBaseActivity<WebViewPresenter> implements WebViewContract.View<WebViewPresenter> {
    public static final String WEB_PAGE_URL = "WEB_PAGE_URL";

    private String url;
    private String lastFailUrl = null;
    public static void startFrom(Context context, String url) {
        Bundle bundle = new Bundle();
        bundle.putString(WEB_PAGE_URL, url);
        LauncherManager.launcher.launch(context, WebViewActivity.class,bundle);
    }

    @Override
    public int layoutViewId() {
        return R.layout.activity_web_view;
    }
    WebView client;
    ProgressBar loadingBar;

    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setNeedNavigate(true);
        setToolBar(R.id.toolbar, uToolBar);
        client = findView(R.id.client);
        loadingBar = findView(R.id.loadingBar);
        url = getIntent().getStringExtra(WEB_PAGE_URL);
        WebSettings settings = client.getSettings();
        settings.setJavaScriptEnabled(true);
        ULog.d(url);
        client.loadUrl(url);
        client.setWebViewClient(new WebViewClient(){
            private boolean isValidUrl(String url) {
                if (TextUtils.isEmpty(url)) {
                    return false;
                }
                final String httpsPrefix = "https://";
                final String httpPrefix = "http://";
                final String filePrefix = "file:///";
                final String contentPrefix = "content://";

                if (url.startsWith(httpsPrefix)
                        || url.startsWith(httpPrefix)
                        || url.startsWith(filePrefix)
                        || url.startsWith(contentPrefix)) {
                    return true;
                }
                return false;
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                lastFailUrl = null;
                if (!isValidUrl(url)) {
                    //Common alipay only.
                } else {
                    view.loadUrl(url);
                }
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    lastFailUrl = request.getUrl().toString();
                }
                toast("网络请求错误", Toast.LENGTH_SHORT);
            }
        });

        client.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                setTitle(title);
            }
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                loadingBar.setProgress(newProgress);
                if (newProgress == 100) {
                    loadingBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected WebViewPresenter createPresenterInstance() {
        return new WebViewPresenter(this);
    }

    @Override
    public void onBackPressed() {
        if(client.canGoBack()){
            client.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (client != null) {
            client.removeAllViews();
            try {
                client.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}

