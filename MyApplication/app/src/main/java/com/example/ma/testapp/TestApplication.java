package com.example.ma.testapp;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shumengma on 2018/7/20.
 */

public class TestApplication extends Application {
    private static Context mContext;

    private static Typeface font;//图片字体

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if(mContext == null){
            mContext = this;
        }
    }

    public static Typeface getFont() {
        if (font == null) {
            font = create(mContext.getAssets(), "atom_hotel_iconfont.ttf");
        }
        return font;
    }

    private static Typeface create(AssetManager mgr, String path) {
        try {
            return Typeface.createFromAsset(mgr, path);
        } catch (Throwable e) {
            String assetExists;
            try {
                InputStream is = mgr.open(path);
                if (is != null) {
                    assetExists = "true";
                    try {
                        is.close();
                    } catch (Throwable ee) {
                    }
                } else {
                    assetExists = "false";
                }
            } catch (Throwable e1) {
                e1.printStackTrace();
                assetExists = e1.getMessage();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                //Font asset not found
                try {
                    Class cls = Class.forName("android.graphics.FontFamily");
                    Object fontFamily = cls.newInstance();
                    Method m = cls.getDeclaredMethod("addFontFromAsset", AssetManager.class, String.class);
                    m.setAccessible(true);
                    Boolean flag = (Boolean) m.invoke(fontFamily, mgr, path);
                    if (flag) {
//                    m = Typeface.class.getDeclaredMethod("createFromFamilies",new Class[]{cls[].class});
                        Field f = cls.getDeclaredField("mNativePtr");
                        f.setAccessible(true);
                        Long mNativePtr = (Long) f.get(fontFamily);

                        long[] ptrArray = new long[1];
                        ptrArray[0] = mNativePtr;

                        m = Typeface.class.getDeclaredMethod("nativeCreateFromArray", long[].class);
                        m.setAccessible(true);
                        Long l = (Long) m.invoke(fontFamily, new Object[]{ptrArray});

                        Constructor<Typeface> c = Typeface.class.getDeclaredConstructor(long.class);
                        c.setAccessible(true);
                        return c.newInstance(l);
                    }
                } catch (Throwable e1) {
                    throw new RuntimeException(assetExists, e1);
                }
            } else {
                throw new RuntimeException(assetExists, e);
            }
        }
        return null;
    }
}
