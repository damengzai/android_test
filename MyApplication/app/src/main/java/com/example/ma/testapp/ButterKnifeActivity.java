package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by shumengma on 2018/10/16.
 */

public class ButterKnifeActivity extends AppCompatActivity {
    @BindView(R.id.btn_butterknife_toast)
    Button button;

    @BindViews({R.id.btn_bk_1, R.id.btn_bk_2})
    List<Button> buttonList;

    @BindString(R.string.fav)
    String fav;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_butterknife_toast)
    void showToast(){
        Toast.makeText(this, "BK", Toast.LENGTH_LONG).show();
    }
    @OnLongClick(R.id.btn_butterknife_toast)
    boolean showToastLong(){
        Toast.makeText(this, "BKLong", Toast.LENGTH_LONG).show();
        return true;
    }
//    @OnClick(R.id.bkb_butterknife)

}
