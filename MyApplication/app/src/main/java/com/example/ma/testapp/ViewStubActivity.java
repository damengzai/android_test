package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.ma.testapp.view.vpItem;

public class ViewStubActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
        ViewStub vs = findViewById(R.id.vs);
        vs.inflate();
        viewPager = findViewById(R.id.vp);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new TextViewPagerAdapter());
    }
    class TextViewPagerAdapter extends PagerAdapter{
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            TextView textView = new TextView(ViewStubActivity.this);
//            textView.setText(position+"------");
            vpItem item = new vpItem(ViewStubActivity.this);

            container.addView(item);
            return item;
        }

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
