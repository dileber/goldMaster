package com.dileber.gold.alipaygold.goldmbp.home;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.adapter.NoTimeAdapter;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.dileber.gold.alipaygold.utils.GoldAxisValueFormatter;
import com.dileber.gold.alipaygold.utils.GoldYAxisValueFormatter;
import com.dileber.gold.alipaygold.utils.UGold;
import com.dileber.gold.alipaygold.view.ItemNameTextView;
import com.dileber.gold.alipaygold.view.MyMarkerView;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HomeFragment extends UBaseFragment<HomePresenter> implements HomeContract.View<HomePresenter> {

    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
    protected HomePresenter createPresenterInstance() {
        return new HomePresenter(this, MainRepository.getInstance());
    }
//    ItemNameTextView jinzhangfu;
//    ItemNameTextView lianzhang;
//    ItemNameTextView liandie;
//    ItemNameTextView monthMax;
//    ItemNameTextView monthMin;
//    ItemNameTextView halfMax;
//    ItemNameTextView halfMin;
//    TextView content;
    private LineChart mChart;
    MyMarkerView mv;
    NoTimeAdapter noTimeAdapter;
    @Override
    protected void initView(Bundle savedInstanceState) {

        recyclerView = findView(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
        noTimeAdapter = new NoTimeAdapter(getActivityContext());
        recyclerView.setAdapter(noTimeAdapter);

//        jinzhangfu = findView(R.id.jinzhangfu);
//        liandie = findView(R.id.liandie);
//        lianzhang = findView(R.id.lianzhang);
//        monthMin = findView(R.id.monthMin);
//        monthMax = findView(R.id.monthMax);
//        halfMax = findView(R.id.halfMax);
//        halfMin = findView(R.id.halfMin);
//        content = findView(R.id.content);
        mChart =  findView(R.id.fundChart);
        mChart.setDrawGridBackground(false);
        // no description text
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false); //--双指缩放.

        mChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        mChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(true);打开或关闭对图表所有轴的的缩放。

        mv = new MyMarkerView(getActivity(), R.layout.gold_maker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);//--是否绘制竖直分割线.
        xAxis.setGranularity(1f); // only intervals of 1 day  底部label的分割间隙
        xAxis.setLabelCount(4);//--对应的当前绘制在底部的label数

        IAxisValueFormatter goldYAxisValueFormatter = new GoldYAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);  //--绘制Y方向(应该)被显示的数量，第二个参数表示label是否是精准变化，还是近似变化
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setValueFormatter(goldYAxisValueFormatter);



        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);



        mChart.animateX(2500);
        mPresenter.fundNetValue();
        mPresenter.toutiao();
    }

    RecyclerView recyclerView;

    @Override
    public int layoutViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showChat(FundNetValueResponse fundNetValueResponse) {
        ArrayList<Entry> values = new ArrayList<Entry>();
        UGold uGold = new UGold();
        uGold.initChat(fundNetValueResponse);
        mv.setTime(uGold.key);
        mChart.getXAxis().setValueFormatter(new GoldAxisValueFormatter(uGold.key));
        for (int i = 0; i < uGold.value.size(); i++) {
            values.add(new Entry(i, uGold.value.get(i)));
        }

        LineDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values,null);

            set1.setDrawIcons(false);

            // set the line to be drawn like this "- - - - - -"
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);


            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setDrawCircleHole(false);
            set1.setColor(ColorTemplate.rgb("#037cff"));
            set1.setCircleColor(ColorTemplate.rgb("#037cff"));
            set1.setLineWidth(1.8f);
            set1.setDrawCircles(true);
            set1.setCircleRadius(2.0f);
            set1.setCubicIntensity(0.2f);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set1.setFillColor(ColorTemplate.rgb("#037cff"));

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
//        jinzhangfu.setText(uGold.getJinZhangFu());
//        lianzhang.setText(uGold.zhangci+"日");
//        liandie.setText(uGold.dieci+"日");
//        monthMax.setText(uGold.oneMonthMax+"元");
//        monthMin.setText(uGold.oneMonthMin+"元");
//
//        halfMax.setText(uGold.halfYearMax+"元");
//        halfMin.setText(uGold.halfYearMin+"元");
//        content.setText(uGold.getTip());

    }

    @Override
    public void showToutiao(List<TouTiaoResponse.Data.Items> items) {
        noTimeAdapter.refresh(items);
    }

}
