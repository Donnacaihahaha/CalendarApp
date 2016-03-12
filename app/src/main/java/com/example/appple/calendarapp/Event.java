package com.example.appple.calendarapp;

/**
 * Created by appple on 3/11/16.
 */
public class Event {
    private Long id;
    private String title;
    private java.util.Date date;

    public Event() {
    }

    public Event(Long id) {
        this.id = id;
    }

    public Event(Long id, String title, java.util.Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }
}
