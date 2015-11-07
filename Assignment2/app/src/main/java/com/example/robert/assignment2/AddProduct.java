package com.example.robert.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
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

    public void cancel(View view) {
        wipe(view);

        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }

    public void saveProduct(View view) {
        //set helpers
        ProductDBHelper dbHelper = new ProductDBHelper(this);
        Random rnd = new Random();
        EditText n = (EditText)findViewById(R.id.nameField);
        EditText d = (EditText)findViewById(R.id.descripField);
        EditText p = (EditText)findViewById(R.id.priceField);

        //get data, wipe fields
        String name = n.getText().toString();
        String descrip = d.getText().toString();
        float price = Float.parseFloat(p.getText().toString());
        wipe(view);

        //put in db return to main
        dbHelper.createProduct(rnd.nextInt(10000),name,descrip,price);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public void wipe(View view){
        EditText name = (EditText)findViewById(R.id.nameField);
        EditText descrip = (EditText)findViewById(R.id.descripField);
        EditText price = (EditText)findViewById(R.id.priceField);

        name.setText("");
        descrip.setText("");
        price.setText("");
    }
}
