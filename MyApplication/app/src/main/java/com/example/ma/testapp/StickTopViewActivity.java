package com.example.ma.testapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


/**
 * Created by shumengma on 2018/10/8.
 */

public class StickTopViewActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private NestedScrollView nestedScrollView;
    private AppBarLayout appBarLayout;
    private Toolbar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick_top_view);
        collapsingToolbarLayout = findViewById(R.id.ctl);
        nestedScrollView = findViewById(R.id.nsv);
        appBarLayout = findViewById(R.id.abl);
        toolBar = findViewById(R.id.toolbar);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimary));


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("----------", "onScrollChange: scrollY"+scrollY);
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("---------", "onOffsetChanged: verticalOffset"+verticalOffset);
                if (verticalOffset < -240){
                    collapsingToolbarLayout.setTitle("Title");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.cardview_light_background));
                }else {
                    collapsingToolbarLayout.setTitle("");
                }
            }
        });
    }
}
