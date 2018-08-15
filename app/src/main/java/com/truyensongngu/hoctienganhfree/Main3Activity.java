package com.truyensongngu.hoctienganhfree;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.truyensongngu.hoctienganhfree.Adapter.Dictionary_adapter;
import com.truyensongngu.hoctienganhfree.Fragment.FragEngLish;
import com.truyensongngu.hoctienganhfree.Fragment.FragViet;
import com.truyensongngu.hoctienganhfree.model.NoiDungTruyen;
import com.truyensongngu.hoctienganhfree.model.data;
import com.truyensongngu.hoctienganhfree.model.newword;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    Button btnEng, btnViet, btnTuDien;
    FrameLayout layout1, layout2;
    TextView txtEng, txtViet;
    ImageView imgSound, imgHinh;
    MediaPlayer mediaPlayer;
    ArrayList<NoiDungTruyen> listNoiDung;
    TextView txtPage;
    private int id;
    private String name;
    private String DATABASE_NAME = "TruyenTranhSongNguOff.sqlite";
    SQLiteDatabase database, database2;
    ArrayList<newword> listNewWord;
    Dictionary_adapter adapter;
    final String DATABASE_NAME2 = "database.sqlite";

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        MobileAds.initialize(this,
                "ca-app-pub-9718414910847389~5864446254");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        initView();
        listNoiDung = new ArrayList<>();

        id = getIntent().getIntExtra("id", 0);
        mediaPlayer = MediaPlayer.create(this, data.fileMp3[id - 1]);
        imgHinh.setImageResource(data.filehinh[id-1]);
        name = getIntent().getStringExtra("name");
        txtPage.setText(name);




        btnViet.setOnClickListener(this);
        btnEng.setOnClickListener(this);
        imgSound.setOnClickListener(this);
        btnTuDien.setOnClickListener(this);

        getData();


//


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            finish();
        }else {
            finish();
        }
    }
    public ArrayList<newword> searchgetData(String tukhoa) {
        //SELECT Tu FROM TuVung Where Tu LIKE "%abandone%"
        //SELECT * FROM vocabulary INNER JOIN wordtype ON vocabulary.word_type = wordtype.id WHERE word LIKE '%g%'
        database2 = Database.initDatabase(this, DATABASE_NAME2);
        Cursor cursor = database2.rawQuery("SELECT * FROM phrase WHERE l_from LIKE '" + tukhoa+ "%' LIMIT 0, 10", null);
        cursor.moveToFirst();
        listNewWord.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String tu = cursor.getString(1).trim();
            String des = cursor.getString(2).trim();



            listNewWord.add(new newword(tu,  des));
            adapter.notifyDataSetChanged();
        }
        return listNewWord;
    }
    public void DialogNewWord(){
        Dialog dialog = new Dialog(Main3Activity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogword);
        final ListView lview = dialog.findViewById(R.id.lv);
        final ImageView img = dialog.findViewById(R.id.img);
        SearchView sv = dialog.findViewById(R.id.svTuVung);
        listNewWord = new ArrayList<>();
        getData();
        adapter = new Dictionary_adapter(Main3Activity.this, R.layout.line_list, listNewWord);
        lview.setAdapter(adapter);
        lview.setTextFilterEnabled(true);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                lview.setVisibility(View.VISIBLE);
                img.setVisibility(View.GONE);
                listNewWord = searchgetData(s);
                adapter = new Dictionary_adapter(Main3Activity.this, R.layout.line_list, listNewWord);
                lview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            }
        });


        dialog.show();
    }



    public void getData(){
        String eng = null;
        String viet = null;
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NoiDungTruyen", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            eng = cursor.getString(2).trim();
            viet = cursor.getString(3).trim();
            listNoiDung.add(new NoiDungTruyen(eng, viet));

        }

        txtEng.setText(listNoiDung.get(id - 1).getEng());
        txtViet.setText(listNoiDung.get(id - 1).getViet());
    }

    private void initView() {
        btnTuDien = findViewById(R.id.btnNewWord);
        imgHinh = findViewById(R.id.imghinh);
        btnEng = findViewById(R.id.btnEnglish);
        btnViet = findViewById(R.id.btnVietNam);
        layout1 = findViewById(R.id.frlayout);
        layout2 = findViewById(R.id.frlayout2);
        txtEng = findViewById(R.id.txtEnglish);
        txtViet = findViewById(R.id.txtVietNam);
        imgSound = findViewById(R.id.imgSound);
        txtPage = findViewById(R.id.txtpage);
    }



    @Override
    public void onClick(View v) {
        Animation animation;
        switch (v.getId()){
            case R.id.btnNewWord:
                DialogNewWord();
                break;
            case R.id.btnEnglish:
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.VISIBLE);
                break;
            case R.id.btnVietNam:
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                break;
            case R.id.imgSound:
                imgSound.setEnabled(false);
                imgSound.setImageResource(R.drawable.megaphoneon);
                animation = AnimationUtils.loadAnimation(Main3Activity.this, R.anim.anim_sound);
                imgSound.startAnimation(animation);

                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        imgSound.setEnabled(true);
                        imgSound.clearAnimation();
                        imgSound.setImageResource(R.drawable.megaphoneoff);
                    }
                });
                break;


        }

    }
}
