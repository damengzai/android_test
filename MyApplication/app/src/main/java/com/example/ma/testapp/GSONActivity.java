package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ma.testapp.pojo.Result;
import com.example.ma.testapp.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by shumengma on 2018/10/22.
 */

public class GSONActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_layout);
        ButterKnife.bind(this);
        parseData();
    }
    private void parseData(){
        //基础使用
        Gson gson = new Gson();
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();//配合在实体类上使用@Expose，没有    @Expose声明的就不会导出，或者deserialize = false,serialize = false的不导出，序列化或者反序列化的时候，就不会有这个参数
        User u =  new User("nn", 11);
        //class序列化成string
        String uStr = gson.toJson(u);
        //string反序列化成class
        User user = gson.fromJson(uStr, User.class);
        //
        String jsonArray = "[\"Android\", \"JAVA\", \"C\"]";
        //也可以使用String[],有时候使用List操作方便，不直接使用List<String>.class是因为类型擦除，List<String>和List<User>两个的字节码文件就一个，是
        List<String> strList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());
        Log.e("", "parseData: ");

        //e.g.服务端返回数据,可以使用这种类型的泛型，见pojo.Result

//        Type userType = new TypeToken<Result<User>>(){}.getType();
//        Result<User> userResult = gson.fromJson("json result", userType);
//        User user1 = userResult.data;
    }
}
