package com.truyensongngu.hoctienganhfree.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.truyensongngu.hoctienganhfree.R;
import com.truyensongngu.hoctienganhfree.model.ChiMuc;

import java.util.ArrayList;

public class MucAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<ChiMuc> list;

    public MucAdapter(int layout, Context context, ArrayList list) {
        this.layout = layout;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.imgMuc = convertView.findViewById(R.id.imgMuc);
            holder.txtTenMuc = convertView.findViewById(R.id.txtTenTruyen);
            holder.txtTongMuc = convertView.findViewById(R.id.txtTongSo);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChiMuc chiMuc = (ChiMuc) list.get(position);
        holder.imgMuc.setImageResource(R.mipmap.ic_launcher);
        holder.txtTenMuc.setText(chiMuc.getTenMuc());
        holder.txtTongMuc.setText(chiMuc.getTongSo()+"");

        return convertView;
    }

    class ViewHolder{
        ImageView imgMuc;
        TextView txtTenMuc, txtTongMuc;
    }

}
