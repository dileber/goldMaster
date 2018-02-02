package com.dileber.gold.alipaygold.utils;

import android.graphics.Color;
import android.text.Html;

import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.drcosu.ndileber.dileberui.diagram.lineschart.QuXianChatView;
import com.drcosu.ndileber.tools.TNum;
import com.drcosu.ndileber.tools.log.ULog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by hyy on 2018/2/1.
 */
public class UGold {

    public List<Float> value = new ArrayList<>();
    public List<String> key = new ArrayList<>();
    float max = Float.MIN_VALUE,min = Float.MAX_VALUE;
    public final List<Float> lastSixDate = new ArrayList<>();
    public final List<Float> lastTwoDate = new ArrayList<>();
    public final List<Float> lastMonthDate = new ArrayList<>();
    public float oneMonthMax = Float.MIN_VALUE;
    public float oneMonthMin= Float.MAX_VALUE;
    public float halfYearMax= Float.MIN_VALUE;
    public float halfYearMin= Float.MAX_VALUE;
    public float nowZhangFu;
    public float now = 0;
    public float zd = 0;
    public int zhangci = 0;
    public int dieci = 0;
    public boolean zhangciAble = true;
    public boolean dieciAble = true;
    public int tm = 6;
    public int tn = 2;
    public int tz = 30;

    public void initChat(FundNetValueResponse fundNetValueResponse) {
        Collections.reverse(fundNetValueResponse.data.resultList);
        for(int i=0;i<fundNetValueResponse.data.resultList.size();i++){
            for(Map.Entry<String,String> m :fundNetValueResponse.data.resultList.get(i).entrySet()){
                key.add(m.getKey());
                float f = Float.parseFloat(m.getValue())*100;
                value.add(f);
                if(max<f){
                    max = f;
                }
                if(min>f){
                    min = f;
                }
            }
        }
        initZhangShi(fundNetValueResponse.data.resultList);
    }

    private void initZhangShi(List<Map<String, String>> resultList) {
        Collections.reverse(resultList);
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


//        lianzhang.setText(zhangci+"日");
//        liandie.setText(dieci+"日");
//        monthMax.setText(oneMonthMax+"元");
//        monthMin.setText(oneMonthMin+"元");
//
//        halfMax.setText(halfYearMax+"元");
//        halfMin.setText(halfYearMin+"元");

    }

    public CharSequence getJinZhangFu(){
        nowZhangFu = lastTwoDate.get(0)-lastTwoDate.get(1);
        if(nowZhangFu>0){
            return (Html.fromHtml("<font color = '#af0c0f'>"+nowZhangFu+"</font>"));
        }else{
            return (Html.fromHtml("<font color = '#3d9f1c'>"+nowZhangFu+"</font>"));
        }
    }

    public String getTip(){
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
        return t_s.toString();
    }

}
