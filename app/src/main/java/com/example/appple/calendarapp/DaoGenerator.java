package com.example.appple.calendarapp;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by appple on 3/11/16.
 */
public class DaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.appple.calendarapp"); //Scheme for GreenDAO ORM
        createDB(schema);
        new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "./app/src/main/java/");
    }

    private static void createDB(Schema schema) {

        // Add event
        Entity event = schema.addEntity("Event");
        event.addIdProperty();

        // Add the event properties
        event.addStringProperty("title");
        event.addDateProperty("date");
    }
}
