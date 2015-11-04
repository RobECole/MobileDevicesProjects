package com.example.robert.assignment2;

/**
 * Created by Robert on 04-Nov-2015.
 */
public class Product {
    public int productId;
    public String name;
    public String description;
    public float price;

    public Product(int pid, String name, String desc, float price){
        this.productId = pid;
        this.name = name;
        this.description = desc;
        this.price = price;
    }

    public int getProductId(){
        return this.productId;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public float getPrice(){
        return this.price;
    }
    @Override
    public String toString(){
        return String.format("%d %s %s %s", this.productId, this.name, this.description, this.price);
    }


}
