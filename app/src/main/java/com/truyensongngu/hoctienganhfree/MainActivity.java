package com.truyensongngu.hoctienganhfree;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.truyensongngu.hoctienganhfree.Adapter.MucAdapter;
import com.truyensongngu.hoctienganhfree.model.ChiMuc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
         {

    ListView listMuc;
    ArrayList<ChiMuc> arrayMuc;
    MucAdapter adapter;
    Button btnBatDau, btnDanhGia, btnAppK, btnShare;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main);
        btnBatDau = findViewById(R.id.btnBatDau);
        btnShare = findViewById(R.id.btnShare);
        btnAppK = findViewById(R.id.btnAppK);
        btnDanhGia = findViewById(R.id.btnDanhGia);

        btnBatDau.setOnClickListener(this);
        btnAppK.setOnClickListener(this);
        btnDanhGia.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);



    }


             @Override
             public void onClick(View v) {
                 switch (v.getId()){
                     case R.id.btnBatDau:
                         Intent intent2 = new Intent(this, Main2Activity.class);
                         startActivity(intent2);
                         break;
                     case R.id.btnAppK:
                         startActivity(new Intent(MainActivity.this, MoreApp.class));

                         break;
                     case R.id.btnDanhGia:
                         Intent intent3 = new Intent();
                         intent3.setAction(Intent.ACTION_VIEW);
                         intent3.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.truyensongngu.hoctienganhfree"));
                         startActivity(intent3);
                         break;
                     case R.id.btnShare:
//                         Intent intent = new Intent(Intent.ACTION_SEND);
//                         intent.setType("text/plan");
//                         String sharebody = "App rất hay mọi người tải về mà xài";
//                         intent.putExtra(Intent.EXTRA_SUBJECT, "https://play.google.com/store/apps/details?id=com.truyensongngu.hoctienganhfree");
//                         intent.putExtra(Intent.EXTRA_TEXT, sharebody);
//                         startActivity(Intent.createChooser(intent, "Share using"));

                            if (shareDialog.canShow(ShareLinkContent.class)) {
                                ShareLinkContent content = new ShareLinkContent.Builder()
                                        .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.truyensongngu.hoctienganhfree"))
                                        .build();
                                shareDialog.show(content);
                            }


                         break;
                 }
             }
         }
