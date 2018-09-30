package com.example.ma.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;;

/**
 * Created by shumengma on 2018/8/7.
 * <p>
 * 部分文字带有边框
 */

public class BoardTextView extends FrameLayout {
    TextView tv;

    public BoardTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public BoardTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoardTextView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = View.inflate(getContext(), R.layout.atom_hotel_board_text_view, this);
        tv = (TextView) v.findViewById(R.id.atom_hotel_board_text_tv);
        setData(-52480, "满￥200可用，可用于预定含境外直减的报价", "[\"境外直减\"]");
    }

    public void setData(int fontColor, String mainPromt, String subPromts) {
        if (!TextUtils.isEmpty(mainPromt)) {
            SpannableString ss = new SpannableString(mainPromt);
            if (!TextUtils.isEmpty(subPromts)) {
                JSONArray subArray = JSON.parseArray(subPromts);
                for (int i = 0; i < subArray.size(); i++) {
                    String subPromt = subArray.getString(i);
                    int index = mainPromt.indexOf(subPromt);
                    int length = subPromt.length();

                    addBoard(ss, fontColor, index, length);
                }
            }
        }
    }

    private void addBoard(SpannableString ss, final int color, int index, final int length) {

        RoundBackgroundColorSpan span = new RoundBackgroundColorSpan(color, color, 4);
        ss.setSpan(span, index, index + length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv.setText(ss);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public class RoundBackgroundColorSpan extends ReplacementSpan {
        private int mRadius;
        private int bgColor;
        private int textColor;
        private int mSize;

        public RoundBackgroundColorSpan(int bgColor,
                                        int textColor,
                                        int radius) {
            super();
            this.bgColor = bgColor;
            this.textColor = textColor;
            this.mRadius = radius;
        }

        /**
         * @param start 第一个字符的下标
         * @param end   最后一个字符的下标
         * @return span的宽度
         */
        @Override
        public int getSize(@NonNull Paint paint,
                           CharSequence text,
                           int start,
                           int end,
                           Paint.FontMetricsInt fm) {
            mSize = (int) (paint.measureText(text, start, end) + 2 * mRadius);
            return mSize + 5;//5:距离其他文字的空白
        }

        /**
         * @param y baseline
         */
        @Override
        public void draw(@NonNull Canvas canvas,
                         CharSequence text,
                         int start,
                         int end,
                         float x,
                         int top,
                         int y, int bottom,
                         @NonNull Paint paint) {
            int defaultColor = paint.getColor();//保存文字颜色
            float defaultStrokeWidth = paint.getStrokeWidth();

            //绘制圆角矩形
            paint.setColor(bgColor);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            paint.setAntiAlias(true);
            RectF rectF = new RectF(x + 2.5f, y + 2.5f + paint.ascent(), x + mSize, y + paint.descent());
            //设置文字背景矩形，x为span其实左上角相对整个TextView的x值，y为span左上角相对整个View的y值。
            // paint.ascent()获得文字上边缘，paint.descent()获得文字下边缘
            //x+2.5f解决线条粗细不一致问题
            canvas.drawRoundRect(rectF, mRadius, mRadius, paint);

            //绘制文字
            paint.setColor(textColor);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(defaultStrokeWidth);
            canvas.drawText(text, start, end, x + mRadius, y, paint);//此处mRadius为文字右移距离

            paint.setColor(defaultColor);//恢复画笔的文字颜色
        }
    }
}