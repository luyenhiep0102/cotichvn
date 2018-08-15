package com.truyensongngu.hoctienganhfree;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.truyensongngu.hoctienganhfree.Adapter.MoreappAdapter;
import com.truyensongngu.hoctienganhfree.model.moreApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoreApp extends AppCompatActivity {
    private String urlMoreApp = "http://truyentranhsongnguav.com/model/web_moreApp.php";
    int idd;
    String ten, linkApp, linkDan;
    MoreappAdapter adapter;
    GridView gv;
    ArrayList<moreApp> listApp;
    String link;
    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_app);
        gv = findViewById(R.id.grviewMoreApp);
        listApp = new ArrayList<>();
        connection = new Connection(this);

        if (connection.isconnect()){
            getData(urlMoreApp);
        }else {
            Toast.makeText(MoreApp.this, "Lỗi kết nối có thể do Wifi hay 3G", Toast.LENGTH_SHORT).show();
        }

    }
    private void getData(String url) {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i< response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                idd = object.getInt("MAAPP");
                                ten = object.getString("TENAPP");
                                linkApp = object.getString("LINKAPP");
                                linkDan = object.getString("LINKDAN");

                                listApp.add(new moreApp(idd, ten, linkApp, linkDan));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter = new MoreappAdapter(R.layout.custom_moreapp, MoreApp.this, listApp);
                        gv.setAdapter(adapter);
                        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                link = listApp.get(i).getLinkDan();

                                if (connection.isconnect()){

                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(link));
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MoreApp.this, "Lỗi kết nối có thể do Wifi hay 3G", Toast.LENGTH_SHORT).show();
                                }
                            }



                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.d("loigame", error.toString());
                Toast.makeText(MoreApp.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
