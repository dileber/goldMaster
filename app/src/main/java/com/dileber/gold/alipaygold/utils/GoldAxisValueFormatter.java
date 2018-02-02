package com.dileber.gold.alipaygold.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by hyy on 2018/2/1.
 */
public class GoldAxisValueFormatter implements IAxisValueFormatter {
    private List<String> labels;

    /**
     * @param labels 要显示的标签字符数组
     */
    public GoldAxisValueFormatter(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return labels.get((int) value);
    }
}
