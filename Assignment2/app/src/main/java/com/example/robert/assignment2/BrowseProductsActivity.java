package com.example.robert.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BrowseProductsActivity extends AppCompatActivity {

    @Override
    protected  void onStart(){
        super.onStart();
        //TODO load DB on start

    }

    @Override
    protected void onStop(){
        super.onStop();
        //TODO save DB on stop
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse_products, menu);
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

    public void addProduct(View view) {
        //TODO Handle add product feature to open addProduct activity
    }

    public void next(View view) {
        //TODO change view to display next product in products<>
    }

    public void delete(View view) {
        //TODO Create delete function to remove current product from DB
    }

    public void prev(View view) {
        //TODO change view to display previous product in catalogue
    }

    public void showProduct(){
        //TODO function that takes product argument and displays in proper field
    }

    public void convertToBitCoin(){
        // TODO create async task to convert price (dollars) to BTC: https://blockchain.info/tobtc?currency=CAD&value=49.99
    }
}
