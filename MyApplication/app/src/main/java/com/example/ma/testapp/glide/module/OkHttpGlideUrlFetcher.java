package com.example.ma.testapp.glide.module;

import android.support.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by shumengma on 2018/10/9.
 */

public class OkHttpGlideUrlFetcher implements DataFetcher<InputStream> {
    private final OkHttpClient client;
    private final GlideUrl url;
    private InputStream stream;
    private ResponseBody responseBody;

    public OkHttpGlideUrlFetcher(OkHttpClient client, GlideUrl url){
        this.client = client;
        this.url = url;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super InputStream> callback) {
        try {
            Request.Builder requestBuilder = new Request.Builder().url(url.toStringUrl());
            for (Map.Entry<String, String> headerEntry : url.getHeaders().entrySet()){
                String key = headerEntry.getKey();
                requestBuilder.addHeader(key, headerEntry.getValue());
            }

            Request request = requestBuilder.build();
            Response response = client.newCall(request).execute();
            responseBody = response.body();
            long contentLength = responseBody.contentLength();
            stream = ContentLengthInputStream.obtain(responseBody.byteStream(), contentLength);
            callback.onDataReady(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanup() {
        if (stream != null){
            try {
                stream.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        if (responseBody != null){
            try {
                responseBody.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancel() {

    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return null;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return null;
    }
}
