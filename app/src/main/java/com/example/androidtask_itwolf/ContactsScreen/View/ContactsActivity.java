package com.example.androidtask_itwolf.ContactsScreen.View;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidtask_itwolf.ContactsScreen.Adapter.ContactInteractor;
import com.example.androidtask_itwolf.ContactsScreen.Adapter.ContactRecycleViewAdapter;
import com.example.androidtask_itwolf.ContactsScreen.Room.ContactEntity;
import com.example.androidtask_itwolf.ContactsScreen.Room.DatabaseRoom;
import com.example.androidtask_itwolf.R;

import java.util.List;

public class ContactsActivity extends AppCompatActivity implements ContactInteractor {

    private DatabaseRoom databaseRoom;
    private EditText nameEditText;
    private EditText phoneEditText;
    private RecyclerView recyclerView;
    private ContactRecycleViewAdapter contactRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        databaseRoom = Room.databaseBuilder(getApplicationContext(), DatabaseRoom.class, "Database").allowMainThreadQueries().build();

        nameEditText = findViewById(R.id.nameedit);
        phoneEditText = findViewById(R.id.phoneedit);
        recyclerView = findViewById(R.id.recycleview);

        getAllContacts();
    }

    public void addContact(View view) {
        if (!nameEditText.getText().toString().matches("") && !phoneEditText.getText().toString().matches("")) {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setName(nameEditText.getText().toString());
            contactEntity.setPhone(phoneEditText.getText().toString());
            long id = databaseRoom.getContactDao().addContact(contactEntity);
            if (id > 0) {
                Toast.makeText(getApplicationContext(), "Contact Saved", Toast.LENGTH_LONG).show();
                nameEditText.setText("");
                phoneEditText.setText("");
                phoneEditText.clearFocus();
                getAllContacts();
            }
        } else {
            Toast.makeText(this, "Please Fill Contact Data", Toast.LENGTH_LONG).show();
        }
    }

    private void getAllContacts() {
        if (databaseRoom.getContactDao().getAllContacts().size() >= 0) {
            List<ContactEntity> contactEntityList = databaseRoom.getContactDao().getAllContacts();
            contactRecycleViewAdapter = new ContactRecycleViewAdapter(contactEntityList, this, this);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(contactRecycleViewAdapter);
        }
    }

    @Override
    public void deleteContact(final ContactEntity contactEntity) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseRoom.getContactDao().deleteContact(contactEntity.getId());
                        getAllContacts();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
