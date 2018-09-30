package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ma.testapp.view.NestedScrollView;

/**
 * Created by shumengma on 2018/9/29.
 */

public class ScrollViewActivity extends AppCompatActivity {
    private NestedScrollView sv_root;
    private LinearLayout ll_content;
    private ImageView img_top;
    private FrameLayout fl_img_content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_main);
        sv_root = (NestedScrollView) findViewById(R.id.sv_root);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        img_top = (ImageView) findViewById(R.id.img_top);
        fl_img_content = (FrameLayout) findViewById(R.id.fl_img_content);
        initView();
    }

    private void initView(){
//        fl_img_content.setBottom(2);
        sv_root.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= 0) {//往上滑动
                    Log.e("----", "onScrollChange: ------上滑");
                } else {//下拉滑动
//                    img_top.scrollTo(0,0);

//                    fl_img_content.setTop( - scrollY);
//                    fl_img_content.setBottom(-scrollY + 800);
//                    FrameLayout.LayoutParams paramsNor = img_top.getLayoutParams();
                    FrameLayout.MarginLayoutParams margin = new FrameLayout.MarginLayoutParams(img_top.getLayoutParams());
                    margin.topMargin =  -700 - scrollY;
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(margin);
//                    params.height = 800;
//                    params.width = FrameLayout.LayoutParams.WRAP_CONTENT;
                    img_top.setLayoutParams(params);


//                    ViewGroup.LayoutParams params = fl_img_content.getLayoutParams();
//                    params.height = - scrollY;
//                    fl_img_content.setLayoutParams(params);
                    Log.e("----", "onScrollChange: ------下拉scrollY:"+scrollY+"ll_content.getPaddingTop():"+ll_content.getPaddingTop());
                }
            }
        });
    }
}
