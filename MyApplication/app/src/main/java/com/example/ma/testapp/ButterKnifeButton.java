package com.example.ma.testapp;


import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.OnClick;

/**
 * Created by shumengma on 2018/10/16.
 */

public class ButterKnifeButton extends Button {
    public ButterKnifeButton(Context context) {
        super(context);
    }

    public ButterKnifeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButterKnifeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick
    public void onClick(){
        Toast.makeText(getContext(), "BKB", Toast.LENGTH_LONG).show();
        Log.d("------", "onClick: -------ButterKnifeButton");
    }
}
