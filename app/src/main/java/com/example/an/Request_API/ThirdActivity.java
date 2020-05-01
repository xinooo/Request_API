package com.example.an.Request_API;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by AN on 2020/4/28.
 */

public class ThirdActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txt_id,txt_title;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        imageView = (ImageView)findViewById(R.id.selectedImage);
        txt_id = (TextView)findViewById(R.id.txt_id);
        txt_title = (TextView)findViewById(R.id.txt_title);


        Intent intent = getIntent();
        Bundle bag = intent.getExtras();
        imageView.setImageBitmap((Bitmap) bag.getParcelable("thumbnailUrl"));
        txt_id.setText("id："+bag.getString("id"));
        txt_title.setText("title："+bag.getString("title"));
    }
}
