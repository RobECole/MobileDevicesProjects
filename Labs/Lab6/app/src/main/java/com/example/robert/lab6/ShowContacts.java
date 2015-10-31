package com.example.robert.lab6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowContacts extends AppCompatActivity implements OnClickListener {
    public static int DATA = 2;
    public ArrayAdapter<Contact> adapter;
    static ArrayList<Contact> contactList = new ArrayList<Contact>();

    ListView lv;

    @Override
    protected void onStart() {
        super.onStart();

         try {
            InputStream inputStream = openFileInput("contactData.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                boolean contains;
                while ((receiveString = bufferedReader.readLine()) != null) {
                    Scanner scanner = new Scanner(receiveString);
                    int id = scanner.nextInt();
                    contains = false;
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("contactData.txt", Context.MODE_PRIVATE));
            for(int i = 0; i < contactList.size(); i++) {
                Contact contact = contactList.get(i);
                String contactData = contact.id + " " + contact.fn + " " + contact.ln + " " + contact.phone + "\n";
                outputStreamWriter.write(contactData);
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        lv = (ListView)findViewById(R.id.listView);


        adapter = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1,contactList);
        lv.setAdapter(adapter);

        Button add = (Button)findViewById(R.id.add);
        Button remove = (Button)findViewById(R.id.remove);


        add.setOnClickListener(this);
        remove.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_contacts, menu);
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

    @Override
    public void onClick(View v) {
        Intent intent;

        if (v.getId() == R.id.add) {
            intent = new Intent(this, AddContact.class);
        }
        else {
            intent = new Intent(this, DeleteContact.class);
        }
        startActivityForResult(intent, DATA);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent){
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (resultIntent.getIntExtra("act",0) == 1) {
            Contact c = new Contact(contactList.size() + 1, resultIntent.getStringExtra("First"),
                    resultIntent.getStringExtra("Last"), resultIntent.getStringExtra("phone"));
            contactList.add(c);
        }
        else if (resultIntent.getIntExtra("act",0) == 2){
            int id = Integer.parseInt(resultIntent.getStringExtra("id"));
            int i = 0;
            for( Contact c: contactList){
                if (c.id == id){
                    contactList.remove(i);
                    break;
                }
                i++;
            }
        }
        adapter.notifyDataSetChanged();
    }
}
