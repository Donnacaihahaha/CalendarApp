package com.example.appple.calendarapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addeventfrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link addeventfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addeventfrag extends Fragment {
    private EditText title_editText;
    private EditText date_editText;
    private Button save_button;

    private Calendar calendar;

    public addeventfrag() {
        // Required empty public constructor
    }

    public static addeventfrag newInstance() {
        addeventfrag fragment = new addeventfrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_addeventfrag, container, false);

        calendar = Calendar.getInstance();

        title_editText = (EditText) view.findViewById(R.id.title_editText);
        date_editText = (EditText) view.findViewById(R.id.date_editText);
        save_button = (Button) view.findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if ("".equals(title_editText.getText().toString())) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("Please provide a title for the event.")
                            .setCancelable(true)
                            .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .show();

                    return;
                }

                Intent data = new Intent();
                data.putExtra("title", title_editText.getText().toString());
                data.putExtra("date", calendar.getTime());
                getActivity().finish();
            }
        });

        return view;
    }

}
