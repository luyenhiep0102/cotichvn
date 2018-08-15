package com.truyensongngu.hoctienganhfree.model;

public class ChapTruyen {
    private int maChap;
    private String tenChapTA;
    private String TenChapTV;
    private int Hinh;

    public ChapTruyen(int maChap, String tenChapTA, String tenChapTV, int hinh) {
        this.maChap = maChap;
        this.tenChapTA = tenChapTA;
        TenChapTV = tenChapTV;
        Hinh = hinh;
    }

    public int getMaChap() {
        return maChap;
    }

    public void setMaChap(int maChap) {
        this.maChap = maChap;
    }

    public String getTenChapTA() {
        return tenChapTA;
    }

    public void setTenChapTA(String tenChapTA) {
        this.tenChapTA = tenChapTA;
    }

    public String getTenChapTV() {
        return TenChapTV;
    }

    public void setTenChapTV(String tenChapTV) {
        TenChapTV = tenChapTV;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
