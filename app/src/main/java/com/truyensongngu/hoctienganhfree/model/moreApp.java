package com.truyensongngu.hoctienganhfree.model;

public class moreApp {
    private int MaApp;
    private String TenApp;
    private String LinkApp;
    private String linkDan;

    public moreApp(int maApp, String tenApp, String linkApp, String linkDan) {
        MaApp = maApp;
        TenApp = tenApp;
        LinkApp = linkApp;
        this.linkDan = linkDan;
    }

    public int getMaApp() {
        return MaApp;
    }

    public void setMaApp(int maApp) {
        MaApp = maApp;
    }

    public String getTenApp() {
        return TenApp;
    }

    public void setTenApp(String tenApp) {
        TenApp = tenApp;
    }

    public String getLinkApp() {
        return LinkApp;
    }

    public void setLinkApp(String linkApp) {
        LinkApp = linkApp;
    }

    public String getLinkDan() {
        return linkDan;
    }

    public void setLinkDan(String linkDan) {
        this.linkDan = linkDan;
    }
}
