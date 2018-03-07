package com.dileber.gold.alipaygold.utils;

import com.drcosu.ndileber.tools.TNum;
import com.drcosu.ndileber.tools.UTime;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by hyy on 2018/2/1.
 */
public class GoldYAxisValueFormatter implements IAxisValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return TNum.getDecimal(value,2,true)+"元";
    }
}
