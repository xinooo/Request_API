package com.example.an.Request_API;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn;
    private String url = "https://jsonplaceholder.typicode.com/photos";
    public static List<String> id_string = new ArrayList<String>();
    public static List<String> title_string = new ArrayList<String>();
    public static List<String> thumbnailUrl_string = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TransTask(MainActivity.this).execute(url);
            }
        });
    }


}
