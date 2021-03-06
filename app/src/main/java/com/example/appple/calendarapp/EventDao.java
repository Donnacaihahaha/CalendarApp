package com.example.appple.calendarapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

import com.example.appple.calendarapp.Event;
/**
 * Created by appple on 3/11/16.
 */
public class EventDao extends AbstractDao<Event, Long> {

public static final String TABLENAME = "EVENT";

public static class Properties {
    public final static Property Id = new Property(0, Long.class, "id", true, "_id");
    public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
    public final static Property Date = new Property(2, java.util.Date.class, "date", false, "DATE");
};


public EventDao(DaoConfig config) {
        super(config);
        }

public EventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        }

/** Creates the underlying database table. */
public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "'EVENT' (" + //
        "'_id' INTEGER PRIMARY KEY ," + // 0: id
        "'TITLE' TEXT," + // 1: title
        "'DATE' INTEGER);"; // 2: date
        db.execSQL(sql);
        }

/** Drops the underlying database table. */
public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'EVENT'";
        db.execSQL(sql);
        }

/** @inheritdoc */
@Override
protected void bindValues(SQLiteStatement stmt, Event entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
        stmt.bindLong(1, id);
        }

        String title = entity.getTitle();
        if (title != null) {
        stmt.bindString(2, title);
        }

        java.util.Date date = entity.getDate();
        if (date != null) {
        stmt.bindLong(3, date.getTime());
        }
        }

/** @inheritdoc */
@Override
public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
        }

/** @inheritdoc */
@Override
public Event readEntity(Cursor cursor, int offset) {
        Event entity = new Event( //
        cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
        cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
        cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)) // date
        );
        return entity;
        }

/** @inheritdoc */
@Override
public void readEntity(Cursor cursor, Event entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        }

@Override
protected Long updateKeyAfterInsert(Event entity, long rowId) {
        entity.setId(rowId);
        return rowId;
        }

/** @inheritdoc */
@Override
public Long getKey(Event entity) {
        if(entity != null) {
        return entity.getId();
        } else {
        return null;
        }
        }

/** @inheritdoc */
@Override
protected boolean isEntityUpdateable() {
        return true;
        }

}
