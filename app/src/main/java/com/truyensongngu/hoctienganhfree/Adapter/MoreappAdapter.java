package com.truyensongngu.hoctienganhfree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.truyensongngu.hoctienganhfree.R;
import com.truyensongngu.hoctienganhfree.model.moreApp;

import java.util.ArrayList;

public class MoreappAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<moreApp> arrayList;

    public MoreappAdapter(int layout, Context context, ArrayList<moreApp> arrayList) {
        this.layout = layout;
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    class ViewHolder{
        ImageView imgMoreApp;
        TextView txtMoreApp;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.imgMoreApp = convertView.findViewById(R.id.imgApp);
            holder.txtMoreApp = convertView.findViewById(R.id.txtTenApp);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        moreApp app = arrayList.get(position);
        Picasso.with(context).load(app.getLinkApp().toString()).into(holder.imgMoreApp);
        holder.txtMoreApp.setText(app.getTenApp().toString());
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list);
        convertView.startAnimation(animation);
        return convertView;
    }
}
