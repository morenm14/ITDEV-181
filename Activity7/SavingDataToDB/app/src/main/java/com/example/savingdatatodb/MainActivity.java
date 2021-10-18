package com.example.savingdatatodb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, phoneNumber;
    Button submitBtn, showDataBtn, editDataBtn, deleteDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        submitBtn = findViewById(R.id.submitBtn);
        showDataBtn = findViewById(R.id.showDataBtn);
        editDataBtn = findViewById(R.id.editDataBtn);
        deleteDataBtn = findViewById(R.id.deleteDataBtn);

        submitBtn.setOnClickListener(view -> {
            String inName = name.getText().toString().trim();
            String inPhone = phoneNumber.getText().toString().trim();

            try {
                ContactsDB db = new ContactsDB(this);
                db.open();
                db.createEntry(inName,inPhone);
                db.close();
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                name.setText(null);
                phoneNumber.setText(null);
            }catch (SQLException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        showDataBtn.setOnClickListener(view -> {

            startActivity(new Intent(this, Data.class));

        });

        editDataBtn.setOnClickListener(view -> {
            try {
                ContactsDB db = new ContactsDB(this);
                db.open();
                db.updateRecord("1", "Mario Moreno", "4145555555");
                db.close();
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            }catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        deleteDataBtn.setOnClickListener(view -> {
            try {
                ContactsDB db = new ContactsDB(this);
                db.open();
                db.deleteRecord("1");
                db.close();
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                
            }catch (SQLException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                
            }

        });
    }
}