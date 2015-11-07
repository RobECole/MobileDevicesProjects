package com.example.robert.assignment2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Robert on 06-Nov-2015.
 */
public class toBTC extends AsyncTask <String, String, String> {
    private listener ls = null;
    private String btc;

    public toBTC(listener ls){
        this.ls = ls;
    }

    @Override
    protected String doInBackground(String... params){
        try {
            URL url = new URL(params[0]);
            BufferedReader brf = new BufferedReader(new InputStreamReader(url.openStream()));
            String doc;
            while((doc=brf.readLine())!=null){
                btc = doc;
            }
            brf.close();
            } catch (IOException e1) {
            e1.printStackTrace();
        }
        return btc;
    }

    @Override
    protected void onPostExecute(String result){
        ls.bitCoin(btc);
    }
}
