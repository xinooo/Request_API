package com.example.an.Request_API;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AN on 2020/4/27.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflter;
    ArrayList<Bitmap> Bitmap;
    ArrayList<String> id;
    ArrayList<String> title;

    public CustomAdapter(Context applicationContext, ArrayList<Bitmap> Bitmap,ArrayList<String> id,ArrayList<String> title) {
        this.context = applicationContext;
        this.Bitmap = Bitmap;
        this.id = id;
        this.title = title;
        inflter = (LayoutInflater.from(applicationContext));
    }



    @Override
    public int getCount() {
        return Bitmap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.activity_gridview, null); // inflate the layout
        ImageView img = (ImageView) convertView.findViewById(R.id.img); // get the reference of ImageView
        TextView txt_id = (TextView) convertView.findViewById(R.id.txt_id);
        TextView txt_title = (TextView) convertView.findViewById(R.id.txt_title);
        img.setImageBitmap(Bitmap.get(position));
        txt_id.setText(id.get(position));
        txt_title.setText(title.get(position));

        return convertView;
    }
}
