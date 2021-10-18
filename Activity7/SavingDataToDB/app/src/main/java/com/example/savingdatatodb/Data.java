package com.example.savingdatatodb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Data extends AppCompatActivity {

    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        data = findViewById(R.id.data);

        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            data.setText(db.getData());
            db.close();
        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}