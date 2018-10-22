package com.example.ma.testapp.pojo;

/**
 * Created by shumengma on 2018/10/22.
 */

public class Result<T> {
    public int code;
    public String message;
    public T data;
    //接口返回的数据，code，message，是通用的，T是真正的数据，使用泛型，GSON支持
}
