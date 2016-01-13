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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ShowContacts extends AppCompatActivity {
    public static int ADD = 1;
    public static int DEL = 2;
    public static String filename = "contacts.txt";
    public ArrayAdapter<Contact> adapter;
    static ArrayList<Contact> contactList = new ArrayList<Contact>();

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        lv = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactList);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(contactList.isEmpty()){
            try {
                contactList = readFromFile();
            } catch (Exception e) {
                Log.e("login activity", "File not found: " + e.toString());
            }
        }
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contactList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop(){
        super.onStop();
        writeToFile(contactList);
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
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent){
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if(responseCode == RESULT_OK){
            if (requestCode == ADD) {
                Contact c = new Contact(contactList.size() + 1, resultIntent.getStringExtra("First"),
                        resultIntent.getStringExtra("Last"), resultIntent.getStringExtra("phone"));
                contactList.add(c);
                Toast.makeText(getApplicationContext(), "Added " + resultIntent.getStringExtra("First") + " contact", Toast.LENGTH_SHORT).show();
            }
            else if (requestCode == DEL){
                int id = Integer.parseInt(resultIntent.getStringExtra("id"));
                int i = 0;
                for( Contact c: contactList){
                    if (c.id == id){
                        Toast.makeText(getApplicationContext(), "Deleted " + contactList.get(i).toString() + " from contacts", Toast.LENGTH_SHORT).show();
                        contactList.remove(i);
                        break;
                    }
                    i++;
                }

            }
            adapter.notifyDataSetChanged();
        }
        else{
            if (requestCode == ADD) {
                Toast.makeText(getApplicationContext(), "Failed to add contact", Toast.LENGTH_SHORT).show();
            }
            else if (requestCode == DEL){
                Toast.makeText(getApplicationContext(), "Failed to delete contact", Toast.LENGTH_SHORT).show();
            }
        }
        adapter.notifyDataSetChanged();


    }

    //write contacts to text file
    public void writeToFile(ArrayList<Contact> contacts){
        //write semesters to text file
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
            for(int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                outputStreamWriter.write(contact.toString());
            }
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    //read semesters from text file
    public ArrayList<Contact> readFromFile(){
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            InputStream inputStream = openFileInput(filename);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ((receiveString = bufferedReader.readLine()) != null) {
                    Scanner scanner = new Scanner(receiveString);
                    int i = scanner.nextInt();
                    String first = scanner.next();
                    String last = scanner.next();
                    String phone = scanner.next();
                    Contact contact = new Contact(i, first, last, phone);
                    contacts.add(contact);
                }
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return contacts;
    }

    public void delete(View view) {
        Intent intent = new Intent(this, DeleteContact.class);
        startActivityForResult(intent, DEL);
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddContact.class);
        startActivityForResult(intent, ADD);
    }
}
