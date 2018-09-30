package com.example.ma.testapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by shumengma on 2018/7/20.
 */

public class FontTextView extends TextView {

    public FontTextView(Context context) {
        this(context, null);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if(!isInEditMode()) {
            Typeface font = TestApplication.getFont();
            setTypeface(font);
        }
    }
}
