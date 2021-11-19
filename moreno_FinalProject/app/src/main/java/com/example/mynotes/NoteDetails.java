package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class NoteDetails extends AppCompatActivity {
    TextView noteDetail;
    Toolbar toolbar;
    Adapter adapter;
    private NotesDB db;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        noteDetail = findViewById(R.id.noteDetail);

        Intent intent = getIntent();
        id = intent.getLongExtra("ID", 0);

        db = new NotesDB(this);
        Note note = db.getNote(id);
        noteDetail.setText(note.getContent());

        Toast.makeText(NoteDetails.this, "ID: " + id, Toast.LENGTH_SHORT).show();

        // set toolbar
        toolbar = findViewById(R.id.detailToolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(note.getTitle());
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_details,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.editNoteBtn){
            Intent intent = new Intent(getApplicationContext(), EditNote.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }

        if (item.getItemId() == R.id.deleteNoteBtn){
            db.deleteNote(id);
            startActivity(new Intent(NoteDetails.this, MainActivity.class));
            this.finish();
            Toast.makeText(NoteDetails.this, "Deleted", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}