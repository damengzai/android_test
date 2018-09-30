package com.example.ma.testapp.chipview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ma.testapp.R;

/**
 * Created by shumengma on 2018/9/30.
 */

public class ChipViewActivity extends AppCompatActivity {
    private ChipGroup hot;
    private Chip chip;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chip);
        initView();
        initData();
    }
    private void initView(){
        hot = findViewById(R.id.cg_hot);
        chip = findViewById(R.id.cp);
    }
    private void initData(){
        String[] hotPoint = {"北京国贸","维亚大厦","魏公村","中关村","朝阳门","紫竹桥","四季青桥","西郊机场"};
        for (int i = 0; i < hotPoint.length; i++){
            Chip chip = new Chip(this);
            chip.setText(hotPoint[i]);
            hot.addView(chip);
        }


        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hot.removeViewAt(hot.getChildCount() - 1);
            }
        });
    }
}
