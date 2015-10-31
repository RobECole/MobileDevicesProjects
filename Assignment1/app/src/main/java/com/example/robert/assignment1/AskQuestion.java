package com.example.robert.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AskQuestion extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ask_question);
        Button yes = (Button)findViewById(R.id.yes);
        Button no = (Button)findViewById(R.id.no);

        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        TextView question = (TextView)findViewById(R.id.questionView);
        TextView remark = (TextView)findViewById(R.id.remarkView);

        switch (MainMenu.questionCount) {
            case 1:
                question.setText(R.string.Question_1);
                remark.setText(R.string.Sub_1);
                break;
            case 2:
                question.setText(R.string.Question_2);
                remark.setText(R.string.Sub_2);
                break;
            case 3:
                question.setText(R.string.Question_3);
                remark.setText(R.string.Sub_3);
                break;
            case 4:
                question.setText(R.string.Question_4);
                remark.setText(R.string.Sub_4);
                break;
            case 5:
                question.setText(R.string.Question_5);
                remark.setText(R.string.Sub_5);
                break;
        }

    }

    @Override
    public void onClick(View v){
        if (v.getId() == R.id.yes) {
            MainMenu.yesCount++;
        }
        else if (v.getId() == R.id.no){
            MainMenu.noCount++;
        }
        MainMenu.questionCount++;
        Intent intent;
        if(MainMenu.questionCount<6){
            intent = new Intent(this, AskQuestion.class);
        }
        else {
            intent = new Intent(this, Summary.class);
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ask_question, menu);
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
}
