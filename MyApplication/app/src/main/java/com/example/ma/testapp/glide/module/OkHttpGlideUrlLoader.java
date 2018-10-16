package com.example.ma.testapp.glide.module;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by shumengma on 2018/10/9.
 */

public class OkHttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {
    private OkHttpClient mOkHttpClient;

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        private static volatile OkHttpClient sOkHttpClient;

        private static OkHttpClient getOkHttpClient() {
            if (sOkHttpClient == null) {
                synchronized (Factory.class) {
                    if (sOkHttpClient == null) {
                        sOkHttpClient = new OkHttpClient();
                    }
                }
            }
            return sOkHttpClient;
        }

        public Factory() {
        }

        @NonNull
        @Override
        public ModelLoader<GlideUrl, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
            return new OkHttpGlideUrlLoader(getOkHttpClient());
        }

        @Override
        public void teardown() {

        }
    }

    public OkHttpGlideUrlLoader(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull GlideUrl glideUrl, int width, int height, @NonNull Options options) {
        return null;
//        return new OkHttpGlideUrlFetcher(mOkHttpClient,glideUrl).;
    }

    @Override
    public boolean handles(@NonNull GlideUrl glideUrl) {
        return false;
    }

}
