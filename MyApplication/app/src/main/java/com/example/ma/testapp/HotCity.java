package com.example.ma.testapp;

import java.io.Serializable;

/**
 * Created by shumengma on 2018/8/15.
 */

public class HotCity implements Serializable{
    private static final long serialVersionUID = -3706585986599327682L;
    public String cityUrl = "{}";
    public String cityName;
    public HotelCityTimeZoneData cityInfo;

    public static class HotelCityTimeZoneData implements Serializable {
        private static final long serialVersionUID = 1L;

        //是否是国际城市,{true:是国际酒店}
        public boolean foreignCity;
        //国家名称
        public String countryName;
        //时区简称 例如:"GMT-5:00" 等
        public String utc;
        //夏令时时区 例如:"GMT+8:00"  最好不自己解析，用国际通用的timezone工具类来搞
        public String dst;
        //夏令时开始 例如:"MM/DD 02:00"
        public String dstStart;
        //夏令时结束 例如:"11/20 01:00"
        public String dstEnd;
        //为切国际rn页区分港澳台提供的，为台湾为1
        public int businessType;
        //若为区则为上级城市
        public String parentCityName;
    }
}
