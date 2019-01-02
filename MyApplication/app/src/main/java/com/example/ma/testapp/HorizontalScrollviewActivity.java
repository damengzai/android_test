package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HorizontalScrollviewActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scrollview);
        linearLayout = findViewById(R.id.items_linear);
        initData();
    }

    private void initData(){
        for (int i = 0; i < 100; i++){
            TextView tv = new TextView(this);
            tv.setText(i+"------");
            linearLayout.addView(tv);
        }

    }
}
