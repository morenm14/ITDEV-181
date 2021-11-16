package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText newNoteTitle, newNoteDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // set toolbar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        //set resources
        newNoteTitle = findViewById(R.id.newNoteTitle);
        newNoteDetails = findViewById(R.id.newNoteDetails);

    }
}