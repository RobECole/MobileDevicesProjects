package com.example.robert.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Scanner;

public class DeleteGrade extends AppCompatActivity {

    public ArrayAdapter<Grade> adapter;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grade);

        sp = (Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<Grade>(this,android.R.layout.simple_spinner_item,ShowGrades.gradeList);
        sp.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_grade, menu);
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


    public void removeClass(View view) {
        GradesDBHelper dbHelper = new GradesDBHelper(this);
        String select = sp.getSelectedItem().toString();
        String[] tokens = select.split("\\s+");

        dbHelper.deleteGrade(Integer.parseInt(tokens[0]));

        Intent results = new Intent();
        setResult(RESULT_OK,results);
        finish();


    }
}
