package com.example.ma.testapp.bean;

/**
 * Created by shumengma on 2018/10/24.
 */

public class MessageEvent {
    private String message;
    public MessageEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
