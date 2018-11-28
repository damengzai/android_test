package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

public class TranslateAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView animationTv;
    private Button animationBtn, animationOutBtn, dialogInAnimation;
    private View layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_animation);
        animationTv = findViewById(R.id.tv_animation);
        animationBtn = findViewById(R.id.btn_animation);
        animationOutBtn = findViewById(R.id.btn_out_animation);
        dialogInAnimation = findViewById(R.id.btn_in_dialog);
        layout = findViewById(R.id.poi_detail_layout);
        animationBtn.setOnClickListener(this);
        animationOutBtn.setOnClickListener(this);
        dialogInAnimation.setOnClickListener(this);
        findViewById(R.id.btn_in_layout).setOnClickListener(this);
        findViewById(R.id.btn_out_layout).setOnClickListener(this);
        findViewById(R.id.poi_detail_layout).setOnClickListener(this);
        findViewById(R.id.atom_hotel_poi_detail_name).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_animation:
//                bottomInAnimation.setDuration(400);
                animationTv.clearAnimation();
//                animationTv.startAnimation(bottomInAnimation);
                animationTv.startAnimation(AnimationUtils.loadAnimation(TranslateAnimationActivity.this, R.anim.atom_hotel_dialog_bottom_in));
                animationTv.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_out_animation:
//                bottomOutAnimation.setDuration(400);
                animationTv.clearAnimation();
//                animationTv.startAnimation(bottomOutAnimation);
                animationTv.startAnimation(AnimationUtils.loadAnimation(TranslateAnimationActivity.this,R.anim.atom_hotel_dialog_bottom_out));
                animationTv.setVisibility(View.GONE);
                break;
            case R.id.btn_in_dialog:
                PoiDetailDialog detailDialog = new PoiDetailDialog(TranslateAnimationActivity.this, "POI", "ADDRESS");
                detailDialog.show();
                break;
            case R.id.btn_in_layout:
                layout.clearAnimation();
                layout.startAnimation(AnimationUtils.loadAnimation(TranslateAnimationActivity.this, R.anim.atom_hotel_dialog_bottom_in));
                layout.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_out_layout:
                layout.clearAnimation();
                layout.startAnimation(AnimationUtils.loadAnimation(TranslateAnimationActivity.this,R.anim.atom_hotel_dialog_bottom_out));
                layout.setVisibility(View.GONE);
                break;
            case R.id.poi_detail_layout:
                Log.e("------", "onClick: ");
                break;
            case R.id.atom_hotel_poi_detail_name:
                Log.e("-----", "onClick: atom_hotel_poi_detail_name");
                break;
        }
    }

    TranslateAnimation bottomInAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
    TranslateAnimation bottomOutAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
}
