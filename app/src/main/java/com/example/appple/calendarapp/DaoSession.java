package com.example.appple.calendarapp;


import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.IdentityScopeType;

import com.example.appple.calendarapp.Event;

import com.example.appple.calendarapp.EventDao;


/**
 * Created by appple on 3/11/16.
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eventDaoConfig;

    private final EventDao eventDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        eventDao = new EventDao(eventDaoConfig, this);

        registerDao(Event.class, eventDao);
    }

    public void clear() {
        eventDaoConfig.getIdentityScope().clear();
    }

    public EventDao getEventDao() {
        return eventDao;
    }
}
