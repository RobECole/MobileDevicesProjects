package com.example.robert.lab6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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

    public void addResults(View v){
        EditText firstName = (EditText)findViewById(R.id.firstName);
        EditText lastName = (EditText)findViewById(R.id.lastName);
        EditText phone = (EditText)findViewById(R.id.phone);

        Intent results = new Intent();
        results.putExtra("First", firstName.getText().toString());
        results.putExtra("Last", lastName.getText().toString());
        results.putExtra("phone", phone.getText().toString());
        results.putExtra("act", 1);
        setResult(RESULT_OK,results);
        finish();
    }
}
