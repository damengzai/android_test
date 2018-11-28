package com.example.ma.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ma.testapp.chipview.ChipViewActivity;
import com.example.ma.testapp.glide.GlideActivity;
import com.example.ma.testapp.mvvm.view.ui.MVVMActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shumengma on 2018/6/21.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private Button showDialog;
    private TextView tvTest;
    private ImageView imgTest;
    private Context mContext;
    private FontTextView order,fav;
    private FrameLayout container;
    private Button toNestedScrollView, showActivityDialog, navigationView, chipView, glide,stick_top_view,webView,butterKnife;
    @BindView(R.id.btn_to_okhttp)
    Button toOkHttp;
    @BindView(R.id.seekbar)
    SeekBar seekBar;
    @BindView(R.id.btn_to_vs)
    Button toVS;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.layout_main);
        showDialog = (Button) findViewById(R.id.btn_show_dialog);
        showDialog.setOnClickListener(this);
        tvTest = (TextView) findViewById(R.id.tv_test);
        imgTest = (ImageView) findViewById(R.id.img_test);
        order = (FontTextView) findViewById(R.id.order);
        fav = (FontTextView) findViewById(R.id.fav);
        container = (FrameLayout) findViewById(R.id.container);
        showActivityDialog = (Button) findViewById(R.id.show_activity_dialog);
        showActivityDialog.setOnClickListener(this);
        toNestedScrollView = (Button) findViewById(R.id.btn_to_nested_scroll_view);
        toNestedScrollView.setOnClickListener(this);
        navigationView = (Button) findViewById(R.id.btn_to_nav_view);
        navigationView.setOnClickListener(this);
        chipView = findViewById(R.id.btn_to_chip);
        chipView.setOnClickListener(this);
        glide = findViewById(R.id.btn_to_glide);
        glide.setOnClickListener(this);
        stick_top_view = findViewById(R.id.stick_top_view);
        stick_top_view.setOnClickListener(this);
        butterKnife = findViewById(R.id.btn_to_butterKnife);
        butterKnife.setOnClickListener(this);
        webView = findViewById(R.id.btn_to_wv);
        webView.setOnClickListener(this);

        ButterKnife.bind(this);


        container.addView(new BoardTextView(this));
//        tvTest.setTextColor(-43691);
//        Log.d("------", "onCreate: "+ Color.parseColor("#FFFF5555"));
        tvTest.setText(Html.fromHtml("<font color=\"#FF6E6E\" size=\"40\">降</font> 浏览，收藏"));

        String jiangFavCollStr = mContext.getResources().getString(R.string.fav);
        SpannableString spannableString = new SpannableString(jiangFavCollStr);
        spannableString.setSpan(new AbsoluteSizeSpan(19, true), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new CustomVerticalCenterSpan(), 1, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.test_c)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AlignmentSpan() {
            @Override
            public Layout.Alignment getAlignment() {
                return Layout.Alignment.ALIGN_CENTER;
            }
        }, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        fav.setText(spannableString);
//        order.setText("&#xf459; 我的订单");
//        fav.setText("&#xe415; 浏览&amp;收藏");

        String jsName = "bundle://Hotel/HDetailMapHotel.png";
        String[] nameArr = jsName.split("/");
        String name = nameArr[nameArr.length - 1];
        String imgName = name.substring(0, name.indexOf("."));
        char[] nameChar = imgName.toCharArray();
        StringBuilder androidName = new StringBuilder("");
        for (int i = 0; i < nameChar.length; i++) {
            char s = nameChar[i];
            if (Character.isUpperCase(s)) {
                if (i != 0) {
                    //非首字母大写，前面加横线
                    androidName.append("_");
                    androidName.append(Character.toLowerCase(s));
                }else {
                    androidName.append(Character.toLowerCase(s));
                }
            } else {
                androidName.append(s);
            }
        }
        setImage(androidName.toString());

