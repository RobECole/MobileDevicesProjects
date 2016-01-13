package com.example.robert.lab5;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Robert on 27-Oct-2015.
 * Async reader, takes URL and returns string
 */
public class Async extends AsyncTask <String, String, String> {
    private listener ls = null;
    private String license = "";

    public Async(listener ls){
        this.ls = ls;
    }

    @Override
    protected String doInBackground(String... params){
        try {
            URL url = new URL(params[0]);
            BufferedReader brf = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while((line=brf.readLine())!=null){
                license = license + line + "\n";
            }
            brf.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return license;
    }

    @Override
    protected void onPostExecute(String result){
        ls.placeHolder(license);
    }
}
