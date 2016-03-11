package com.example.appple.calendarapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by appple on 3/10/16.
 */
public class eventsRepos {
    private DatabaseHelper dbHelper;

    public eventsRepos(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public int insert(TableData newevent) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableData.events, newevent.eventname);
        values.put(TableData.dateandtime,newevent.dataname);
        values.put(TableData.eventsdetails,newevent.eventdetails);


        // Inserting Row
        long eventnum = db.insert(TableData.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) eventnum;
    }

    public void delete(int eventnum) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(TableData.TABLE,TableData.eventnumber+ "= ?", new String[] { String.valueOf(eventnum) });
        db.close(); // Closing database connection
    }

    public void update(TableData newevent) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TableData.events, newevent.eventname);
        values.put(TableData.dateandtime,newevent.dataname);
        values.put(TableData.eventsdetails,newevent.eventdetails);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(TableData.TABLE, values, TableData.eventnumber + "= ?", new String[] { String.valueOf(newevent.eventnum) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getEventList(String dateandtime) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                TableData.eventnumber + "," +
                TableData.events + "," +
                TableData.dateandtime + ","+
                TableData.eventsdetails + ","+
                " FROM " + TableData.TABLE;


        ArrayList<HashMap<String, String>> getEventList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> event = new HashMap<String, String>();
                event.put("id", cursor.getString(cursor.getColumnIndex(TableData.eventnumber)));
                event.put("name", cursor.getString(cursor.getColumnIndex(TableData.events)));
                getEventList.add(event);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return getEventList;

    }

    public ArrayList<HashMap<String, String>> getStudentByDate(String dateandtime){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                TableData.eventnumber + "," +
                TableData.events + "," +
                TableData.dateandtime + ","+
                TableData.eventsdetails + ","+
                " FROM " + TableData.TABLE
                + " WHERE " +
                TableData.dateandtime+ "=?";

        int iCount =0;
        ArrayList<HashMap<String, String>> getEventList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(dateandtime) } );


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> event = new HashMap<String, String>();
                event.put("id", cursor.getString(cursor.getColumnIndex(TableData.eventnumber)));
                event.put("name", cursor.getString(cursor.getColumnIndex(TableData.events)));
                getEventList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return getEventList;
    }



}