//        parseJsonArray();
        parseJson();
        int max = seekBar.getMax();
        seekBar.setOnSeekBarChangeListener(this);
        Log.d("----", "onCreate: ");

        MarkerType type = MarkerType.COLLECTION;
        Log.e("------", "onCreate: "+MarkerType.COLLECTION.equals(type));
        Log.e("------", "onCreate: "+MarkerType.DETAIL.equals(type));
    }


    public void showView() {
        HotelInternetShareView.showView(this, false, "");
    }


    private void setImage(String name) {
        if (mContext == null) {
            return;
        }
        try {
            ApplicationInfo appInfo = mContext.getApplicationInfo();
            int resID = mContext.getResources().getIdentifier(name, "drawable", appInfo.packageName);
            imgTest.setImageResource(resID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_to_nested_scroll_view:
                Intent i = new Intent(this, ScrollViewActivity.class);
                startActivity(i);
                break;
            case R.id.btn_show_dialog:
                showView();
                break;
            case R.id.show_activity_dialog:
                HotelActivityDialog dialog = new HotelActivityDialog(this);
                dialog.showActivity(new HotelActivityDialog.showRedEnvelopDialogInter() {
                    @Override
                    public void showRedEnvelop() {

                    }
                });
                break;
            case R.id.btn_to_nav_view:
                startActivity(new Intent(MainActivity.this, NavigationViewActivity.class));
                break;
            case R.id.btn_to_chip:
                startActivity(new Intent(MainActivity.this, ChipViewActivity.class));
                break;
            case R.id.btn_to_glide:
                startActivity(new Intent(MainActivity.this, GlideActivity.class));
                break;
            case R.id.stick_top_view:
                startActivity(new Intent(MainActivity.this, StickTopViewActivity.class));
                break;
            case R.id.btn_to_wv:
                startActivity(new Intent(MainActivity.this, WebViewAcitivity.class));
                break;
            case R.id.btn_to_butterKnife:
                startActivity(new Intent(MainActivity.this, ButterKnifeActivity.class));
                break;
        }
    }

    @OnClick(R.id.btn_to_okhttp)
    void gotoOkHttp(){
        startActivity(new Intent(MainActivity.this, OkHttpActivity.class));
    }

    @OnClick(R.id.btn_to_gson)
    void gotoGSON(){
        startActivity(new Intent(MainActivity.this, GSONActivity.class));
    }
    @OnClick(R.id.btn_to_eventbus)
    void gotoEventBus(){
        startActivity(new Intent(MainActivity.this, EventBusActivity.class));
    }

    @OnClick(R.id.btn_to_mvvm)
    void goToMVVM(){
        startActivity(new Intent(MainActivity.this, MVVMActivity.class));
    }
    @OnClick(R.id.btn_to_vs)
    void goToVS(){startActivity(new Intent(MainActivity.this, ViewStubActivity.class));}
    @OnClick(R.id.btn_to_animation)
    void gotoAnimation(){
        startActivity(new Intent(MainActivity.this, TranslateAnimationActivity.class));
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int pro, boolean b) {
        for (int i = 0; i <= pro; i++) {
            Log.e("----", "onProgressChanged: i"+i);
        }
        for (int j = pro+1; j <= seekBar.getMax(); j++) {
            Log.e("----", "onProgressChanged: j"+j);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 使TextView中不同大小字体垂直居中
     */
    public class CustomVerticalCenterSpan extends ReplacementSpan {
        private int fontSizeSp;    //字体大小sp

        public CustomVerticalCenterSpan(){
//            this.fontSizeSp = fontSizeSp;
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            text = text.subSequence(start, end);
            Paint p = getCustomTextPaint(paint);
            return (int) p.measureText(text.toString());
        }

        @Override
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            text = text.subSequence(start, end);
            Paint p = getCustomTextPaint(paint);
            Paint.FontMetricsInt fm = p.getFontMetricsInt();
            canvas.drawText(text.toString(), x, y - ((y + fm.descent + y + fm.ascent) / 2 - (bottom + top) / 2), p);    //此处重新计算y坐标，使字体居中
        }

        private TextPaint getCustomTextPaint(Paint srcPaint) {
            TextPaint paint = new TextPaint(srcPaint);
//            paint.setTextSize(ViewUtils.getSpPixel(mContext, fontSizeSp));   //设定字体大小, sp转换为px
            paint.setTextSize(srcPaint.getTextSize());   //设定字体大小, sp转换为px
            return paint;
        }
    }


    private void parseJsonArray(){
        String value = "[{\"queryCount\":1693122,\"cityInfo\":{\"provinceName\":\"北京\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"北京\",\"cityUrl\":\"beijing_city\"},{\"queryCount\":977618,\"cityInfo\":{\"provinceName\":\"上海\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"上海\",\"cityUrl\":\"shanghai_city\"},{\"queryCount\":761092,\"cityInfo\":{\"provinceName\":\"四川\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"成都\",\"cityUrl\":\"chengdu\"},{\"queryCount\":693833,\"cityInfo\":{\"provinceName\":\"广东\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"广州\",\"cityUrl\":\"guangzhou\"},{\"queryCount\":556658,\"cityInfo\":{\"provinceName\":\"广东\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"深圳\",\"cityUrl\":\"shenzhen\"},{\"queryCount\":549059,\"cityInfo\":{\"provinceName\":\"浙江\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"杭州\",\"cityUrl\":\"hangzhou\"},{\"queryCount\":543427,\"cityInfo\":{\"provinceName\":\"山东\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"青岛\",\"cityUrl\":\"qingdao\"},{\"queryCount\":526670,\"cityInfo\":{\"provinceName\":\"重庆\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"重庆\",\"cityUrl\":\"chongqing_city\"},{\"queryCount\":517260,\"cityInfo\":{\"provinceName\":\"陕西\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"西安\",\"cityUrl\":\"xian\"},{\"queryCount\":511413,\"cityInfo\":{\"provinceName\":\"海南\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"三亚\",\"cityUrl\":\"sanya\"},{\"queryCount\":467320,\"cityInfo\":{\"provinceName\":\"香港\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"香港\",\"cityUrl\":\"hongkong_city\"},{\"queryCount\":461673,\"cityInfo\":{\"provinceName\":\"福建\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"厦门\",\"cityUrl\":\"xiamen\"},{\"queryCount\":374958,\"cityInfo\":{\"provinceName\":\"云南\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"昆明\",\"cityUrl\":\"kunming\"},{\"queryCount\":344038,\"cityInfo\":{\"provinceName\":\"辽宁\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"大连\",\"cityUrl\":\"dalian\"},{\"queryCount\":300096,\"cityInfo\":{\"provinceName\":\"贵州\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"贵阳\",\"cityUrl\":\"guiyang\"},{\"queryCount\":297998,\"cityInfo\":{\"provinceName\":\"江苏\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"南京\",\"cityUrl\":\"nanjing\"},{\"queryCount\":288801,\"cityInfo\":{\"provinceName\":\"云南\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"大理\",\"cityUrl\":\"dali\"},{\"queryCount\":274810,\"cityInfo\":{\"provinceName\":\"澳门\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"澳门\",\"cityUrl\":\"macao_city\"},{\"queryCount\":272517,\"cityInfo\":{\"provinceName\":\"广东\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"惠州\",\"cityUrl\":\"huizhou_guangdong\"},{\"queryCount\":251257,\"cityInfo\":{\"provinceName\":\"广东\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"珠海\",\"cityUrl\":\"zhuhai\"}]";
        List<HotCity> cityList = JSONArray.parseArray(value, HotCity.class);
        Log.e("TAG", "parseJsonArray: ");
    }


    private void parseJson(){
        String value = "{\"queryCount\":1693122,\"cityInfo\":{\"provinceName\":\"北京\",\"dstEnd\":\"\",\"foreignCity\":false,\"countryName\":\"中国\",\"utc\":\"GMT 8:00\",\"businessType\":0,\"dst\":\"\",\"dstStart\":\"\"},\"cityName\":\"北京\",\"cityUrl\":\"beijing_city\"}";
        JSONObject jsonObject = JSON.parseObject(value);
        HotCity hotCity = JSON.toJavaObject(jsonObject, HotCity.class);

        Log.e("TAG", "parseJsonArray: ");
    }
    enum MarkerType {
        NORMAL,             //一般样式
        NORMAL_SELECTED,    //一般样式选中
        NORMAL_PRE,         //一般样式已读
        DETAIL,             //详细
        COLLECTION          //已收藏
    }

}
