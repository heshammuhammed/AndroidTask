package com.example.androidtask_itwolf.ContactsScreen.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    long addContact(ContactEntity contactEntity);

    @Query("SELECT * FROM contacts")
    List<ContactEntity> getAllContacts();

    @Query("DELETE FROM contacts WHERE id = :contactId")
    void deleteContact(int contactId);

}
