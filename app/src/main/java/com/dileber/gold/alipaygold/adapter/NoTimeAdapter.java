package com.dileber.gold.alipaygold.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dileber.gold.alipaygold.R;
import com.dileber.gold.alipaygold.data.model.response.TouTiaoResponse;
import com.drcosu.ndileber.tools.UTime;
import com.drcosu.ndileber.view.recycle.DileberAdapter;
import com.drcosu.ndileber.view.recycle.DileberHolder;

/**
 * Created by hyy on 2018/3/2.
 */
public class NoTimeAdapter extends DileberAdapter<NoTimeAdapter.VH,TouTiaoResponse.Data.Items>{


    public NoTimeAdapter(Context context) {
        super(context);
    }

    @Override
    protected void addViewHolderData(VH holder, int position) {
        holder.model = mModel.get(position);
    }

    @Override
    public DileberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
        return new VH(contentView);
    }

    public class VH extends DileberHolder<TouTiaoResponse.Data.Items> {
        TextView time,content,title;
        public VH(View itemView) {
            super(itemView);
            time = findView(R.id.timem);
            content = findView(R.id.content);
            title = findView(R.id.title);
        }

        @Override
        public void load(Context context) {
            time.setText(UTime.getDateStr(UTime.Pattern.y_m_d_h_m_s,model.resource.display_time*1000));
            content.setText(model.resource.content_short);
            title.setText(model.resource.title);

//            http://m-prod.goldtoutiao.com/articles/3241897
        }
    }
}
