package com.example.androidtask_itwolf.ContactsScreen.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ContactEntity.class } , version = 1)
public abstract class DatabaseRoom extends RoomDatabase {
    public abstract ContactDao getContactDao();
}
