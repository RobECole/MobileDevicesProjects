package com.example.robert.lab7;

/**
 * Created by Robert on 04-Nov-2015.
 */
public class Grade {

    public long id;
    public int studentId;
    public String courseComponent;
    public float mark;

    public Grade (int id, String cc, float m){
        this.studentId = id;
        this.courseComponent = cc;
        this.mark = m;
    }

    public int getStudentId(){
        return this.studentId;
    }

    public String getCourseComponent(){
        return this.courseComponent;
    }
    public void setId(long id){
        this.id = id;
    }
    public float getMark(){
        return this.mark;
    }
    public long getId(){
        return this.id;
    }

    @Override
    public String toString(){
        String s = studentId + " " + courseComponent + " " + mark;
        return s;
    }


}
