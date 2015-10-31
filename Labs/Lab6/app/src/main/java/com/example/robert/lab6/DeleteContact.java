package com.example.robert.lab6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Scanner;


public class DeleteContact extends AppCompatActivity {

    public ArrayAdapter<Contact> adapter;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        sp = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<Contact>(this,android.R.layout.simple_spinner_item,ShowContacts.contactList);

        sp.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_contact, menu);
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

    public void delete (View v){
        String line = sp.getSelectedItem().toString();
        Scanner scanner = new Scanner(line);
        String id = scanner.next();

        Intent results = new Intent();
        results.putExtra("id", id);
        results.putExtra("act", 2);

        setResult(RESULT_OK,results);
        finish();
    }
}
