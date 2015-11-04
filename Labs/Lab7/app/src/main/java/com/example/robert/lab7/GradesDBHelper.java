package com.example.robert.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Robert on 04-Nov-2015.
 */
public class GradesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILENAME = "grades.db";
    public static final String TABLE_NAME = "Grades";

    // don't forget to use the column name '_id' for your primary key
    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + "(" +
            "  studentId int primary key," +
            "  courseComponent varchar(100) not null," +
            "  mark decimal not null" +
            ")";

    public static final String DROP_STATEMENT = "DROP TABLE " + TABLE_NAME;

    public GradesDBHelper(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // the implementation below is adequate for the first version
        // however, if we change our table at all, we'd need to execute code to move the data
        // to the new table structure, then delete the old tables (renaming the new ones)

        // the current version destroys all existing data
        database.execSQL(DROP_STATEMENT);
        database.execSQL(CREATE_STATEMENT);
    }

    public Grade createGrade(int studentId, String courseComponent, float mark) {
        // create the object
        Grade grade = new Grade(studentId, courseComponent, mark);

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // insert the data into the database
        ContentValues values = new ContentValues();
        values.put("studentId", grade.getStudentId());
        values.put("courseComponent", grade.getCourseComponent());
        values.put("mark", grade.getMark());
        long id = database.insert(TABLE_NAME, null, values);

        // assign the Id of the new database row as the Id of the object
        grade.setId(id);

        return grade;
    }

    public Grade getGrade(long id) {
        Grade grade = null;

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // retrieve the contact from the database
        String[] columns = new String[] { "studentId", "courseComponent", "mark" };
        Cursor cursor = database.query(TABLE_NAME, columns, "_id = ?", new String[] { "" + id }, "", "", "");
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            int studentId = cursor.getInt(0);
            String courseComponent = cursor.getString(1);
            float mark = cursor.getFloat(2);
            grade = new Grade(studentId, courseComponent, mark);
            grade.setId(id);
        }

        Log.i("DatabaseAccess", "getGrade(" + id + "):  grade: " + grade);

        return grade;
    }

    public ArrayList<Grade> getAllGrades() {
        ArrayList<Grade> grades = new ArrayList<Grade>();

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // retrieve the contact from the database
        String[] columns = new String[] { "studentId", "courseComponent", "mark" };
        Cursor cursor = database.query(TABLE_NAME, columns, "", new String[]{}, "", "", "");
        cursor.moveToFirst();
        do {
            // collect the contact data, and place it into a contact object
            int studentId = cursor.getInt(0);
            String courseComponent = cursor.getString(1);
            float mark = cursor.getFloat(2);

            Grade grade = new Grade(studentId, courseComponent, mark);
            // add the current contact to the list
            grades.add(grade);

            // advance to the next row in the results
            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("DatabaseAccess", "getAllContacts():  num: " + grades.size());

        return grades;
    }
    public boolean updateGrade(Grade grade) {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // update the data in the database
        ContentValues values = new ContentValues();
        values.put("studentId", grade.getStudentId());
        values.put("courseComponent", grade.getCourseComponent());
        values.put("mark", grade.getMark());
        int numRowsAffected = database.update(TABLE_NAME, values, "_id = ?", new String[] { "" + grade.getId() });

        Log.i("DatabaseAccess", "updateContact(" + grade + "):  numRowsAffected: " + numRowsAffected);

        // verify that the contact was updated successfully
        return (numRowsAffected == 1);
    }

    public boolean deleteGrade(int id) {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // delete the contact
        int numRowsAffected = database.delete(TABLE_NAME, "studentId = ?", new String[] { "" + id });

        Log.i("DatabaseAccess", "deleteGrade(" + id + "):  numRowsAffected: " + numRowsAffected);

        // verify that the contact was deleted successfully
        return (numRowsAffected == 1);
    }

    public void deleteAllGrades() {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // delete the contact
        int numRowsAffected = database.delete(TABLE_NAME, "", new String[] {});

        Log.i("DatabaseAccess", "deleteAllGrades():  numRowsAffected: " + numRowsAffected);
    }
}
