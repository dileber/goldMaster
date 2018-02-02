package com.dileber.gold.alipaygold;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
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
import com.drcosu.ndileber.tools.TNum;
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

public class MainActivity extends UBaseActivity<Main2Presenter> implements Main2Contract.View<Main2Presenter>, OnChartValueSelectedListener {

    public static void start(Context context) {
        LauncherManager.launcher.launch(context, Main2Activity.class);
    }

    @Override
    public int layoutViewId() {
        return R.layout.activity_main2;
    }
    BarChart mChart;
    LineChart line_chart;
    @Override
    protected void initView(Bundle savedInstanceState) {
        UToolBar uToolBar = new UToolBar();
        uToolBar.setTitleString("黄金分析师");
        uToolBar.setNeedNavigate(false);
        setToolBar(R.id.toolbar, uToolBar);
        baseFundChartView = findView(R.id.chat);
        jinzhangfu = findView(R.id.jinzhangfu);
        liandie = findView(R.id.liandie);
        lianzhang = findView(R.id.lianzhang);
        monthMin = findView(R.id.monthMin);
        monthMax = findView(R.id.monthMax);
        halfMax = findView(R.id.halfMax);
        halfMin = findView(R.id.halfMin);
        content = findView(R.id.content);
        mChart = findViewById(R.id.chart);
        line_chart = findViewById(R.id.line_chart);

        mChart.setOnChartValueSelectedListener(this);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setDrawBarShadow(false);//--绘制当前展示的内容顶部阴影
        mChart.setDrawValueAboveBar(true);//--绘制的图形都在bar顶部

        mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);//Y方向的最大值.

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false); //--双指缩放.

        mChart.setDrawGridBackground(false);//--绘制中心内容区域背景色.
        // mChart.setDrawYLabels(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);//--是否绘制竖直分割线.
        xAxis.setGranularity(1f); // only intervals of 1 day  底部label的分割间隙
        xAxis.setLabelCount(7);//--对应的当前绘制在底部的label数
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(4, false);  //--绘制Y方向(应该)被显示的数量，第二个参数表示label是否是精准变化，还是近似变化
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
//        leftAxis.setValueFormatter(custom);

        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

//        rightAxis.setDrawGridLines(false); //-绘制水平分割线，按照当前Y方向的label点为起始点
//        rightAxis.setLabelCount(8, false);
//        rightAxis.setSpaceTop(15f);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)Y方向的起始值


        Legend l = mChart.getLegend();
        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(Legend.LegendForm.SQUARE);//--设置legend的形状.
