package com.truyensongngu.hoctienganhfree.model;

/**
 * Created by Administrator on 5/13/2018.
 */

public class newword {
    private String tuTA;

    private String des;

    public newword(String tuTA, String des) {
        this.tuTA = tuTA;
        this.des = des;
    }

    public String getTuTA() {
        return tuTA;
    }

    public void setTuTA(String tuTA) {
        this.tuTA = tuTA;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}