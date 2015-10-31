package com.example.robert.lab4;

import android.content.Intent;
import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver R = new MyReceiver();
        this.registerReceiver(R,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.BATTERY_CHANGED");
        sendBroadcast(intent);
    }
}
