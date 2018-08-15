package com.truyensongngu.hoctienganhfree.Adapter;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.truyensongngu.hoctienganhfree.R;
import com.truyensongngu.hoctienganhfree.model.newword;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Administrator on 5/13/2018.
 */

public class Dictionary_adapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<newword> arrayList;
    TextToSpeech toSpeech;

    public Dictionary_adapter(Context context, int layout, ArrayList<newword> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults returns = new FilterResults();
                ArrayList<newword> result = new ArrayList<>();
                if (charSequence != null){
                    if (arrayList != null && arrayList.size() >  0){
                        for (final newword g : arrayList) {
                            if (g.getTuTA().toLowerCase()
                                    .contains(charSequence.toString()))
                                result.add(g);
                        }
                    }
                    returns.values = result;
                }
                return returns;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayList = (ArrayList<newword>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder{
        TextView txtTu, txtNgia, txtSys, txtPam;
        Button btnSound, btnSoundUK;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTu = view.findViewById(R.id.txtTu);

            holder.txtPam = view.findViewById(R.id.txtPam);
            holder.btnSound = view.findViewById(R.id.btnsound);
            holder.btnSoundUK = view.findViewById(R.id.btnsoundUK);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        newword nw = arrayList.get(i);
        holder.txtTu.setText(nw.getTuTA());
//        holder.txtNgia.setText(nw.getNghiaTV());
        holder.txtPam.setText("/ "+nw.getDes()+" /");
     //   holder.txtSys.setText("("+nw.getSysbol()+")");
        holder.btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int y) {
                        if (y != TextToSpeech.ERROR){
                            toSpeech.setLanguage(Locale.ENGLISH);
                            toSpeech.speak(arrayList.get(i).getTuTA(), TextToSpeech.QUEUE_FLUSH, null);



                        }

                    }
                });
            }
        });
        holder.btnSoundUK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int y) {
                        if (y != TextToSpeech.ERROR){
                            toSpeech.setLanguage(Locale.UK);
                            toSpeech.speak(arrayList.get(i).getTuTA(), TextToSpeech.QUEUE_FLUSH, null);



                        }

                    }
                });
            }
        });
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list);
        view.startAnimation(animation);
        return view;
    }
}
