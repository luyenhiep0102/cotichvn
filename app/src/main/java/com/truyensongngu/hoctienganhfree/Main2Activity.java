package com.truyensongngu.hoctienganhfree;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.truyensongngu.hoctienganhfree.model.ChapTruyen;
import com.truyensongngu.hoctienganhfree.model.ChiMuc;
import com.truyensongngu.hoctienganhfree.model.data;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listChap;
    ArrayList<ChapTruyen> arrayMuc;
    Chapadapter adapter;
    private String DATABASE_NAME = "TruyenTranhSongNguOff.sqlite";
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        listChap = findViewById(R.id.lvChap);
        arrayMuc = new ArrayList<>();
        getData();
        listChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("id",arrayMuc.get(position).getMaChap());
                intent.putExtra("name", arrayMuc.get(position).getTenChapTV());
                startActivity(intent);
            }
        });
    }
    private void getData(){
        int id = 0;
        String maChap = null;
        String tenChap = null;
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM chapTruyen ", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            id = cursor.getInt(0);
            maChap = cursor.getString(1).trim().toString();
            tenChap = cursor.getString(2).trim();
            arrayMuc.add(new ChapTruyen(id, maChap, tenChap, data.filehinh[i]));
        }
        adapter = new Chapadapter(R.layout.chap_truyen, Main2Activity.this, arrayMuc);
        listChap.setAdapter(adapter);

    }
}
