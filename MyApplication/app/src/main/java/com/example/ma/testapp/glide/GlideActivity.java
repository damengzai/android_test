package com.example.ma.testapp.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
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
        RequestOptions options = new RequestOptions()
                .circleCrop()                       //圆形
                .placeholder(R.mipmap.ic_launcher)  //占位图
                .error(R.mipmap.ic_launcher)        //加载失败
                .fallback(R.mipmap.ic_launcher)     //加载的图片为null的时候，显示的后备图
//                .override(100,100)                 //指定大小
                ;

//        Glide
//                .with(this)
//                .asBitmap()                         //作为图片，gif的话会显示第一帧
//                .load("http://img5.imgtn.bdimg.com/it/u=415293130,2419074865&fm=27&gp=0.jpg")
//                .apply(options)
//                .thumbnail(0.1f)                    //缩略图
//                .into(img);

        CustomViewTarget customViewTarget = new CustomViewTarget<ImageView, Bitmap>(img) {
            @Override
            protected void onResourceCleared(@Nullable Drawable placeholder) {

            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {

            }

            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition transition) {
                img.setImageBitmap(resource);
            }
        };
        Glide
                .with(this)
                .asBitmap()
                .load("http://img5.imgtn.bdimg.com/it/u=415293130,2419074865&fm=27&gp=0.jpg")
                .apply(options)
                .into(customViewTarget);
    }
}
