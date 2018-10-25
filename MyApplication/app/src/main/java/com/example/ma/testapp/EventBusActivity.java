package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ma.testapp.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shumengma on 2018/10/24.
 */

public class EventBusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @OnClick(R.id.btn_send_event)
    void sendEvent(){
        EventBus.getDefault().post(new MessageEvent("发送事件"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMessageMain(MessageEvent event){
        Log.d("----", "onReceiveMessageMain: "+Thread.currentThread().getId());
        Log.d("----", "onReceiveMessageMain: "+event.getMessage());
    }
    @Subscribe(threadMode =  ThreadMode.BACKGROUND)
    public void onReceiveMessageBackground(MessageEvent event){
        Log.d("----", "onReceiveMessageBackground: "+Thread.currentThread().getId());
        Log.d("----", "onReceiveMessageBackground: "+event.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onReceiveMessagePosting(MessageEvent event){
        Log.d("----", "onReceiveMessagePosting: "+Thread.currentThread().getId());
        Log.d("----", "onReceiveMessagePosting: "+event.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onReceiveMessageAsync(MessageEvent event){
        Log.d("----", "onReceiveMessageAsync: "+Thread.currentThread().getId());
        Log.d("----", "onReceiveMessageAsync: "+event.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onReceiveMessageMainOrdered(MessageEvent event){
        Log.d("----", "onReceiveMessageMainOrdered: "+Thread.currentThread().getId());
        Log.d("----", "onReceiveMessageMainOrdered: "+event.getMessage());
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
