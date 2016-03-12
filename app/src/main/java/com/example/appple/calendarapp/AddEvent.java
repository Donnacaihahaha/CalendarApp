package com.example.appple.calendarapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AddEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.containerCalendarFragment, addeventfrag.newInstance())
                .addToBackStack(null)
                .commit();
    }
    }