package com.example.robert.lab5;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Robert on 27-Oct-2015.
 */
public class Async extends AsyncTask <String, String, String> {
    private listener ls = null;
    private String brown = "";

    public Async(listener ls){
        this.ls = ls;
    }

    @Override
    protected String doInBackground(String... params){
        try {
            URL url = new URL(params[0]);
            BufferedReader brf = new BufferedReader(new InputStreamReader(url.openStream()));
            String doc;
            while((doc=brf.readLine())!=null){
                brown = brown + doc + "\n";
            }
            brf.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


        return brown;
    }

    @Override
    protected void onPostExecute(String result){
        ls.placeHolder(brown);
    }
}
