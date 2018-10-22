package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by shumengma on 2018/10/18.
 */

public class OkHttpActivity extends AppCompatActivity {
    @BindView(R.id.get_data)
    Button getData;
    @BindView(R.id.show_data)
    TextView showData;

//    private static final String SERVER_URL = "http://kbx.chuyunspace.com/router";
    private static final String WEATHER_SERVER_URL = "http://www.weather.com.cn/data/sk/101110101.html";
    private OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.get_data)
    void getOkData() {
        String params = "{\"phone\":\"15822524636\",\"refer\":\"1\",\"service\":\"\"}";

        RequestBody body = new FormBody.Builder()
//                .add("params", AES.Encrypt(params, key))
                .build();

        Request request = new Request.Builder()
                .url(WEATHER_SERVER_URL)
//                .post(body)
                .build();
        final Call call = okHttpClient.newCall(request);


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            Headers headers = response.headers();
                            final String result = response.body().string();
                            System.out.println("----"+result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showData.setText(result);
                                }
                            });
                        }
                    });
//                    Response response = call.execute();
//                    System.out.println("----"+response.body().string());
//                    showData.setText(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        t.start();
    }
}
