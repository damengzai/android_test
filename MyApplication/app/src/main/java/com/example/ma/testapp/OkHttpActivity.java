package com.example.ma.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

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
    private OkHttpClient okHttpClient = new OkHttpClient
                                            .Builder()
                                            .addInterceptor(new LogginInterceptor()) //添加应用拦截器
                                            .addInterceptor(new GzipRequestInterceptor())
//                                            .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                                            .build();
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
    //拦截器，添加了日志打印
    class LogginInterceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
//            FormBody f = (FormBody)request.body();
            long t1 = System.nanoTime();
            Log.d("------", "intercept: Sending request "+ request.url() + " on " + chain.connection() + "\n "+ request.headers());

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.d("------", "intercept: Receive response from " + response.request().url() + " in " + (t2-t1) + "\n" + response.headers());
            return response;
        }
    }
    //请求体压缩拦截器
    class GzipRequestInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Log.d("----", "intercept: encoding:"+originalRequest.headers("Content-Encoding"));
            if (originalRequest.body() == null || originalRequest.headers("Content-Encoding") != null){
                return chain.proceed(originalRequest);
            }
            Request compressedRequest = originalRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(originalRequest.method(), gzip(originalRequest.body()))
                    .build();

            return chain.proceed(compressedRequest);
        }
        private RequestBody gzip(final RequestBody body){
            return new RequestBody() {
                @Nullable
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() throws IOException {
                    return -1; //we don`t know the compressed length in advance
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSkin = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSkin);
                    gzipSkin.close();
                }
            };
        }
    }
    //重写响应结果拦截器
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=60")
                    .build();
        }
    };
}
