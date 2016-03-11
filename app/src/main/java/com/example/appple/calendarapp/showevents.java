package com.example.appple.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class showevents extends AppCompatActivity {

String datetime;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showevents);
        Intent intent  = getIntent();
        datetime = intent.getExtras().getString("message1");
        eventsRepos repo = new eventsRepos(this);
        lv = (ListView) findViewById(R.id.list);
        ArrayList<HashMap<String, String>> studentList =  repo.getStudentByDate(datetime);
        ArrayList<String> events = new ArrayList<String>();
        ListAdapter adapter = new SimpleAdapter(showevents.this,studentList, R.layout.view_student_entry, new String[] { "id","name"}, new int[] {R.id.student_Id, R.id.student_name});
        setListAdapter(adapter);


    }
}
