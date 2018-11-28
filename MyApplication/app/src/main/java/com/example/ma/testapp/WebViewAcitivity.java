package com.example.ma.testapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by shumengma on 2018/10/10.
 */

public class WebViewAcitivity extends AppCompatActivity {
    private WebView webView;
    String detailUrl;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.wv_for_price);

        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);


//        webView.setWebChromeClient(webChromeClient);
//        webView.setWebViewClient(webViewClient);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(false);
//        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        webView.getSettings().setDisplayZoomControls(true);
        WebView.setWebContentsDebuggingEnabled(true);
//        setAcceptThirdPartyCookies();

        String ctripHotelDetailUrl = "https://m.ctrip.com/webapp/hotel/hoteldetail/428365.html?atime=20180930&daylater=0&days=1&contrl=0&pay=0&discount=&latlon=&listindex=1&userLocationSearch=false#fromList";
//        String ctripUrl = "https://m.ctrip.com/webapp/hotel/?atime=20180930&days=1&city=2";
//        String ctripUrl = "https://accounts.ctrip.com/H5Login/Index?from=https://m.ctrip.com/webapp/hotel/";
        //elong
        String elongUrl = "http://m.elong.com/hotel/40101029/#indate=2018-09-29&outdate=2018-09-30";
        String feizhu = "https://h5.m.taobao.com/trip/hotel/detail/detail.html?checkIn=2018-10-23&checkOut=2018-10-24&shid=51745100&_projVer=1.1.11&isInternational=0&ttid=201300%40travel_h5_3.1.0";
        String meituan = "https://i.meituan.com/awp/h5/hotel/poi/deal.html?poiId=5241372&startTime=1539907200000&endTime=1539993600000&ct_poi=200388587257877468035240585851965810531_c0_e2506917188224341080_anull_o1_dhotelpoitagb_k1002&type=1&zlFlag=true";
        String ctripLogin = "https://accounts.ctrip.com/H5Login/Index?from=https%3A%2F%2Fm.ctrip.com%2Fwebapp%2Fmyctrip%2Findex&backurl=https%3A%2F%2Fm.ctrip.com%2Fwebapp%2Fmyctrip%2Findex";
        // set cookie
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除  

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(feizhu);

//        webView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK){
//                    return true;
//                }
//                return false;
//            }
//        });
        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("", "onClick: 点击HTML页面的时候，不会收到事件");
            }
        });
    }

    public final void setAcceptThirdPartyCookies() {
        //target 23 default false, so manual set true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            if (url.equals("https://m.ctrip.com/webapp/hotel/")) {
                //获取cookie
                CookieManager cookieManager = CookieManager.getInstance();
                String cookieStr = cookieManager.getCookie(url);
                if (cookieStr != null) {
                    System.out.println("test cookie1" + cookieStr);
                    setCookies(cookieManager, cookieStr);
//                    webView.loadUrl(detailUrl);
                }
            }

//            if (url.contains("hotel/hoteldetail")) {
                injectJs();
//            }
            super.onPageFinished(view, url);
        }

//        @Override
//        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
//            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
//                if (view.canGoBack()){
//                    view.goBack();
//                    return true;
//                }
//                return false;
//            }
//            return false;
//        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            Log.d("----", "doUpdateVisitedHistory: ------"+url);
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (url.contains("/review/")){
                return null;
            }
            return super.shouldInterceptRequest(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {
            System.out.println("url=" + url);
            if (url.startsWith("https://m.ctrip.com/webapp/hotel/booking")) {
//                PriceStayDialog priceStayDialog = new PriceStayDialog(mContext, 1, new PriceStayDialog.priceStatyDialogClickListener() {
//                    @Override
//                    public void onPositiveClick() {
//                        // 跳转外部浏览器，带上登录态
//                        Intent intent = new Intent();
//                        intent.setAction("android.intent.action.VIEW");
//                        Uri content_url = Uri.parse(url);
//                        intent.setData(content_url);
//                        startActivity(intent);
//                    }
//
//                    @Override
//                    public void onNegativeClick() {
//
//                    }
//                });
//                priceStayDialog.show();

                return true;
            }
            if (url.startsWith("ctrip://")) {
                return true;
//				if(webView.canGoBack()) {
//					webView.goBack();
//				}
            } else if (url.contains("login_dynamicpwd") || url.contains("https://m.ctrip.com/webapp/hotel/")) {
                return false;
            }
            return true;
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
    };

    protected void injectJs() {
////        webView.loadUrl("javascript:alert('jhjj');var objs = document.getElementsByClassName('dl-base-mod'); for(i=0;i<objs.length;i++){if(objs[i].attributes['data-ubt-key'].nodeValue=='c_hotel_inland_detail_comment'){objs[i].parentNode.removeChild(objs[i])}}");
//        webView.loadUrl("javascript:alert('jhjj');var registerObj = document.getElementById('headDiv_login'); registerObj.parentNode.removeChild(registerObj);" +//头部注册
//                "var forgetPwd = document.getElementById('forgetPwdBtn_login'); forgetPwd.parentNode.removeChild(forgetPwd);" +//找回密码
//                "var dyPwd = document.getElementById('dyPwdBtn_login'); dyPwd.parentNode.removeChild(dyPwd);" +//手机快捷登录
//                "var goOversea = document.getElementById('btnGoOverseaLogin'); goOversea.parentNode.removeChild(goOversea);" +//海外手机登录
//                "var thirdLogin = document.getElementById('thirdLogin'); thirdLogin.parentNode.removeChild(thirdLogin);");//第三方登录

        webView.loadUrl("javascript:var registerObj = document.getElementById('headDiv_login'); registerObj.parentNode.removeChild(registerObj);var forgetPwd = document.getElementById('forgetPwdBtn_login'); forgetPwd.parentNode.removeChild(forgetPwd);var dyPwd = document.getElementById('dyPwdBtn_login'); dyPwd.parentNode.removeChild(dyPwd);var goOversea = document.getElementById('btnGoOverseaLogin'); goOversea.parentNode.removeChild(goOversea);var thirdLogin = document.getElementById('thirdLogin'); thirdLogin.parentNode.removeChild(thirdLogin);");
//        webView.loadUrl("window.addEventListener('click',function() { event.stopPropagation()},true);");
    }

    //注入cookie
    private void setCookies(CookieManager cookieManager, String cookies) {
        String[] cookiezList = cookies.split(";");
        for (int i = 0; i < cookiezList.length; i++) {
            String cookie = cookiezList[i];
            System.out.println("test cookie=" + cookie);
            cookieManager.setCookie(detailUrl, cookie);
        }
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (webView.canGoBack()){
//            webView.goBack();
//            return;
//        }
//        super.onBackPressed();
//    }
}
