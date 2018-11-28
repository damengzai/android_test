package com.example.ma.testapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by shumengma on 2018/11/20.
 * <p>
 * 地图点击POI点详细内容展示
 */

public class PoiDetailDialog extends Dialog {
    /**
     * POI名字
     */

    private TextView poiName;
    /**
     * POI地点
     */
    TextView poiAddress;

    private String name;
    private String address;

    public PoiDetailDialog(@NonNull Context context, String poiName, String poiAddress) {
        super(context, R.style.atom_hotel_dialog_bottom_in_out);
        this.name = poiName;
        this.address = poiAddress;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.atom_hotel_poi_detail_dialog);
        poiName = findViewById(R.id.atom_hotel_poi_detail_name);
        poiAddress = findViewById(R.id.atom_hotel_poi_detail_address);
        setCanceledOnTouchOutside(true);

        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

//        window.setWindowAnimations(R.style.dialog_animation);

        poiName.setText(name);
        poiAddress.setText(address);
    }
}
