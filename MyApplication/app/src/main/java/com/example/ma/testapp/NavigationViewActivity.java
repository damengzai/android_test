package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shumengma on 2018/9/30.
 */

public class NavigationViewActivity extends AppCompatActivity {
    private NavigationView nv;
    private LinearLayout headerLayout;
    private ImageView header;
    private DrawerLayout dl;
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        nv = (NavigationView) findViewById(R.id.navigation_view);
        nv.setItemIconTintList(null);
        dl = (DrawerLayout) findViewById(R.id.drawer);

        tv = (TextView) findViewById(R.id.nav_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(Gravity.LEFT, true);
            }
        });

        headerLayout = (LinearLayout) nv.getHeaderView(0);
        Log.d("----", "onCreate: getChildCount----"+nv.getChildCount());
        header = (ImageView) headerLayout.getChildAt(0);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("----", "onNavigationItemSelected: -------click"+item.getItemId());
                return false;
            }
        });

    }
}
