package com.example.ma.testapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by liumeng on 2018/5/28.
 */

public class HotelInternetShareView extends LinearLayout implements View.OnClickListener{

    private Dialog dialog;

    private FragmentActivity mActivity;
    private String tag = "MERGED_DIALOG_TAG";;

    private List<String> descContent;
    private static String QAVOrderNo;
    private static boolean QAVIsRN;
    private static final String CITY_CARD_SHOW = "CityCardShow";
    private static final String CITY_CARD_CLICK = "CityCardClick";
    private static final String CITY_CARD_SHARE_CLICK = "CityCardShareClick";
    public HotelInternetShareView(Context context, int height, Dialog dialog) {
        super(context);
        this.dialog=dialog;
        init(height);
        QAVTrigger(CITY_CARD_SHOW,null);
    }
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static  void showView(final Activity activity, final boolean isRN, String orderNo){
        QAVOrderNo = orderNo;
        QAVIsRN = isRN;
        Dialog dialog = new Dialog(activity, R.style.atom_hotel_rc_translucent);
        final Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        final RelativeLayout dialogLayout = new RelativeLayout(activity);
        HotelInternetShareView hotelInternetShareView=new HotelInternetShareView(activity,200,dialog);
//        hotelInternetShareView.setContent(cityShareInfo);
        dialogLayout.addView(hotelInternetShareView);
//                dialogLayout.setBackgroundColor(0xF0FFFFFF);
        dialog.setContentView(dialogLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(isRN){
                    activity.finish();
                }

            }
        });
    }


    public void init(int height){
        mActivity=(FragmentActivity) getContext();
        View.inflate(mActivity, R.layout.atom_hotel_hotel_internet_share, this);


    }

    @Override
    public void onClick(View view) {
        if(mActivity==null){
            return;
        }
        Bitmap shareBitmap=null;
        Intent sendIntent = new Intent("android.intent.action.SEND", (Uri)null);
        sendIntent.setType("text/plain");
        String channelKeyStr=null;

    }
    /**
     * @param shareType String 点击分享的时候，分享的类型朋友圈：wxpyq，微信好友：wxhy，微博：weibo
     * */
    private void QAVTrigger(String QAVKey, String shareType){

    }
}
