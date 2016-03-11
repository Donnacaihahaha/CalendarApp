package com.example.appple.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = (CalendarView) findViewById(R.id.calendar);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(MainActivity.this, showevents.class);

                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("message1", dayOfMonth+ "/" + month+"/"+year);
                intent.putExtras(bundle);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), dayOfMonth+ "/" + month+"/"+year, Toast.LENGTH_LONG).show();
            }
        });
    }
}
