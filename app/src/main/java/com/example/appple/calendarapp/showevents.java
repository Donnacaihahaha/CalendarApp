package com.example.appple.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class showevents extends AppCompatActivity implements android.view.View.OnClickListener{

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
        if(studentList.size()!=0) {




            ListAdapter adapter = new SimpleAdapter( );
            lv.setAdapter(adapter);
        }else{
            Toast.makeText(this, "No events!", Toast.LENGTH_SHORT).show();
        }

    }
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {
            Intent intent = new Intent(this, AddEvent.class);
            Bundle bundle = new Bundle();
            bundle.putString("message1", datetime);
            intent.putExtras(bundle);
            startActivity(intent);

        }
    }
}
