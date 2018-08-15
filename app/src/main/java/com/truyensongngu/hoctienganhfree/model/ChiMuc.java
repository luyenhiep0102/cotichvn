package com.truyensongngu.hoctienganhfree.model;

public class ChiMuc {
    private int maMuc;
    private String tenMuc;
    private int tongSo;

    public ChiMuc(int maMuc, String tenMuc, int tongSo) {
        this.maMuc = maMuc;
        this.tenMuc = tenMuc;
        this.tongSo = tongSo;
    }

    public int getMaMuc() {
        return maMuc;
    }

    public void setMaMuc(int maMuc) {
        this.maMuc = maMuc;
    }

    public String getTenMuc() {
        return tenMuc;
    }

    public void setTenMuc(String tenMuc) {
        this.tenMuc = tenMuc;
    }

    public int getTongSo() {
        return tongSo;
    }

    public void setTongSo(int tongSo) {
        this.tongSo = tongSo;
    }
}