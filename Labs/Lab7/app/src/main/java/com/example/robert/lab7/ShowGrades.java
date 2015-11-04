package com.example.robert.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ShowGrades extends AppCompatActivity {
    public ArrayAdapter<Grade> adapter;
    static ArrayList<Grade> gradeList = new ArrayList<>();

    ListView lv;
    GradesDBHelper dbHelper = new GradesDBHelper(this);


    @Override
    protected  void onStart(){
        super.onStart();
        try{
            gradeList = dbHelper.getAllGrades();
        }catch (Exception e){

        }

    }

    @Override
    protected void onStop(){
        super.onStop();
        dbHelper.deleteAllGrades();

        for(Grade gr: gradeList){
            dbHelper.createGrade(gr.getStudentId(),gr.getCourseComponent(),gr.getMark());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grades);

        lv = (ListView)findViewById(R.id.listView);

        gradeList = dbHelper.getAllGrades();
        adapter = new ArrayAdapter<Grade>(this,android.R.layout.simple_list_item_1,gradeList);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_grades, menu);
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

    public void add(View view) {
        Intent intent = new Intent(this, AddGrade.class);
        startActivityForResult(intent,23);
    }

    public void rmv(View view) {
        Intent intent = new Intent (this, DeleteGrade.class);
        startActivityForResult(intent,32);
    }

    @Override
     public void onActivityResult(int requestCode, int responseCode, Intent resultIntent){
        super.onActivityResult(requestCode, responseCode, resultIntent);
        gradeList = dbHelper.getAllGrades();
        adapter = new ArrayAdapter<Grade>(this,android.R.layout.simple_list_item_1,gradeList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