//        l.setFormSize(9f); //--设置legend的大小
//        l.setTextSize(11f); //--设置legend上的文字大小
//        l.setXEntrySpace(4f);
        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

        setData(7, 100);

        mPresenter.fundNetValue();
        
        initChart();

        ActivityUtils.getFragment(getSupportFragmentManager(),R.id.m, HistoryFragment.newInstance());
    }

    private void initChart() {
//        line_chart.setOnChartValueSelectedListener(this);
        line_chart.setDrawGridBackground(false);

        // no description text
        line_chart.getDescription().setEnabled(false);

        // enable touch gestures
        line_chart.setTouchEnabled(true);

        // enable scaling and dragging
        line_chart.setDragEnabled(false);
        line_chart.setScaleEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        line_chart.setPinchZoom(false);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it


        XAxis xl = line_chart.getXAxis();
        xl.setAvoidFirstLastClipping(true);
        xl.setAxisMinimum(0f);
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawGridLines(false);//--是否绘制竖直分割线.

        YAxis leftAxis = line_chart.getAxisLeft();
        leftAxis.setInverted(true);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = line_chart.getAxisRight();
        rightAxis.setEnabled(false);

        // add data
        setData2(8, 50);

        // // restrain the maximum scale-out factor
        // mChart.setScaleMinima(3f, 3f);
        //
        // // center the view to a specific position inside the chart
        // mChart.centerViewPort(10, 50);

        // get the legend (only possible after setting data)
//        Legend l = line_chart.getLegend();
//
//        // modify the legend ...
//        l.setForm(Legend.LegendForm.LINE);

        // dont forget to refresh the drawing
        line_chart.invalidate();
    }

    private void setData2(int count, float range) {

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float xVal = (float) (Math.random() * range);
            float yVal = (float) (Math.random() * range);
            entries.add(new Entry(xVal, yVal));
        }

        // sort by x-value
        Collections.sort(entries, new EntryXComparator());

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(entries, "DataSet 1");

        set1.setLineWidth(1.5f);
        set1.setCircleRadius(8f);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        // set data
        line_chart.setData(data);
    }

    @Override
    protected Main2Presenter createPresenterInstance() {
        return new Main2Presenter(this, MainRepository.getInstance());
    }

    QuXianChatView baseFundChartView;

    @Override
    public void showChat(FundNetValueResponse fundNetValueResponse) {
        Collections.reverse(fundNetValueResponse.data.resultList);
        final List<Float> floats = new ArrayList<>();
        List<String> x = new ArrayList<>();
        final List<String> time = new ArrayList<>();
        float max = Float.MIN_VALUE,min = Float.MAX_VALUE;
        for(int i=0;i<fundNetValueResponse.data.resultList.size();i++){
            for(Map.Entry<String,String> m :fundNetValueResponse.data.resultList.get(i).entrySet()){

                if(i%(fundNetValueResponse.data.resultList.size()/4)==0){
                    x.add(m.getKey());
                }else{
                    x.add(null);
                }
                time.add(m.getKey());
                float f = Float.parseFloat(m.getValue())*100;
                floats.add(f);
                if(max<f){
                    max = f;
                }
                if(min>f){
                    min = f;
                }
            }
        }


        List<List<Float>> ff = new ArrayList<>();
        ff.add(floats);
        List<String> text = new ArrayList<>();

        baseFundChartView.setRefData(true);
        baseFundChartView.setText(text);
        baseFundChartView.setOnFundChat(new QuXianChatView.OnFundChat() {
            @Override
            public void onRefChatText(QuXianChatView view, int x) {
                if(x<floats.size()){
                    List<String> list = new ArrayList<String>();
                    list.add("时间:"+time.get(x));
                    list.add("价格:"+floats.get(x));
                    view.setText(list);
                }
            }
        });
        baseFundChartView.setFormat(TNum.moneyFormat(2,true));
        baseFundChartView.setyCount(5);
        baseFundChartView.setyStart(min);
        baseFundChartView.setyStop(max);
        baseFundChartView.setDateX(x);
        baseFundChartView.setData(ff);
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(55,161,236));
        baseFundChartView.setColors(colors);
        baseFundChartView.invalidate();

        initZhangShi(fundNetValueResponse.data.resultList);
    }

    private void initZhangShi(List<Map<String, String>> resultList) {
        Collections.reverse(resultList);
        final List<Float> lastSixDate = new ArrayList<>();
        final List<Float> lastTwoDate = new ArrayList<>();
        final List<Float> lastMonthDate = new ArrayList<>();
        float oneMonthMax = Float.MIN_VALUE;
        float oneMonthMin= Float.MAX_VALUE;
        float halfYearMax= Float.MIN_VALUE;
        float halfYearMin= Float.MAX_VALUE;
        float nowZhangFu;
        float now = 0;

        float zd = 0;

        int zhangci = 0;
        int dieci = 0;

        boolean zhangciAble = true;
        boolean dieciAble = true;
        int tm = 6;
        int tn = 2;
        int tz = 30;
        for(int i=0;i<resultList.size();i++){

            for(Map.Entry<String,String> m :resultList.get(i).entrySet()){
                float f = Float.parseFloat(m.getValue())*100;
                if(now==0){
                    now = f;
                }

                if(tm>=0){
                    lastSixDate.add(f);
                    tm -- ;
                }
                if(tn>=0){
                    lastTwoDate.add(f);
                    tn -- ;
                }
                if(tz>=0){
                    if(oneMonthMax<f){
                        oneMonthMax = f;
                    }
                    if(oneMonthMin>f){
                        oneMonthMin = f;
                    }
                    lastMonthDate.add(f);
                    tz -- ;
                }

                if(halfYearMax<f){
                    halfYearMax = f;
                }
                if(halfYearMin>f){
                    halfYearMin = f;
                }

                if(zd!=0){
                    if(zhangciAble){
                        if(f<zd){
                            zhangci++;
                        }else{
                            zhangciAble = false;
                        }
                    }

                    if(dieciAble){
                        if(f>zd){
                            dieci++;
                        }else{
                            dieciAble = false;
                        }
                    }

                }

                zd = f;


            }
        }
        nowZhangFu = lastTwoDate.get(0)-lastTwoDate.get(1);
        if(nowZhangFu>0){
            jinzhangfu.setText(Html.fromHtml("<font color = '#af0c0f'>"+nowZhangFu+"</font>"));
        }else{
            jinzhangfu.setText(Html.fromHtml("<font color = '#3d9f1c'>"+nowZhangFu+"</font>"));
        }

        lianzhang.setText(zhangci+"日");
        liandie.setText(dieci+"日");
        monthMax.setText(oneMonthMax+"元");
        monthMin.setText(oneMonthMin+"元");

        halfMax.setText(halfYearMax+"元");
        halfMin.setText(halfYearMin+"元");

        float t = (halfYearMax-now)-(now-halfYearMin);
        StringBuffer t_s = new StringBuffer("");
        float m = (oneMonthMax-now)-(now-oneMonthMin);
        if(m>0){
            t_s.append("30天内同比上升，短期考虑不宜投；");
        }else{
            t_s.append("30天内同比下降，短期考虑宜投；");
        }
        if(t>0){
            t_s.append("半年同比上升，长期考虑不宜投；");
        }else{
            t_s.append("半年同比下降，长期考虑宜投；");
        }
        if(zhangci==3){
            t_s.append("连张3日，上升期，看好；");
        }else if(zhangci>=5){
            t_s.append("连张多日，上升期，小心谨慎；");
        }

        if(dieci==3){
            t_s.append("连跌3日，危险期；");
        }else if(dieci>=5){
            t_s.append("连跌多日，等待抄底；");
        }

        if(now-halfYearMin<=1){
            t_s.append("特殊提醒：今日最宜投资；");
        }
        if(halfYearMax-now<=1){
            t_s.append("特殊提醒：今日最不宜投资；");
        }
        content.setText(t_s.toString());
    }

    ItemNameTextView jinzhangfu;
    ItemNameTextView lianzhang;
    ItemNameTextView liandie;
    ItemNameTextView monthMax;
    ItemNameTextView monthMin;
    ItemNameTextView halfMax;
    ItemNameTextView halfMin;
    TextView content;


    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            yVals1.add(new BarEntry(i, val));

//            if (Math.random() * 100 < 25) {
//                yVals1.add(new BarEntry(i, val, getResources().getDrawable(R.mipmap.debug_android)));
//            } else {
//                yVals1.add(new BarEntry(i, val));
//            }
        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "XX月班级数量 XX\r\n" +
                    "XX月班级总人数 XXX");

            set1.setDrawIcons(false);

            set1.setColors(Color.parseColor("#037cff"));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.6f);

            mChart.setData(data);
        }
    }

    protected RectF mOnValueSelectedRectF = new RectF();

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() { }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
