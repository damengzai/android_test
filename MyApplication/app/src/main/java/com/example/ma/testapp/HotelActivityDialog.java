package com.example.ma.testapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Date;

/**
 * Created by shumengma on 2018/9/26.
 */

public class HotelActivityDialog extends Dialog implements View.OnClickListener {

    private Context context;
    //时间间隔
    private int interval;
    //展示的图片的url
    private String imgUrl;
    //跳转scheme
    private String jumpScheme;

    public HotelActivityDialog(@NonNull Context context) {
        super(context,R.style.atom_hotel_red_envelop_dialog);
    }

    public HotelActivityDialog(@NonNull Context context, int interval, String imgUrl, String jumpScheme){
        super(context, R.style.atom_hotel_red_envelop_dialog);
        this.context = context;
        this.interval = interval;
        this.imgUrl = imgUrl;
        this.jumpScheme = jumpScheme;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        setContentView(R.layout.atom_hotel_activity_dialog);

//        Injector.inject(this);

        //最外层容器
        LinearLayout container = (LinearLayout) findViewById(R.id.atom_hotel_container_ll);
        //展示图

        ImageView content = (ImageView) findViewById(R.id.atom_hotel_content_sdv);
        //关闭按钮
//        @From(R.id.atom_hotel_close_ftv)
//        FontTextView close;

        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.width =(int) (params.width );
//        params.height = (int) (params.height * 0.6);
//        content.setMaxWidth((int)(params.width* 0.4));
//        content.setMaxHeight((int)(params.height * 0.2));
        WindowManager wm = this.getWindow().getWindowManager();
        Display d = wm.getDefaultDisplay();

        Log.e("--------", "onCreate: "+ d.getWidth()+"----"+d.getHeight());
//        Rect outSize = new Rect();
//        d.getRectSize(outSize);
        Point p = new Point();
        d.getSize(p);
        LinearLayout.LayoutParams contentParam = new LinearLayout.LayoutParams((int)(p.x* 0.8),(int)(p.y * 0.6));
        content.setLayoutParams(contentParam);

        getWindow().setLayout((int)(d.getWidth()* 0.8),(int)(d.getHeight() * 0.6));

//        content.setMaxWidth((int)(d.getWidth()* 0.8));
//        content.setMaxHeight((int)(d.getHeight() * 0.6));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setGravity(Gravity.CENTER);
//        content.setImageUrl(imgUrl);
//        content.setOnClickListener(this);
//        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.atom_hotel_content_sdv){
//            //点击图片，跳转
//            SchemeDispatcher.sendScheme(context, jumpScheme);
//        } else if (view.getId() == R.id.atom_hotel_close_ftv){
//            //点击关闭按钮
//            if (isShowing()){
//                dismiss();
//                HotelSharedPreferncesUtil.putPreferences(HotelSharedPreferncesUtil.TAG_ACTIVITY_DIALOG,new Date().getTime());
//            }
//        }
    }
    public void showActivity(showRedEnvelopDialogInter inter){
//        long lastShowTime = HotelSharedPreferncesUtil.getPreferences(HotelSharedPreferncesUtil.TAG_ACTIVITY_DIALOG, 0l);
//        long currentTime = new Date().getTime();
//        if (currentTime - lastShowTime > interval){
            show();
//            HotelSharedPreferncesUtil.putPreferences(HotelSharedPreferncesUtil.TAG_ACTIVITY_DIALOG,currentTime);
//        } else {
//            //不显示活动弹窗，调用原来显示红包的逻辑
//            if (inter != null){
//                inter.showRedEnvelop();
//            }
//        }
    }
    public interface showRedEnvelopDialogInter{
        void showRedEnvelop();
    }
}
