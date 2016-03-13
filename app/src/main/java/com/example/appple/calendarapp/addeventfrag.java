package com.example.appple.calendarapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

        setDateOnEditText();
        date_editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(), date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        save_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if ("".equals(title_editText.getText().toString())) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("Please provide a title")
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
                getActivity().setResult(getActivity().RESULT_OK, data);
                getActivity().finish();
            }
        });

        return view;
    }

    private DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            setDateOnEditText();
        }
    };

    private void setDateOnEditText() {
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        date_editText.setText(sdf.format(calendar.getTime()));
    }

}
