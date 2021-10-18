package com.example.savingdatatodb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDB {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "person_name";
    public static final  String KEY_PHONE = "_phone";

    private final  String DATABASE_NAME = "ContactsDB";
    private final String DATABASE_TABLE =  "ContactsTable";
    private final int   DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public ContactsDB(Context context){
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode ="CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_NAME + " TEXT NOT NULL, " +
                    KEY_PHONE + " TEXT NOT NULL);";
            db.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public ContactsDB open() throws SQLException {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        ourHelper.close();
    }

    public long createEntry(String name, String phoneNumber){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phoneNumber);
        return ourDatabase.insert(DATABASE_TABLE, null, values);
    }

    public String getData(){
        String [] cols = new String[] {KEY_ROWID, KEY_NAME, KEY_PHONE};
        Cursor cursor = ourDatabase.query(DATABASE_TABLE, cols,null,null, null,null, null);
        String result = "";
        int iRowID = cursor.getColumnIndex(KEY_ROWID);
        int iName = cursor.getColumnIndex(KEY_NAME);
        int iPhone = cursor.getColumnIndex(KEY_PHONE);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result = result + cursor.getString(iRowID) + ": " + cursor.getString(iName) + " " + cursor.getString(iPhone) + "\n";
        }

        cursor.close();

        return result;
    }

    public  long deleteRecord(String id){
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=?", new String[]{id});
    }

    public long updateRecord(String id, String name, String phone){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        return ourDatabase.update(DATABASE_TABLE, values, KEY_ROWID + "=?", new String[]{id});
    }
}
