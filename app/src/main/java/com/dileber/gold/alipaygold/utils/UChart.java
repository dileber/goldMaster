package com.dileber.gold.alipaygold.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v4.content.ContextCompat;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.view.MyMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by hyy on 2018/3/2.
 */
public class UChart {

    public static LineChart setBarChartBase(Context context, LineChart barChartBase){
        barChartBase.setDrawGridBackground(false);
        // no description text
        barChartBase.getDescription().setEnabled(false);
        barChartBase.setPinchZoom(false); //--双指缩放.

        barChartBase.setScaleXEnabled(false); //是否可以缩放 仅x轴
        barChartBase.setScaleYEnabled(false); //是否可以缩放 仅y轴
        barChartBase.setDragEnabled(true);


        XAxis xAxis = barChartBase.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);//--是否绘制竖直分割线.
        xAxis.setGranularity(1f); // only intervals of 1 day  底部label的分割间隙
        xAxis.setLabelCount(4);//--对应的当前绘制在底部的label数


        YAxis leftAxis = barChartBase.getAxisLeft();
        leftAxis.setLabelCount(5, false);  //--绘制Y方向(应该)被显示的数量，第二个参数表示label是否是精准变化，还是近似变化
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setValueFormatter(new GoldYAxisValueFormatter());



        YAxis rightAxis = barChartBase.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = barChartBase.getLegend();

        l.setEnabled(false);
        barChartBase.animateXY(1000, 1000);
        return barChartBase;
    }

    public static void chartData(LineChart barChartBase,ArrayList<Entry> values){
        LineDataSet set1;

        if (barChartBase.getData() != null &&
                barChartBase.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)barChartBase.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barChartBase.getData().notifyDataChanged();
            barChartBase.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values,null);

            set1.setDrawIcons(false);

//            // set the line to be drawn like this "- - - - - -"
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);

//            lineDataSet.setLineWidth(1.75f); // 线宽
//            lineDataSet.setCircleSize(3f);// 显示的圆形大小
//            lineDataSet.setColor(Color.WHITE);// 显示颜色
//            lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
//            lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色


//            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//            set1.setDrawCircleHole(false);
            set1.setColor(ColorTemplate.rgb("#037cff"));
            set1.setCircleColor(ColorTemplate.rgb("#037cff"));
            set1.setLineWidth(1.8f);// 线宽
            set1.setDrawCircles(false);
            set1.setDrawFilled(true);
            set1.setFillColor(ColorTemplate.rgb("#037cff"));

//            set1.setCircleRadius(2.0f);
//            set1.setCubicIntensity(0.2f);
//            set1.setValueTextSize(9f);
//            set1.setFormLineWidth(1f);
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
//            set1.setFormSize(15.f);



            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            barChartBase.setData(data);
        }
    }

}
