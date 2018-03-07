package com.dileber.gold.alipaygold.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.model.MFund;
import com.dileber.gold.alipaygold.data.model.response.FundHisDetailResponse;
import com.drcosu.ndileber.view.recycle.DileberAdapter;
import com.drcosu.ndileber.view.recycle.DileberHolder;
import com.drcosu.ndileber.view.recycle.LoadMoreAdapter;

/**
 * Created by hyy on 2018/2/2.
 */
public class HistoryAdapter extends LoadMoreAdapter<HistoryAdapter.HistoryVH,FundHisDetailResponse.Data.ResultList> {


    public HistoryAdapter(Context context) {
        super(context);
    }

    @Override
    public HistoryVH onCreateLoadMoreViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_list_gold, parent, false);
        return new HistoryVH(contentView);
    }

    @Override
    public void addOtherViewHoderData(HistoryVH holder, int position) {
        holder.model = mModel.get(position);
    }

    @Override
    public int getValueCount() {
        return mModel.size();
    }

    public class HistoryVH extends DileberHolder<FundHisDetailResponse.Data.ResultList>{

        TextView timeT,valueT,valueT2,valueT3;
        public HistoryVH(View itemView) {
            super(itemView);
            timeT = findView(R.id.timeT);
            valueT = findView(R.id.valueT);
            valueT2 = findView(R.id.valueT2);
            valueT3 = findView(R.id.valueT3);
        }

        @Override
        public void load(Context context) {
            timeT.setText(model.date);
            if(Double.parseDouble(model.rate)>0){
                valueT.setText(Html.fromHtml("<font color = '#af0c0f'>"+model.rate+"%</font>"));
            }else{
                valueT.setText(Html.fromHtml("<font color = '#3d9f1c'>"+model.rate+"%</font>"));
            }
            if(Double.parseDouble(model.weekYield)>0){
                valueT2.setText(Html.fromHtml("<font color = '#af0c0f'>"+model.weekYield+"%</font>"));
            }else{
                valueT2.setText(Html.fromHtml("<font color = '#3d9f1c'>"+model.weekYield+"%</font>"));
            }
            if(Double.parseDouble(model.monthYield)>0){
                valueT3.setText(Html.fromHtml("<font color = '#af0c0f'>"+model.monthYield+"%</font>"));
            }else{
                valueT3.setText(Html.fromHtml("<font color = '#3d9f1c'>"+model.monthYield+"%</font>"));
            }
        }
    }
}
