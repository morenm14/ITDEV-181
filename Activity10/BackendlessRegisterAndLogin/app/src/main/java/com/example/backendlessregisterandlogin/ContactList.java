package com.example.backendlessregisterandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class ContactList extends AppCompatActivity {

    ListView contactList;
    View loadingView, contactsView;
    TextView loadingText;
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        contactList = findViewById(R.id.contactList);
        loadingView = findViewById(R.id.loadingView);
        contactsView = findViewById(R.id.contactsView);
        loadingText = findViewById(R.id.loadingText);

        String whereClause = "userEmail = '" + AppInitializer.user.getEmail() + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        query.setGroupBy("name");

        loadingView.setVisibility(View.VISIBLE);
        loadingText.setText("Getting Contacts...");

        Backendless.Persistence.of(Contact.class).find(query, new AsyncCallback<List<Contact>>() {
            @Override
            public void handleResponse(List<Contact> response) {
                AppInitializer.contacts = response;
                adapter = new ContactsAdapter(ContactList.this, AppInitializer.contacts);
                contactList.setAdapter(adapter);
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(ContactList.this, fault.getMessage(), Toast.LENGTH_SHORT).show();
                loadingView.setVisibility(View.GONE);
            }
        });

    }
}