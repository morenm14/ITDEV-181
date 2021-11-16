package com.example.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        //CREATE TABLE Notes (id INT PRIMARY KEY, title TEXT , content TEXT, date TEXT, time TEXT);
        String query = String.format("CREATE TABLE %s ( %s INT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                DATABASE_TABLE, KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME);
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
}
