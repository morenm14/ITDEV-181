package com.example.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NotesDB extends SQLiteOpenHelper {

    //create db
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notesDB";
    private static final String DATABASE_TABLE = "Notes";

    //create table columns
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";

    public NotesDB(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INT PRIMARY KEY,"+
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_DATE + " TEXT," +
                KEY_TIME + " TEXT" + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion){
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    //insert note to db
    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_TITLE, note.getTitle());
        contentValues.put(KEY_CONTENT, note.getContent());
        contentValues.put(KEY_DATE, note.getDate());
        contentValues.put(KEY_TIME, note.getTime());

        long ID = db.insert(DATABASE_TABLE,null, contentValues);

        Log.d("inserted", "id: " + ID);
        return ID;
    }

    // retrieve one note
    public Note getNote(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{
                KEY_ID, KEY_TITLE, KEY_CONTENT,KEY_DATE, KEY_TIME},
                KEY_ID + "=?", new String[]{String.valueOf(id)},
                null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Note note = new Note(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));

        cursor.close();

        return note;
    }

    //retrieve all notes
    public List<Note> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> notes = new ArrayList<>();

        String query = String.format("SELECT * FROM %s", DATABASE_TABLE);
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){

            do{
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                notes.add(note);

            }while (cursor.moveToNext());
        }

        cursor.close();

        return notes;
    }
}
