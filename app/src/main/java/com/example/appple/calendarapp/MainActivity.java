package com.example.appple.calendarapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.greenrobot.dao.QueryBuilder;
public class MainActivity extends AppCompatActivity {


    CalendarView calendarView;
    ListView eventsListView;
    ArrayList<Event> eventsList;
    EventAdapter eventsListAdapter;
    DaoMaster.DevOpenHelper calendarDBHelper;
    SQLiteDatabase calendarDB;
    DaoMaster daoMaster;
    DaoSession daoSession;
    EventDao eventDao;
    Button btnAdd;
    private final int ADD_EVENT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendar_calendarView);
        eventsListView = (ListView) findViewById(R.id.events_listView);

        eventsList = new ArrayList<Event>();
        eventsListAdapter = new EventAdapter(this, eventsList);
        eventsListView.setAdapter(eventsListAdapter);

        // initialise the database
        initDatabase();

        // Populate the eventsList for the date selected on the calendar view

        this.setEventsListForDate(new Date(calendarView.getDate()));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDay = Calendar.getInstance();

                selectedDay.set(Calendar.YEAR, year);

                selectedDay.set(Calendar.MONTH, month);
                selectedDay.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setEventsListForDate(selectedDay.getTime());
            }
        });

        btnAdd =  (Button) findViewById(R.id.Add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddEvent.class);
                startActivityForResult(intent, ADD_EVENT_REQUEST);
            }
        });
    }


    public void setEventsListForDate(Date date) {

        Calendar todaytime = Calendar.getInstance();
        todaytime.setTime(date);
        todaytime.set(Calendar.HOUR_OF_DAY, 0);
        todaytime.set(Calendar.MINUTE, 0);
        todaytime.set(Calendar.SECOND, 0);
        todaytime.set(Calendar.MILLISECOND, 0);

        Calendar tomorrowtime = Calendar.getInstance();
        tomorrowtime.setTime(date);
        tomorrowtime.add(Calendar.DATE, 1);
        tomorrowtime.set(Calendar.HOUR_OF_DAY, 0);
        tomorrowtime.set(Calendar.MINUTE, 0);
        tomorrowtime.set(Calendar.SECOND, 0);

        // Get list of Guest objects in database using QueryBuilder.
        // If list is null, then database tables were created for first time,
        // so we call "closeReopenDatabase()" to reopen the database.
        QueryBuilder queryBuilder = eventDao.queryBuilder();
        List<Event> eventListFromDB = queryBuilder.where(EventDao.Properties.Date.between(todaytime.getTime(), tomorrowtime.getTime())).list();

        if (eventListFromDB == null) {
            closeReopenDatabase();
            eventListFromDB = queryBuilder.where(EventDao.Properties.Date.between(today.getTime(), tomorrow.getTime())).list();
        }

        eventsList.clear();
        eventsList.addAll(eventListFromDB);

        // Sort the list before displaying the events
        Collections.sort(eventsList, new Comparator<Event>() {
            public int compare(Event event1, Event event2) {
                return event1.getDate().compareTo(event2.getDate());
            }
        });
        calendarView.setDate(date.getTime());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


            Random rand = new Random();
            long id = rand.nextLong();
            while (eventDao.load(id) != null) {//check EventDao
                id = rand.nextLong();
            }

            String title = data.getStringExtra("title");
            Date date = (Date) data.getSerializableExtra("date");

            Event event = new Event(id, title, date);
            eventDao.insert(event);
            setEventsListForDate(date);

    }

    public void deleteEvent(Event event) {

        // Keep the date so we can refresh the list for the same date
        Date date = event.getDate();

        eventDao.delete(event);

        setEventsListForDate(date);
    }

    private void initDatabase() {
        calendarDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        calendarDB = calendarDBHelper.getWritableDatabase();

        // Get DaoMaster
        daoMaster = new DaoMaster(calendarDB);

        // Create initial database table if they do not exist
        DaoMaster.createAllTables(calendarDB, true);

        // Create a database access session
        daoSession = daoMaster.newSession();

        // Get instance of eventDao
        eventDao = daoSession.getEventDao();
    }

    private void closeDatabase() {
        daoSession.clear();
        calendarDB.close();
        calendarDBHelper.close();
    }

    private void closeReopenDatabase() {
        closeDatabase();

        calendarDBHelper = new DaoMaster.DevOpenHelper(this, "ORM.sqlite", null);
        calendarDB = calendarDBHelper.getWritableDatabase();

        //Get DaoMaster
        daoMaster = new DaoMaster(calendarDB);

        // Create DaoSession instance
        // Use method in DaoMaster to create a database access session
        daoSession = daoMaster.newSession();

        // From DaoSession instance, get instance of eventDao
        eventDao = daoSession.getEventDao();
    }
}
