package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class AddNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText newNoteTitle, newNoteDetails;
    Date date, time;
    String noteDate, timeCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // set toolbar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        Objects.requireNonNull(getSupportActionBar()).setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get date
        date = Calendar.getInstance().getTime();
        noteDate = DateFormat.getDateInstance().format(date);

        //set time created
        time = Calendar.getInstance().getTime();
        timeCreated = DateFormat.getTimeInstance().format(time);

        //set resources
        newNoteTitle = findViewById(R.id.newNoteTitle);
        newNoteDetails = findViewById(R.id.newNoteDetails);

        newNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()!= 0){
                    Objects.requireNonNull(getSupportActionBar()).setTitle(charSequence);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                this.finish();
                break;

            case R.id.save:
                if (newNoteTitle.getText().toString().isEmpty() || newNoteDetails.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    break;
                }else{
                    Note note = new Note();
                    note.setTitle(newNoteTitle.getText().toString());
                    note.setContent(newNoteDetails.getText().toString());
                    note.setDate(noteDate);
                    note.setTime(timeCreated);

                    NotesDB notesDB = new NotesDB(AddNote.this);
                    notesDB.addNote(note);

                    newNoteTitle.setText(null);
                    newNoteDetails.setText(null);


                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    this.finish();
                }

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                break;
        }
        
        return super.onOptionsItemSelected(item);
    }
}