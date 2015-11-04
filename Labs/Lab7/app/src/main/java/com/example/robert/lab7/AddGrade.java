package com.example.robert.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddGrade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_grade, menu);
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

    public void addDb(View view) {
        //set up helpers
        GradesDBHelper dbHelper = new GradesDBHelper(this);
        EditText sid = (EditText)findViewById(R.id.studentId);
        EditText cc = (EditText)findViewById(R.id.courseComponent);
        EditText m = (EditText)findViewById(R.id.mark);

        //get data from input
        int studentId = Integer.parseInt(sid.getText().toString());
        String courseComponent = cc.getText().toString();
        float mark = Float.parseFloat(m.getText().toString());

        //put in db with helper
        dbHelper.createGrade(studentId, courseComponent, mark);

        Intent results = new Intent();
        setResult(RESULT_OK,results);
        finish();
    }
}
