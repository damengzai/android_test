package com.example.ma.testapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.ma.testapp.R;

public class vpItem extends RelativeLayout {
    private Context context;
    public vpItem(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public vpItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public vpItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }
    private void initView(){
        LayoutInflater.from(getContext()).inflate(R.layout.vpitem, this);
    }
}
