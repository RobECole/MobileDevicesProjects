package com.example.robert.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void about(View view) {
        Intent intent = new Intent(this, about.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, login.class);
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);
        if(responseCode == RESULT_OK){
            String name = resultIntent.getStringExtra("name");
            String pass = resultIntent.getStringExtra("pass");

            Toast toast;
            if (name.equals("Rob") && pass.equals("100481609")){
               toast = Toast.makeText(getApplicationContext(),
                        "Login Correct",
                        Toast.LENGTH_SHORT);


            }
            else{
                toast = Toast.makeText(getApplicationContext(),"Incorrect login", Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }
}
