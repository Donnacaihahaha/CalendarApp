package com.example.appple.calendarapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by appple on 3/10/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "crud.db";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_EVENT = "CREATE TABLE" + TableData.TABLE+
                "(" + TableData.eventnumber + "INTEGER PRIMARY KEY AUTOINCREMENT" +
                TableData.events +"TEXT, "+
                TableData.dateandtime + "TEXT, " +
                TableData.eventsdetails + " TEXT )";
        db.execSQL(CREATE_TABLE_EVENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableData.TABLE);

        onCreate(db);

    }
}


