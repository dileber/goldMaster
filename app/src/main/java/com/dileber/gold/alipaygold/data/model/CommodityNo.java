package com.dileber.gold.alipaygold.data.model;

/**
 * Created by hyy on 2018/3/1.
 */
public enum CommodityNo {

    GC("GC"),
    AU("AU"),
    CL("CL");
    String s;
    CommodityNo(String s){
        this.s = s;
    }

    public String getS() {
        return s;
    }
}
