package com.dileber.gold.alipaygold;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.dileber.gold.alipaygold.goldmbp.history.HistoryFragment;
import com.dileber.gold.alipaygold.goldmbp.home.HomeFragment;
import com.dileber.gold.alipaygold.goldmbp.oldhome.OldHomeFragment;
import com.dileber.gold.alipaygold.goldmbp.text1.Main2Activity;
import com.dileber.gold.alipaygold.goldmbp.text1.Main2Contract;
import com.dileber.gold.alipaygold.goldmbp.text1.Main2Presenter;
import com.dileber.gold.alipaygold.utils.DayAxisValueFormatter;
import com.dileber.gold.alipaygold.view.ItemNameTextView;
import com.dileber.gold.alipaygold.utils.MyAxisValueFormatter;
import com.drcosu.ndileber.dileberui.diagram.lineschart.QuXianChatView;
import com.drcosu.ndileber.mvp.ubase.UBaseActivity;
import com.drcosu.ndileber.tools.SSystem;
import com.drcosu.ndileber.tools.SysInfoUtil;
import com.drcosu.ndileber.tools.TNum;
import com.drcosu.ndileber.tools.TVersion;
import com.drcosu.ndileber.utils.ActivityUtils;
import com.drcosu.ndileber.utils.LauncherManager;
import com.drcosu.ndileber.utils.UToolBar;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends UBaseActivity<MainPresenter> implements MainContract.View<MainPresenter> {

    public static void start(Context context) {
        LauncherManager.launcher.launch(context, MainActivity.class);
    }

   TextView version;

    private static final int MESSAGE_NEXT = 1;

    Handler h;

    @Override
    public int layoutViewId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView(Bundle savedInstanceState) {
        version = findView(R.id.version);
        version.setText("V"+TVersion.getVersionName(getActivityContext()));
        h = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == MESSAGE_NEXT ){
                    AppActivity.start(getActivityContext());
                    finish();
                }
                return true;
            }
        });
    }


    @Override
    protected MainPresenter createPresenterInstance() {
        return new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        h.sendEmptyMessageDelayed(MESSAGE_NEXT, 2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        h.removeMessages(MESSAGE_NEXT);
    }
}
