package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class NoteDetails extends AppCompatActivity {
    TextView noteDetail;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        noteDetail = findViewById(R.id.noteDetail);

        Intent intent = getIntent();
        long id = intent.getLongExtra("ID", 0);
        noteDetail.setText(intent.getStringExtra("content"));

        Toast.makeText(NoteDetails.this, "ID: " + id, Toast.LENGTH_SHORT).show();


        // set toolbar
        toolbar = findViewById(R.id.detailToolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(intent.getStringExtra("title"));
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_details,menu);
        return super.onCreateOptionsMenu(menu);
    }
}