package com.truyensongngu.hoctienganhfree.model;

public class NoiDungTruyen {
    private String eng;
    private String viet;

    public NoiDungTruyen(String eng, String viet) {
        this.eng = eng;
        this.viet = viet;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getViet() {
        return viet;
    }

    public void setViet(String viet) {
        this.viet = viet;
    }
}
