package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by shumengma on 2018/10/16.
 */

public class ButterKnifeActivity extends AppCompatActivity {
    @BindView(R.id.btn_butterknife_toast)
    Button button;

    @BindView(R.id.bkb_butterknife)
    ButterKnifeButton bkb;

    @BindViews({R.id.btn_bk_1, R.id.btn_bk_2})
    List<Button> buttonList;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindString(R.string.fav)
    String fav;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);

//        ((LinearLayout)((ViewGroup)findViewById(android.R.id.content)).getChildAt(0)).addView(new ButterKnifeButton(this));

        ButterKnife.bind(this);
        initData();
    }

    @OnClick(R.id.btn_butterknife_toast)
    void showToast(){
        Toast.makeText(this, "BK", Toast.LENGTH_LONG).show();
    }
    @OnLongClick(R.id.btn_butterknife_toast)
    boolean showToastLong(){
        Toast.makeText(this, "BKLong", Toast.LENGTH_LONG).show();
        return true;
    }
    @OnClick(R.id.bkb_butterknife)
    void onClick(){
        Log.d("------", "onClick:------bkbkbkb ");
    }

    private void initData(){
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);
        rv.setAdapter(new RVAdapter());
    }
    class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyHolder>{

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            MyHolder holder = new MyHolder(LayoutInflater.from(ButterKnifeActivity.this).inflate(R.layout.rv_item, null, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            myHolder.tv.setText(i+"----");
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class MyHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.rv_tv)
            TextView tv;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
