package com.example.an.Request_API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by AN on 2020/4/27.
 */

public class TransTask extends AsyncTask<String, Void, String> {

    private Context context;

    public TransTask(Context context){
        this.context=context;
    }


    protected String doInBackground(String... strings) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            while(line!=null){
                sb.append(line);
                line = in.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            parseJSON(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJSON(String s) throws JSONException {

        JSONArray jsonArray = new JSONArray(s);
        for (int i=0;i<jsonArray.length();i++){
            MainActivity.id_string.add(jsonArray.getJSONObject(i).getString("id"));
            MainActivity.title_string.add(jsonArray.getJSONObject(i).getString("title"));
            String ss = jsonArray.getJSONObject(i).getString("thumbnailUrl");
            String[] all=ss.split("/");
            MainActivity.thumbnailUrl_string.add("https://ipsumimage.appspot.com/"+all[3]+","+all[4]);
        }
        context.startActivity(new Intent(context, SecondActivity.class));

    }

}


