package com.example.robert.lab6;

/**
 * Created by Robert on 23-Oct-2015.
 */
public class Contact {
    public String phone;
    public String fn;
    public String ln;
    public int id;

    public Contact(int i, String f, String l, String p){
        this.phone = p;
        this.fn = f;
        this.ln = l;
        this.id = i;
    }

    @Override
    public String toString(){
        return (this.id + " " + this.ln + " " + this.fn+ " " + this.phone);
    }
}
