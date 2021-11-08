package com.example.backendlessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String APPLICATION_ID = "0639753A-28F1-79EC-FF17-071231F2CE00";
    public static final String API_KEY = "88A97712-599A-4A7A-A194-6F11BF10FC87";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person person = new Person("Mario", "Moreno");

        String whereClause = "name = 'Bruce'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.initApp( getApplicationContext(), APPLICATION_ID,API_KEY);

       Backendless.Data.of(Person.class).save(person, new AsyncCallback<Person>() {
           @Override
           public void handleResponse(Person response) {

           }

           @Override
           public void handleFault(BackendlessFault fault) {

           }
       });


    }
}