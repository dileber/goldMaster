package com.dileber.gold.alipaygold.goldmbp.oldhome;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.model.response.FundNetValueResponse;
import com.dileber.gold.alipaygold.data.source.MainRepository;
import com.dileber.gold.alipaygold.view.ItemNameTextView;
import com.drcosu.ndileber.dileberui.diagram.lineschart.QuXianChatView;
import com.drcosu.ndileber.mvp.ubase.UBaseFragment;
import com.drcosu.ndileber.tools.TNum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 自动生成：by dileber.
 */


public class OldHomeFragment extends UBaseFragment<OldHomePresenter> implements OldHomeContract.View<OldHomePresenter> {

    public OldHomeFragment() {
    }


    public static OldHomeFragment newInstance() {
        OldHomeFragment fragment = new OldHomeFragment();
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
    protected OldHomePresenter createPresenterInstance() {
        return new OldHomePresenter(this, MainRepository.getInstance());
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        baseFundChartView = findView(R.id.chat);
        jinzhangfu = findView(R.id.jinzhangfu);
        liandie = findView(R.id.liandie);
        lianzhang = findView(R.id.lianzhang);
        monthMin = findView(R.id.monthMin);
        monthMax = findView(R.id.monthMax);
        halfMax = findView(R.id.halfMax);
        halfMin = findView(R.id.halfMin);
        content = findView(R.id.content);
        mPresenter.fundNetValue();
    }

    QuXianChatView baseFundChartView;

    @Override
    public int layoutViewId() {
        return R.layout.fragment_old_home;
    }

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
}