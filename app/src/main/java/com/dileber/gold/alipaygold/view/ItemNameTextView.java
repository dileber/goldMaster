package com.dileber.gold.alipaygold.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dileber.gold.alipaygold.R;

/**
 * Created by hyy on 2017/12/20.
 */

public class ItemNameTextView extends FrameLayout {
    TextView nameInfo;
    TextView value;

    public ItemNameTextView(@NonNull Context context) {
        this(context, null);
    }

    public ItemNameTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    Context mContext;
    public ItemNameTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_name_text, this, true);
        nameInfo = (TextView) findViewById(R.id.nameT);
        value = (TextView) findViewById(R.id.valueT);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemNameTextView);

        String niname = a.getString(R.styleable.ItemNameTextView_ntName);

        nameInfo.setText(niname);
        a.recycle();

    }


    public void setText(CharSequence text){
        value.setText(text);
    }
    public void setName(String text){
        nameInfo.setText(text);
    }


}
