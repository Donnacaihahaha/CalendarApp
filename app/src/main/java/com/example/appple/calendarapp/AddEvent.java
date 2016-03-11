package com.example.appple.calendarapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddEvent extends AppCompatActivity {


    Button btnSave;
    EditText editTexteventName;
    EditText editTexteventdetails;

    //private int _event_Id=0;
    String datetime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Intent intent  = getIntent();
        datetime = intent.getExtras().getString("message1");

        btnSave = (Button) findViewById(R.id.btnSave);

        editTexteventName = (EditText) findViewById(R.id.editTexteventname);
        editTexteventdetails = (EditText) findViewById(R.id.editTexteventdetails);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableData newevent = new TableData();
                newevent.eventname = editTexteventName.getText().toString();
                newevent.eventdetails = editTexteventdetails.getText().toString();
                eventsRepos repo = new eventsRepos(AddEvent.this);
                repo.insert(newevent);
                Intent intent = new Intent(AddEvent.this, showevents.class);

                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("message1", datetime);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
    }