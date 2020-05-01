package com.example.an.Request_API;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by AN on 2020/4/27.
 */

public class SecondActivity extends AppCompatActivity {

    GridView gridView;
    public static ArrayList<Bitmap> Bitmap_list = new ArrayList<Bitmap>();
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gridView = (GridView) findViewById(R.id.gridview);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap_list.clear();
                for(int i=0;i<MainActivity.thumbnailUrl_string.size();i++) {
                    Bitmap_list.add(getBitmapFormURL(MainActivity.thumbnailUrl_string.get(i)));
                    if(i == 0){
                        handler.obtainMessage().sendToTarget();
                    }
                    handler2.obtainMessage().sendToTarget();
                }

            }
        }).start();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bag = new Bundle();
                bag.putParcelable("thumbnailUrl", Bitmap_list.get(position));
                bag.putString("id",MainActivity.id_string.get(position));
                bag.putString("title",MainActivity.title_string.get(position));
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtras(bag);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            customAdapter = new CustomAdapter(getApplicationContext(), Bitmap_list
                    , (ArrayList<String>) MainActivity.id_string
                    ,(ArrayList<String>) MainActivity.title_string);
            gridView.setAdapter(customAdapter);
        };
    };

    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            customAdapter.notifyDataSetChanged();
        };
    };

    public static Bitmap getBitmapFormURL(String src){

        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream input = conn.getInputStream();
            Bitmap mbitmap = BitmapFactory.decodeStream(input);
            return mbitmap;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                System.exit(0);
                return true;
            default:
                break;
        }
        return false;
    }
}
