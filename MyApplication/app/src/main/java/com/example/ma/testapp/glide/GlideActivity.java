package com.example.ma.testapp.glide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ma.testapp.R;

/**
 * Created by shumengma on 2018/9/30.
 */

public class GlideActivity extends AppCompatActivity {
    private ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        img = findViewById(R.id.img);
        initData();
    }
    private void initData(){
        Glide.with(this).load("http://img5.imgtn.bdimg.com/it/u=415293130,2419074865&fm=27&gp=0.jpg").into(img);
    }
}
