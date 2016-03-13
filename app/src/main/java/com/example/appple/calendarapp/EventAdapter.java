package com.example.appple.calendarapp;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.BaseAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by appple on 3/11/16.
 */
public class EventAdapter extends BaseAdapter  {
    private ArrayList<Event> eventsList;
    private Context context;

    private static class ViewHolder {
        TextView textTitle;
        TextView textDate;
        Button btnDel;
    }

    public EventAdapter(Context context, ArrayList<Event> eventsList) {
        this.eventsList = eventsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public Event getItem(int position) {
        return eventsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view

        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.eventlist, parent, false);

            // Initialize the UI elements
            viewHolder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            viewHolder.textDate = (TextView) convertView.findViewById(R.id.textDate);
            viewHolder.btnDel = (Button) convertView.findViewById(R.id.btnDel);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get the data item for this position
        final Event event = getItem(position);

        // Populate the data into the template view using the data object
        viewHolder.textTitle.setText(event.getTitle());

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.US);
        viewHolder.textDate.setText(dateFormat.format(event.getDate()));
         viewHolder.btnDel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity) context).deleteEvent(event);
                } else {
                    Log.d("EventAdapter", "cannot delete");
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
