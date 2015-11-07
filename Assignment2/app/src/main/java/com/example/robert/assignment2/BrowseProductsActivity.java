package com.example.robert.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class BrowseProductsActivity extends AppCompatActivity implements listener {

    ProductDBHelper dbHelper = new ProductDBHelper(this);
    ArrayList<Product> productList = new ArrayList<>();
    final String urlBase = "https://blockchain.info/tobtc?currency=CAD&value=";
    Button prev, next;

    public int pointer = 0;

    @Override
    protected  void onStart(){
        super.onStart();
        productList = dbHelper.getAllProducts();
    }

    @Override
    protected void onStop(){
        super.onStop();
        dbHelper.deleteAllProducts();

        for(Product p:productList){
            dbHelper.createProduct(p.getProductId(),p.getName(),p.getDescription(),p.getPrice());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        pointer = 0;
        prev = (Button)findViewById(R.id.prev);
        next = (Button)findViewById(R.id.next);


        productList = dbHelper.getAllProducts();
        if(productList.size()>0){
            showProduct(productList.get(pointer));
            String url = urlBase + productList.get(pointer).price;
            toBTC async = new toBTC((this));
            async.execute(url);
        }
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
        Intent intent = new Intent(this, AddProduct.class);
        startActivityForResult(intent,32);
    }

    public void next(View view) {

        if (pointer < productList.size()-1){
            pointer++;
            Product p = productList.get(pointer);
            showProduct(p);

            if (pointer==productList.size()-1){
                next.setClickable(false);
            }
            if (pointer == 1){
                prev.setClickable(true);
            }
        }


    }

    public void delete(View view) {
        //TODO Create delete function to remove current product from DB
        Product rm = productList.get(pointer);
        productList.remove(pointer);
        dbHelper.deleteProduct(rm.productId);

        if (pointer > productList.size()-1){
            pointer--;
        }
        showProduct(productList.get(pointer));
    }

    public void prev(View view) {

        if (0 < pointer){
            pointer--;
            Product p = productList.get(pointer);
            showProduct(p);
            if (pointer==0){
                prev.setClickable(false);
            }
            if (pointer==productList.size()-2){
                next.setClickable(true);
            }
        }
    }

    public void showProduct(Product p){
        EditText n = (EditText)findViewById(R.id.nameField);
        EditText d = (EditText)findViewById(R.id.descripField);
        EditText pr = (EditText)findViewById(R.id.priceField);
        EditText prb = (EditText)findViewById(R.id.btcField);

        float price = p.getPrice();

        n.setText(p.getName());
        d.setText(p.getDescription());
        pr.setText(Float.toString(price));

        String url = urlBase + price;
        toBTC async = new toBTC((this));
        async.execute(url);
    }

    @Override
    public void bitCoin(String string){
        EditText prBTC = (EditText)findViewById(R.id.btcField);
        prBTC.setText(string);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent){
        super.onActivityResult(requestCode, responseCode, resultIntent);

        productList = dbHelper.getAllProducts();
        showProduct(productList.get(pointer));
    }
}
