package com.truyensongngu.hoctienganhfree;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 4/24/2018.
 */

public class Connection {
    Context context;
    public Connection(Context context){
        this.context = context;
    }
    public Boolean isconnect(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivity != null){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null){
                return true;
            }
        }
        return false;
    }
}
