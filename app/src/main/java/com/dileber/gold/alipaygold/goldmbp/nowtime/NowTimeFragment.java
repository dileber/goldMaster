package com.dileber.gold.alipaygold.goldmbp.nowtime;


import android.content.Context;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.os.Handler;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.model.CommodityNo;
import com.dileber.gold.alipaygold.data.model.response.GetFuturesQuoteResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.dileber.gold.alipaygold.utils.GoldAxisValueFormatter;
import com.dileber.gold.alipaygold.utils.GoldXAxisValueFormatter;
import com.dileber.gold.alipaygold.utils.GoldYAxisValueFormatter;
import com.dileber.gold.alipaygold.utils.UChart;
import com.dileber.gold.alipaygold.utils.UGold;
import com.dileber.gold.alipaygold.view.MyMarkerView;
import com.drcosu.ndileber.mvp.acivity.BaseActivity;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;
import com.drcosu.ndileber.tools.TNum;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.tools.log.ULog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 自动生成：by dileber.
 */


public class NowTimeFragment extends UBaseFragment<NowTimePresenter> implements NowTimeContract.View<NowTimePresenter> {

    public NowTimeFragment() {
    }


    public static NowTimeFragment newInstance() {
        NowTimeFragment fragment = new NowTimeFragment();
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
    protected NowTimePresenter createPresenterInstance() {
        return new NowTimePresenter(this, MainRepository.getInstance());
    }

    private LineChart gcChart,auChart;

    MyMarkerView au,gc;
    @Override
    protected void initView(Bundle savedInstanceState) {
        auChart =  findView(R.id.auChart);
        UChart.setBarChartBase(getActivityContext(),auChart);
        au = new MyMarkerView(getActivityContext(), R.layout.gold_maker_view);
        au.setChartView(auChart); // For bounds control
        auChart.setMarker(au); // Set the marker to the chart
        XAxis xAxis = auChart.getXAxis();
        xAxis.setValueFormatter(new GoldXAxisValueFormatter(aus));


        gcChart =  findView(R.id.gcChart);
        UChart.setBarChartBase(getActivityContext(),gcChart);
        gc = new MyMarkerView(getActivityContext(), R.layout.gold_maker_view);
        gc.setChartView(gcChart); // For bounds control
        gcChart.setMarker(gc); // Set the marker to the chart
        XAxis xAxis2 = gcChart.getXAxis();
        xAxis2.setValueFormatter(new GoldXAxisValueFormatter(gcs));

        mPresenter.getFuturesQuote(CommodityNo.AU);
        mPresenter.getFuturesQuote(CommodityNo.GC);

    }

    Handler handler=new Handler();
    Runnable runnable=new Runnable(){
        @Override
        public void run() {
            mPresenter.getFuturesQuote(CommodityNo.AU);
            mPresenter.getFuturesQuote(CommodityNo.GC);
            handler.postDelayed(this, 2000);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public int layoutViewId() {
        return R.layout.fragment_now_time;
    }

    volatile List<String> aus = new ArrayList<>(),gcs = new ArrayList<>();

    @Override
    public void showFuturesQuote(GetFuturesQuoteResponse fundNetValueResponse,CommodityNo commodityNo) {
//        1519952400000,272.30000000000001136868377216160297393798828125;1519952460000,272.19999999999998863131622783839702606201171875;

        String[] s = fundNetValueResponse.data.split(";");
        switch (commodityNo){
            case AU:
                aus.clear();
                ArrayList<Entry> values = new ArrayList<Entry>();
                for (int i = 0; i < s.length; i++) {
                    String[] ms = s[i].split(",");
                    aus.add(UTime.getDateStr(UTime.Pattern.y_m_d_h_m_s,Long.parseLong(ms[0])));
                    float value = new BigDecimal(Double.parseDouble(ms[1])).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

                    values.add(new Entry(i, value));
                }

                au.setTime(aus);


                UChart.chartData(auChart,values);
                break;
            case GC:
                gcs.clear();

                ArrayList<Entry> values2 = new ArrayList<Entry>();
                for (int i = 0; i < s.length; i++) {
                    String[] ms = s[i].split(",");
                    gcs.add(UTime.getDateStr(UTime.Pattern.y_m_d_h_m_s,Long.parseLong(ms[0])));
                    float value = new BigDecimal(Double.parseDouble(ms[1])).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

                    values2.add(new Entry(i, value));
                }
                gc.setTime(gcs);


                UChart.chartData(gcChart,values2);
                break;
        }
    }
}