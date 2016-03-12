package com.example.appple.calendarapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import de.greenrobot.dao.QueryBuilder;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView eventsListView;

    private ArrayList<Event> eventsList;
    private EventAdapter eventsListAdapter;

    private DaoMaster.DevOpenHelper calendarDBHelper;
    private SQLiteDatabase calendarDB;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private EventDao eventDao;
    Button btnAdd;
    private final int ADD_EVENT_REQUEST = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = (CalendarView) findViewById(R.id.calendar_calendarView);
        eventsListView = (ListView) findViewById(R.id.events_listView);
        initDatabase();
        setEventsListForDate(new Date(calendarView.getDate()));

        btnAdd = (Button) findViewById(R.id.Add);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDay = Calendar.getInstance();

                selectedDay.set(Calendar.YEAR, year);
                selectedDay.set(Calendar.MONTH, month);
                selectedDay.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                selectedDay.set(Calendar.HOUR_OF_DAY, 0);
                selectedDay.set(Calendar.MINUTE, 0);
                selectedDay.set(Calendar.SECOND, 0);
                selectedDay.set(Calendar.MILLISECOND, 0);

                setEventsListForDate(selectedDay.getTime());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddEvent.class);
                startActivityForResult(intent, ADD_EVENT_REQUEST);

            }

        });


    }
}
