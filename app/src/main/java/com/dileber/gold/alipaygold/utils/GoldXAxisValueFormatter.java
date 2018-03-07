package com.dileber.gold.alipaygold.utils;

import com.drcosu.ndileber.tools.UTime;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hyy on 2018/2/1.
 */
public class GoldXAxisValueFormatter implements IAxisValueFormatter {
    private List<String> labels;

    /**
     * @param labels 要显示的标签字符数组
     */
    public GoldXAxisValueFormatter(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if(labels.size()==0){
            return "";
        }
        String s = labels.get((int)value);
        return s.substring(s.length()-8,s.length());
    }
}
